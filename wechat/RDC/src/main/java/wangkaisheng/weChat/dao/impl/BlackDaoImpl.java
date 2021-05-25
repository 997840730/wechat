package wangkaisheng.weChat.dao.impl;

import wangkaisheng.weChat.dao.BlackDao;
import wangkaisheng.weChat.po.BlackList;
import wangkaisheng.weChat.util.Impl.BaseUtilesImpl;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.util.BeanUtils.toList;

/**
 * @author Administrator
 */
public class BlackDaoImpl extends BaseUtilesImpl implements BlackDao {
    private List<Object> list = new LinkedList<>();
    @Override
    public List<BlackList> getBlackList(BlackList blackList) {
        list.clear();
        String sql = super.select(blackList);
        toList(blackList,list);
        return super.query(sql, list.toArray(), BlackList.class);
    }

    @Override
    public boolean addblacks(BlackList blackList) {
        list.clear();
        String sql = super.insert(blackList);
        toList(blackList,list);
        return super.addAndcancelAndmodify(sql,list.toArray(),BlackList.class);
    }


    @Override
    public boolean deleteblack(BlackList blackList) {
        list.clear();
        String sql = super.delete(blackList);
        toList(blackList,list);
        return addAndcancelAndmodify(sql,list.toArray(),BlackList.class);
    }
}
