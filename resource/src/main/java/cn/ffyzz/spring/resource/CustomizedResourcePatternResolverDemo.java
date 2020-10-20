package cn.ffyzz.spring.resource;

import cn.ffyzz.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/20
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        String currentPackagePath = "/" + System.getProperty("user.dir") + "/resource/src/main/java/cn/ffyzz/spring/resource/";
        String allJavaFilesPathPattern = currentPackagePath + "*.java";
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        pathMatchingResourcePatternResolver.setPathMatcher(new JavaFilePathMatcher());
        Resource[] resources = pathMatchingResourcePatternResolver.getResources(allJavaFilesPathPattern);
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    static class JavaFilePathMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }

}
