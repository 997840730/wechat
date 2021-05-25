package wangkaisheng.weChat.dao.impl;

import wangkaisheng.weChat.dao.NoticeDao;
import wangkaisheng.weChat.po.Notice;
import wangkaisheng.weChat.po.Noticed;
import wangkaisheng.weChat.util.Impl.BaseUtilesImpl;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.util.BeanUtils.toList;

/**
 * @author Administrator
 */
public class NoticeDaoImpl extends BaseUtilesImpl implements NoticeDao {
    private List<Object> list = new LinkedList<>();
    @Override
    public boolean selectnotice(Notice notice) {
        list.clear();
        toList(notice,list);
        String sql = super.select(notice);
        return super.query(sql,list.toArray(),Notice.class).isEmpty();
    }

    @Override
    public boolean insertnotice(Notice notice) {
        list.clear();
        toList(notice,list);
        String sql = super.insert(notice);
        return super.addAndcancelAndmodify(sql,list.toArray(),Notice.class);
    }

    @Override
    public boolean deleteNotice(Notice notice) {
        list.clear();
        String sql = super.delete(notice);
        toList(notice,list);
        return super.addAndcancelAndmodify(sql,list.toArray(),Notice.class);
    }

    @Override
    public boolean insertnoticed(Noticed noticed) {
        list.clear();
        String sql = super.insert(noticed);
        toList(noticed,list);
        return super.addAndcancelAndmodify(sql,list.toArray(),Noticed.class);
    }

    @Override
    public boolean deleteNoticed(Noticed noticed) {
        list.clear();
        String sql = super.delete(noticed);
        toList(noticed,list);
        return super.addAndcancelAndmodify(sql,list.toArray(),Notice.class);
    }

    @Override
    public List<Notice> findnotice(Notice notice) {
        list.clear();
        String sql = super.select(notice);
        toList(notice,list);
        return super.query(sql,list.toArray(),Notice.class);
    }

    @Override
    public List<Noticed> findnoticed(Noticed noticed) {
        list.clear();
        String sql = super.select(noticed);
        toList(noticed,list);
        System.out.println(sql);
        System.out.println(list);
        return super.query(sql,list.toArray(),Noticed.class);
    }

}
