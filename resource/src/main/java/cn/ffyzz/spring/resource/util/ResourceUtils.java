package cn.ffyzz.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/20
 */
public interface ResourceUtils {


    static String getContent(Resource resource) {
        try {
            return getContent(resource, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String getContent(Resource resource, String coding) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource, coding);
        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        }
    }

}
