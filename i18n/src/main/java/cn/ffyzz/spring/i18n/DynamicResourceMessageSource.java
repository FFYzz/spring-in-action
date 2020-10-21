package cn.ffyzz.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/21
 * <p>
 * 动态（更新）资源 {@link MessageSource} 实现
 * 实现步骤：
 * 1. 定位资源位置（ Properties 文件）
 * 2. 初始化 Properties 对象
 * 3. 实现 AbstractMessageSource#resolveCode 方法
 * 4. 监听资源文件（Java NIO 2 WatchService）
 * 5. 使用线程池处理文件变化
 * 6. 重新装载 Properties 对象
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String resourceFileName = "msg.properties";

    private static final String resourcePath = "/META-INF/" + resourceFileName;

    private static final String ENCODING = "UTF-8";

    private final Resource messagePropertiesResource;

    private final Properties messageProperties;

    private final ExecutorService executorService;

    private ResourceLoader resourceLoader;


    public DynamicResourceMessageSource() {
        messagePropertiesResource = getMessagePropertiesResource();
        executorService = Executors.newSingleThreadExecutor();
        messageProperties = loadPropertiesFromResource();
        // 监听资源文件（Java NIO 2 WatchService）
        onMsgPropertiesChanged();
    }

    /**
     * 对文件是否变化进行监听
     */
    private void onMsgPropertiesChanged() {
        if (this.messagePropertiesResource.isFile()) {
            // 获取文件系统中的文件
            try {
                File messagePropertiesFile = messagePropertiesResource.getFile();
                // 获取文件的路径
                Path messagePropertiesFilePath = messagePropertiesFile.toPath();
                // 获取当前的文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                // 获取 WatchService
                WatchService watchService = fileSystem.newWatchService();
                // 获取资源所在的目录
                Path dirPath = messagePropertiesFilePath.getParent();
                // 对目录进行注册
                dirPath.register(watchService, ENTRY_MODIFY);
                // 处理
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 单独开启一个线程进行异步处理
     * 与 WatchService 绑定
     *
     * @param watchService
     */
    private void processMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                // take 取不到时会阻塞
                WatchKey watchKey = watchService.take();
                try {
                    if (watchKey.isValid()) {
                        Watchable watchable = watchKey.watchable();
                        for (WatchEvent event : watchKey.pollEvents()) {
                            // // 目录路径（监听的注册目录）
                            Path dirPath = (Path) watchable;
                            // 事件所关联的对象即注册目录的子文件（或子目录）
                            // 事件发生源是相对路径
                            Path fileRelativePath = (Path) event.context();
                            if (resourceFileName.equals(fileRelativePath.getFileName().toString())) {
                                // 绝对路径
                                Path filePath = dirPath.resolve(fileRelativePath);
                                File file = filePath.toFile();
                                Properties properties = loadPropertiesFromResource(new FileReader(file));
                                synchronized (messageProperties) {
                                    messageProperties.clear();
                                    messageProperties.putAll(properties);
                                }
                            }
                        }
                    }
                } finally {
                    if (watchKey != null) {
                        watchKey.reset();
                    }
                }
            }
        });
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

    private Properties loadPropertiesFromResource() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource, ENCODING);
        try {
            return loadPropertiesFromResource(encodedResource.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties loadPropertiesFromResource(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private Resource getMessagePropertiesResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        return resource;
    }

    private ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : getDefaultResourceLoader();
    }

    private ResourceLoader getDefaultResourceLoader() {
        return new DefaultResourceLoader();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) {
        DynamicResourceMessageSource dynamicResourceMessageSource = new DynamicResourceMessageSource();
        for (int i = 0; i < 10000; ++i) {
            try {
                String message = dynamicResourceMessageSource.getMessage("name", new Object[]{}, Locale.getDefault());
                System.out.println(message);
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
