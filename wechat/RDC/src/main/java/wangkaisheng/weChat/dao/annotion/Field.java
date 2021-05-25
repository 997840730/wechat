package wangkaisheng.weChat.dao.annotion;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    String name();
}
