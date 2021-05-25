package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

/**
 * @author Administrator
 */
@Table(name = "friends")
public class Friends {
    @Field(name = "id")
    private Integer id;
    @Field(name = "uid")
    private Integer uid;
    @Field(name = "fid")
    private Integer fid;

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", uid=" + uid +
                ", fid=" + fid +
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

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }
}
