package cn.ffyzz.spring.resource;

import cn.ffyzz.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/20
 */
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String currentFilePath = "/" + System.getProperty("user.dir") + "/resource/src/main/java/cn/ffyzz/spring/resource/EncodedFileSystemResourceLoaderDemo.java";
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(currentFilePath);
        System.out.println(ResourceUtils.getContent(resource, "UTF-8"));
    }

}
