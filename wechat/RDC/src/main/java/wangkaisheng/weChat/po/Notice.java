package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

/**
 * @author Administrator
 */
@Table(name = "notice")
public class Notice {
    @Field(name = "id")
    private Integer id;
    @Field(name = "sendid")
    private Integer sendid;
    @Field(name = "userid")
    private Integer userid;
    @Field(name = "type")
    private Integer type;
    @Field(name = "name")
    private String name;
    @Field(name = "username")
    private String username;
    @Field(name = "gid")
    private Integer gid;

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", sendid=" + sendid +
                ", userid=" + userid +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", gid=" + gid +
                '}';
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendid() {
        return sendid;
    }

    public void setSendid(Integer sendid) {
        this.sendid = sendid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
