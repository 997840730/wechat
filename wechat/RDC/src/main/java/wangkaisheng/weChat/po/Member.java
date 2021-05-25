package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

/**
 * @author Administrator
 */
@Table(name = "member")
public class Member {
    @Field(name = "id")
    private Integer id;
    @Field(name = "gid")
    private Integer gid;
    @Field(name = "uid")
    private Integer uid;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", gid=" + gid +
                ", uid=" + uid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
