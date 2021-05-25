package wangkaisheng.weChat.dao;

import wangkaisheng.weChat.po.Friends;
import wangkaisheng.weChat.po.Imgs;
import wangkaisheng.weChat.po.Moments;

import java.util.List;

public interface MomentDao {
    boolean addmoment(Moments moments);

    Moments findmoment(Moments moments);

    boolean isertImgs(Imgs imgs);

    List<Moments> listmoments(List<Integer> integers);

    Imgs listimgs(Imgs img);
}
