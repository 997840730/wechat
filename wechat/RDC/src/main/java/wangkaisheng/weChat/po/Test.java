package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

import java.sql.Time;

/**
 * @author Administrator
 */
@Table(name = "Test")
public class Test {
    @Field(name = "time")
    Time time;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
