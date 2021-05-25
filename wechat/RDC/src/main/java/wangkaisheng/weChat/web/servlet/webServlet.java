package wangkaisheng.weChat.web.servlet;

import wangkaisheng.weChat.dao.MemberDao;
import wangkaisheng.weChat.dao.MessageDao;
import wangkaisheng.weChat.dao.UserDao;
import wangkaisheng.weChat.dao.impl.MemberDaoImpl;
import wangkaisheng.weChat.dao.impl.MessageDaoImpl;
import wangkaisheng.weChat.dao.impl.UserDaoImpl;
import wangkaisheng.weChat.factory.ProxyFactory;
import wangkaisheng.weChat.po.*;
import wangkaisheng.weChat.util.BeanUtils;
import wangkaisheng.weChat.web.controller.provider.Provider;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Administrator
 */
@ServerEndpoint(value = "/websocket/{user}", configurator = HttpSessionConfigurator.class)
public class webServlet {

        private static final UserDao userDao = (UserDao) new ProxyFactory().getProxyInstance(new UserDaoImpl());
        private static final MemberDao memberDao = (MemberDao) new ProxyFactory().getProxyInstance(new MemberDaoImpl());
        private static final MessageDao messageDao = (MessageDao) new ProxyFactory().getProxyInstance(new MessageDaoImpl());
        private  Date date = new Date();
        private static int onlineCount = 0;
        private User user;
        private Friends friends = new Friends();;
        //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识

        private static final ConcurrentHashMap<String,webServlet> webSocketSet = new ConcurrentHashMap<String ,webServlet>();

        //与某个客户端的连接会话，需要通过它来给客户端发送数据

        private Session session;

        private final String ALREADY_ONLINE = "您已经在一个新的浏览器上登陆，系统将自动断开与本页面的连接，页面将不可用";


        /**
         * 连接建立成功调用的方法
         * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
         */

        @OnOpen
        public void onOpen(Session session, @PathParam("user") String userId, EndpointConfig config) throws IOException {
            this.session = session;
            webServlet server = webSocketSet.get(userId);
            if(server!=null&& server.session != null && server.session.isOpen()){
                server.session.getAsyncRemote().sendText(ALREADY_ONLINE);
                server.session.close();
                return;
            }
            server = new webServlet();
            //装载用户数据，作为缓存
            server.session = session;
            server.friends.setFid(Integer.valueOf(userId));
            server.user = userDao.getUsersById(server.friends);
            System.out.println(server.user);
            webSocketSet.put(userId,server);
            addOnlineCount();
            System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        }

        /**
         * 连接关闭调用的方法
         */
        @OnClose
        public void onClose() {
            webSocketSet.remove(this);
            //从set中删除
            subOnlineCount();           //在线数减1
            System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        }

        /**
         * 收到客户端消息后调用的方法
         *
         * @param message 客户端发送过来的消息
         * @param session 可选的参数
         */
        @OnMessage
        public void onMessage(String message, Session session){
            Message mess = BeanUtils.toMessage(message);
            Date date =new Date();
            Time time = new Time(date.getTime());
            mess.setTime(time);
            mess.setDate(new java.sql.Date(date.getTime()));
            webServlet you;
            Friends f = new Friends();
            f.setFid(mess.getSenderId());
            User usersById = userDao.getUsersById(f);
            if ("private".equals(mess.getType())){
                you = webSocketSet.get(mess.getChatId().toString());
                if (you==null){
                    mess.setReaded(0);
                    you = webSocketSet.get(mess.getSenderId().toString());
                    if (you!=null){
                        you.sendMessage("对方不在线！");
                    }else {
                        System.out.println("双方用户均掉线！");
                    }
                }else {
                    mess.setReaded(1);
                    you.sendMessage(usersById.getName()+":"+mess.getContent());
                }
                messagekeep(mess);
            }else if ("public".equals(mess.getType())){
                mess.setReaded(2);
                Member member = new Member();
                member.setGid(mess.getChatId());
                List<Member> members = memberDao.getMember(member);
                messagekeep(mess);
                mess = messageDao.findmessage(mess);
                if (mess==null){
                    System.out.println("???");
                    return;
                }
                for (Member m:members){
                    if (!m.getUid().equals(mess.getSenderId())){
                        you=webSocketSet.get(m.getUid().toString());
                        if (you!=null){
                            you.sendMessage(usersById.getName()+":"+mess.getContent());
                        }else {
                            Readed read = new Readed();
                            read.setMid(mess.getId());
                            read.setUid(m.getUid());
                            read.setGid(mess.getChatId());
                            messageDao.insertRead(read);
                        }
                    }
                }
            }else {
                System.out.println("连接失败！");
            }
        }

        private void messagekeep(Message message){
            if (messageDao.insertMessage(message)){
                System.out.println("保存成功");
            }else {
                System.out.println("保存失败");
            }
        }

        /**
         * 发生错误时调用
         *
         * @param session
         * @param error
         */
        @OnError
        public void onError(Session session, Throwable error) {
            System.out.println("发生错误！");
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("已关闭连接！");
            error.printStackTrace();
        }

        /**
         * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
         *
         * @param message
         * @throws IOException
         */
        public void sendMessage(String message){
            this.session.getAsyncRemote().sendText(message);
        }

        public static synchronized int getOnlineCount() {
            return onlineCount;
        }

        public static synchronized void addOnlineCount() {
            webServlet.onlineCount++;
        }

        public static synchronized void subOnlineCount() {
            webServlet.onlineCount--;
        }

}
