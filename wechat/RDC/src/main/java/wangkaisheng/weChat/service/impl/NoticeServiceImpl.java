package wangkaisheng.weChat.service.impl;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.*;
import wangkaisheng.weChat.service.FriendService;
import wangkaisheng.weChat.service.NoticeService;
import wangkaisheng.weChat.service.Service;
import wangkaisheng.weChat.web.controller.contants.Status;

import java.util.List;

import static wangkaisheng.weChat.service.contants.ServiceMessage.*;

/**
 * @author Administrator
 */
public class NoticeServiceImpl extends Service implements NoticeService {

    @Override
    public ServiceResult insertnotice(Notice notice) {
        if (notice.getType().equals(1)){
            Groups groups = new Groups();
            groups.setGroupId(notice.getUserid());
            groups = groupDao.findGroupByid(groups);
            notice.setUsername(groups.getGroupname());
            notice.setGid(groups.getGroupUser());
        }else {
            Friends friends = new Friends();
            friends.setFid(notice.getUserid());
            User usersById = userDao.getUsersById(friends);
            notice.setUsername(usersById.getName());
        }
        if (!noticeDao.selectnotice(notice)){
            return new ServiceResult(Status.ERROR,NOTICE_EXIST.message,null);
        }else {
            if (noticeDao.insertnotice(notice)){
                return new ServiceResult(Status.SUCCESS,NOTICE_SUCCESS.message,notice);
            }else {
                return new ServiceResult(Status.ERROR,NOTICE_ERROR.message,null);
            }
        }
    }

    @Override
    public ServiceResult findnotice(User user) {
        Notice notice = new Notice();
        notice.setGid(user.getWechatId());
        List<Notice> notices =  noticeDao.findnotice(notice);
        if (notices.isEmpty()){
            return new ServiceResult(Status.ERROR,NOTICE_EMPTY.message,null);
        }else {
            return new ServiceResult(Status.SUCCESS,NOTICE_NOEMPTY.message,notices);
        }
    }

    @Override
    public ServiceResult findnoticed(User user) {
        Noticed noticed = new Noticed();
        noticed.setUserid(user.getWechatId());
        System.out.println(noticed);
        List<Noticed> notices =  noticeDao.findnoticed(noticed);
        if (notices.isEmpty()){
            return new ServiceResult(Status.ERROR,NOTICED_EMPTY.message,null);
        }else {
            return new ServiceResult(Status.ERROR,NOTICED_NOEMPTY.message,notices);
        }
    }

    @Override
    public ServiceResult insertnoticed(Noticed noticed) {
        if (noticeDao.insertnoticed(noticed)){
            return new ServiceResult(Status.SUCCESS,NOTICED_ADD_SUCCESS.message,noticed);
        }else {
            return new ServiceResult(Status.ERROR, NOTICED_ADD_ERROR.message, null);
        }
    }

    @Override
    public ServiceResult deleteNoticeById(Notice notice) {
        if (noticeDao.deleteNotice(notice)){
            return new ServiceResult(Status.SUCCESS,NOTICE_DELETE_SUCCESS.message,null);
        }else {
            return new ServiceResult(Status.ERROR, NOTICE_DELETE_ERROR.message, null);
        }
    }

    @Override
    public ServiceResult offnoticed(Noticed noticed) {
        if (noticeDao.deleteNoticed(noticed)){
            return new ServiceResult(Status.SUCCESS,NOTICED_DELETE_SUCCESS.message,null);
        }else {
            return new ServiceResult(Status.ERROR,NOTICED_DELETE_ERROR.message,null);
        }
    }
}
