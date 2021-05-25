package wangkaisheng.weChat.service;

import wangkaisheng.weChat.dao.*;
import wangkaisheng.weChat.dao.impl.*;
import wangkaisheng.weChat.factory.ProxyFactory;

/**
 * @author Administrator
 * @description 所有service类都继承此类，调用该类中的dao层代理对象
 */
public class Service {
    protected static final UserDao userDao = (UserDao) new ProxyFactory().getProxyInstance(new UserDaoImpl());
    protected static final FriendDao friendDao = (FriendDao) new ProxyFactory().getProxyInstance(new FriendDaoImpl());
    protected static final MessageDao messageDao = (MessageDao) new ProxyFactory().getProxyInstance(new MessageDaoImpl());
    protected static final BlackDao blackDao  = (BlackDao) new ProxyFactory().getProxyInstance(new BlackDaoImpl());
    protected static final GroupDao groupDao = (GroupDao) new ProxyFactory().getProxyInstance(new GroupDaoImpl());
    protected static final MemberDao memberDao = (MemberDao) new ProxyFactory().getProxyInstance(new MemberDaoImpl());
    protected static final MomentDao momentDao = (MomentDao) new ProxyFactory().getProxyInstance(new MomentDaoImpl());
    protected static final NoticeDao noticeDao = (NoticeDao) new ProxyFactory().getProxyInstance(new NoticeDaoImpl());

}
