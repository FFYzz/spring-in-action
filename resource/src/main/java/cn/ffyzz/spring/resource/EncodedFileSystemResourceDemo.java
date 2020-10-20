package cn.ffyzz.spring.resource;

import cn.ffyzz.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/20
 */
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String currentFilePath = System.getProperty("user.dir") + "/resource/src/main/java/cn/ffyzz/spring/resource/EncodedFileSystemResourceDemo.java";
        File currentFile = new File(currentFilePath);
        FileSystemResource fileSystemResource = new FileSystemResource(currentFile);
        System.out.println(ResourceUtils.getContent(fileSystemResource, "UTF-8"));
    }

}
