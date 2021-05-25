package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

import java.sql.Date;

/**
 * @author Administrator
 */
@Table(name = "groups")
public class Groups {
    @Field(name = "id")
    private Integer groupId;
    @Field(name = "groupname")
    private String groupname;
    @Field(name ="groupuser")
    private Integer groupUser;
    @Field(name ="introduce")
    private String introduce;
    @Field(name ="date")
    private Date date;


    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupname='" + groupname + '\'' +
                ", groupUser=" + groupUser +
                ", introduce='" + introduce + '\'' +
                ", date=" + date +
                '}';
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(Integer groupUser) {
        this.groupUser = groupUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
