package wangkaisheng.weChat.util;


/**
 * @author Administrator
 */
public class StringUtils {

    public static String toLegalText(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        str = toLegalTextIgnoreTag(str);
        str = str.replaceAll("<[^>]+>", "");
        str = str.replace("\"", "");
        str = str.replaceAll("\t|\r|\n", "");
        return str;
    }
    public static String toLegalTextIgnoreTag(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        str = str.replaceAll("<style[^>]*?>[\\s\\S]*?<\\/style>", "");
        str = str.replaceAll("<script[^>]*?>[\\s\\S]*?<\\/script>", "");
        return str;
    }


    public static String field2SetMethod(String field) {
        StringBuilder method = new StringBuilder("set" + field);
        method.setCharAt(3, (char) (method.charAt(3) - 32));
        return method.toString();
    }
}
