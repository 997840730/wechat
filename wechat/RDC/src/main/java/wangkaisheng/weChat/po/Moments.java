package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@Table(name = "moments")
public class Moments {
    @Field(name = "id")
    private Integer id;
    @Field(name = "uid")
    private Integer uid;
    @Field(name = "talk")
    private String talk;
    @Field(name = "uuid")
    private String uuid;


    private List<String> imgs;

    @Override
    public String toString() {
        return "Moments{" +
                "id=" + id +
                ", uid=" + uid +
                ", talk='" + talk + '\'' +
                ", uuid='" + uuid + '\'' +
                ", imgs=" + imgs +
                '}';
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }
}
