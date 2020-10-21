package cn.ffyzz.spring.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Title:
 * @Author: FFYzz
 * @Mail: cryptochen95 at gmail dot com
 * @Date: 2020/10/21
 */
public class MessageFormatDemo {

    public static void main(String[] args) {

        int planet = 7;
        String event = "a disturbance in the Force";
        String messageFormatPattern = "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}, {3}.";
        MessageFormat messageFormat = new MessageFormat(messageFormatPattern);
        String res = messageFormat.format(new Object[]{planet, new Date(), event, "DDDDDDDDDDDD"});
        System.out.println(res);

        // 重置消息格式
        // applyPattern
        String anotherMessageFormatPattern = "This is a text : {0}, {1}, {2}";
        messageFormat.applyPattern(anotherMessageFormatPattern);
        res = messageFormat.format(new Object[]{"One", "Two", "Three", "Four"});
        System.out.println(res);

        // 重置 Local
        messageFormat.setLocale(Locale.US);
        messageFormat.applyPattern(messageFormatPattern);
        res = messageFormat.format(new Object[]{planet, new Date(), event, "DDDDDDDDDDDD"});
        System.out.println(res);

        // 重置 Format
        messageFormat.setFormat(1, new SimpleDateFormat("YYYY-MM-DD : HH:mm:ss"));
        res = messageFormat.format(new Object[]{planet, new Date(), event, "DDDDDDDDDDDD"});
        System.out.println(res);

    }

}
