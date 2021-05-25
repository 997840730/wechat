package wangkaisheng.weChat.dao.impl;

import wangkaisheng.weChat.dao.MomentDao;
import wangkaisheng.weChat.po.Friends;
import wangkaisheng.weChat.po.Imgs;
import wangkaisheng.weChat.po.Moments;
import wangkaisheng.weChat.util.Impl.BaseUtilesImpl;
import wangkaisheng.weChat.util.UUIDutils;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.util.BeanUtils.toList;

public class MomentDaoImpl extends BaseUtilesImpl implements MomentDao {
    List<Object> list = new LinkedList<>();
    @Override
    public boolean addmoment(Moments moments) {
        list.clear();
        String sql = super.insert(moments);
        toList(moments,list);
        if (moments.getTalk()==null){
            String sql1 = "insert into moments VALUE(NULL,"+list.get(0).toString()+",NULL,\""+moments.getUuid()+"\")";
            return addAndcancelAndmodify(sql1,null,Moments.class);
        }else {
            return addAndcancelAndmodify(sql,list.toArray(),Moments.class);
        }
    }

    @Override
    public Moments findmoment(Moments moments) {
        list.clear();
        String sql = super.select(moments);
        toList(moments,list);
        return query(sql,list.toArray(),Moments.class).get(0);
    }

    @Override
    public boolean isertImgs(Imgs imgs) {
        list.clear();
        String sql = super.insert(imgs);
        toList(imgs,list);
        return addAndcancelAndmodify(sql,list.toArray(),Imgs.class);
    }

    @Override
    public List<Moments> listmoments(List<Integer> integers) {
        if (integers.isEmpty()){
            return null;
        }
        StringBuffer sql = new StringBuffer("select * from moments where uid = "+integers.get(0));
        for (int i = 1;i<integers.size();i++){
            sql.append(" or uid = "+integers.get(i));
        }
        System.out.println(sql);
        return query(sql.toString(),null, Moments.class);
    }

    @Override
    public Imgs listimgs(Imgs img) {
        list.clear();
        String sql = super.select(img);
        toList(img,list);
        List<Imgs> query = query(sql, list.toArray(), Imgs.class);
        if (query.isEmpty()){
            return null;
        }else {
            return query.get(0);
        }
    }

}
