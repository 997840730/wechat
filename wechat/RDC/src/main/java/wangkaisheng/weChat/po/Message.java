package wangkaisheng.weChat.po;

import wangkaisheng.weChat.dao.annotion.Field;
import wangkaisheng.weChat.dao.annotion.Table;

import java.sql.Date;
import java.sql.Time;


@Table(name = "message")
public class Message{
    @Field(name="id")
    private Integer id;
    @Field(name = "senderId")
    private Integer senderId;
    @Field(name = "chatId")
    private Integer chatId;
    @Field(name = "content")
    private String content;
    @Field(name = "type")
    private String type;
    @Field(name = "date")
    private Date date;
    @Field(name = "time")
    private Time time;
    @Field(name = "readed")
    private Integer readed;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", chatId=" + chatId +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", readed=" + readed +
                '}';
    }

    public Integer getReaded() {
        return readed;
    }

    public void setReaded(Integer readed) {
        this.readed = readed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Message(Integer senderId, Integer chatId, String content, String type) {
        this.senderId = senderId;
        this.chatId = chatId;
        this.content = content;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Message() {
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
