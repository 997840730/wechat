package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

/**
 * @author Administrator
 */
@Table(name = "noticed")
public class Noticed {
    @Field(name = "id")
    private Integer id;
    @Field(name = "sendid")
    private Integer sendid;
    @Field(name = "userid")
    private Integer userid;
    @Field(name = "contant")
    private String contant;
    @Field(name = "name")
    private String name;
    @Field(name = "groupname")
    private String groupname;

    @Override
    public String toString() {
        return "Noticed{" +
                "id=" + id +
                ", sendid=" + sendid +
                ", userid=" + userid +
                ", contant='" + contant + '\'' +
                ", name='" + name + '\'' +
                ", groupname='" + groupname + '\'' +
                '}';
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getSendid() {
        return sendid;
    }

    public void setSendid(Integer sendid) {
        this.sendid = sendid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }
}
