package wangkaisheng.weChat.dao;

import wangkaisheng.weChat.po.Notice;
import wangkaisheng.weChat.po.Noticed;

import java.util.List;

public interface NoticeDao {
    boolean selectnotice(Notice notice);

    boolean insertnotice(Notice notice);

    boolean deleteNotice(Notice notice);

    boolean insertnoticed(Noticed noticed);

    boolean deleteNoticed(Noticed noticed);

    List<Notice> findnotice(Notice notice);

    List<Noticed> findnoticed(Noticed notice);
}
