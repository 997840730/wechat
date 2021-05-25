package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

/**
 * @author Administrator
 */
@Table(name = "imgs")
public class Imgs {
    @Field(name = "id")
    private Integer id;
    @Field(name= "mid")
    private Integer mid;
    @Field(name="img")
    private String img;


    @Override
    public String toString() {
        return "Imgs{" +
                "id=" + id +
                ", mid=" + mid +
                ", img='" + img + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
