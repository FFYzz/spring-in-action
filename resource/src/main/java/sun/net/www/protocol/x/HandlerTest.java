package sun.net.www.protocol.x;

import org.springframework.remoting.support.UrlBasedRemoteAccessor;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/20
 */
public class HandlerTest {

    public static void main(String[] args) throws IOException {
//        URL url = new URL(null, "x:///default.properties", new Handler());
        // TODO error
        URL url = new URL("x:///default.properties");
        InputStream inputStream = url.openConnection().getInputStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }

}
