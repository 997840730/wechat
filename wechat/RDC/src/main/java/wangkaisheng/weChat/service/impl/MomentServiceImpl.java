package wangkaisheng.weChat.service.impl;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Imgs;
import wangkaisheng.weChat.po.Moments;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.MomentService;
import wangkaisheng.weChat.service.Service;
import wangkaisheng.weChat.service.contants.ServiceMessage;
import wangkaisheng.weChat.web.controller.contants.Status;

import java.util.LinkedList;
import java.util.List;

public class MomentServiceImpl extends Service implements MomentService {
    @Override
    public ServiceResult addmoment(Moments moments) {
        if (momentDao.addmoment(moments)){
            Moments m = new Moments();
            m.setUuid(moments.getUuid());
            moments =  momentDao.findmoment(m);
            return new ServiceResult(Status.SUCCESS, ServiceMessage.MOMENT_ADD_SUCCESS.message,moments);
        }else {
            return new ServiceResult(Status.ERROR,ServiceMessage.MOMENT_ADD_ERROR.message, null);
        }
    }

    @Override
    public ServiceResult insertImgs(Imgs imgs) {
        if (momentDao.isertImgs(imgs)){
            return new ServiceResult(Status.SUCCESS,ServiceMessage.IMGS_ADD_SUCCESS.message,null);
        }else {
            return new ServiceResult(Status.ERROR,ServiceMessage.IMGS_ADD_ERROR.message,null);
        }
    }

    @Override
    public List<Moments> listmoments(List<User> friends) {
        List<Integer> ids = new LinkedList<>();
        for (User user:friends){
            ids.add(user.getWechatId());
        }
        List<Moments> list = momentDao.listmoments(ids);
        if (list.isEmpty()){
            return null;
        }else {
            return list;
        }
    }

    @Override
    public List<Moments> listmoment(List<Moments> moments) {
        if (moments==null){
            return null;
        }
        for (int i = 0 ; i<moments.size();i++){
            Imgs img = new Imgs();
            img.setMid(moments.get(i).getId());
            img = momentDao.listimgs(img);
            if (img!=null){
                List<String> list = new LinkedList<>();
                list.add(img.getImg());
                moments.get(i).setImgs(list);
            }
        }
        return moments;
    }
}
