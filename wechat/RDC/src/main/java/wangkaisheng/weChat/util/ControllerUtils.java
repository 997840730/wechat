package wangkaisheng.weChat.util;

import wangkaisheng.weChat.web.controller.contants.RequestMethod;

/**
 * @author Administrator
 */
public class ControllerUtils {
    public static RequestMethod valueOf(String name) {
        try {
            name = name.toUpperCase().replaceAll("\\.", "_");
            return RequestMethod.valueOf(name);
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            return RequestMethod.INVALID_REQUEST;
        }
    }
}
