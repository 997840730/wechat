package wangkaisheng.weChat.web.controller.annotation;


import wangkaisheng.weChat.web.controller.contants.RequestMethod;

import java.lang.annotation.*;

/**
 * @author 一个帅哥
 * @description 用于注解对应服务项目与路径
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Action {
    String path()default "";
    RequestMethod method();
}
