package wangkaisheng.weChat.service;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Notice;
import wangkaisheng.weChat.po.Noticed;
import wangkaisheng.weChat.po.User;

/**
 * @author Administrator
 */
public interface NoticeService {
    ServiceResult insertnotice(Notice notice);

    ServiceResult findnotice(User user);

    ServiceResult findnoticed(User user);

    ServiceResult insertnoticed(Noticed noticed);

    ServiceResult deleteNoticeById(Notice notice);

    ServiceResult offnoticed(Noticed noticed);
}
