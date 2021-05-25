package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

/**
 * @author Administrator
 */
@Table(name = "blacklist")
public class BlackList {
    @Field(name = "id")
    private Integer id;
    @Field(name = "uid")
    private Integer uid;
    @Field(name = "bid")
    private Integer bid;

    @Override
    public String toString() {
        return "blacklist{" +
                "id=" + id +
                ", uid=" + uid +
                ", bid=" + bid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
}
