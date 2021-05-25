package wangkaisheng.weChat.service;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Imgs;
import wangkaisheng.weChat.po.Moments;
import wangkaisheng.weChat.po.User;

import java.util.List;

public interface MomentService {
    ServiceResult addmoment(Moments moments);

    ServiceResult insertImgs(Imgs imgs);

    List<Moments> listmoments(List<User> friends);

    List<Moments> listmoment(List<Moments> moments);
}
