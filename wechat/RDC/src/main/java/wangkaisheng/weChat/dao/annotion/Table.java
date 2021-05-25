package wangkaisheng.weChat.dao.annotion;

import java.lang.annotation.*;

/**
 * @author 一个帅哥
 * @description 用于标记表名
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    String name();
}
