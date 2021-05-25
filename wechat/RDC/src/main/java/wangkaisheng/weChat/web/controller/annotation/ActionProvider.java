package wangkaisheng.weChat.web.controller.annotation;

import java.lang.annotation.*;

/**
 * @author 一个帅哥
 * @description 用于注解Provider子类的路径
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface ActionProvider {
    String path()default "";
}
