package wangkaisheng.weChat.dao;

import wangkaisheng.weChat.po.BlackList;

import java.util.List;

public interface BlackDao {

    List<BlackList> getBlackList(BlackList blackList);

    boolean addblacks(BlackList blackList);

    boolean deleteblack(BlackList blackList);
}
