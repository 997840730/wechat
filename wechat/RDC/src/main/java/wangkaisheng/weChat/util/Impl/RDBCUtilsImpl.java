package wangkaisheng.weChat.util.Impl;


import wangkaisheng.weChat.dao.annotion.Table;
import wangkaisheng.weChat.util.RDBCUtils;

import java.lang.reflect.Field;

public class RDBCUtilsImpl implements RDBCUtils {
    String sel = "select * from ";
    @Override
    public <T> String select(T t){
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuffer sql = new StringBuffer(sel+t.getClass().getAnnotation(Table.class).name()+" where 1=1 ");
        for (Field f:fields) {
            f.setAccessible(true);
            if(f.isAnnotationPresent(wangkaisheng.weChat.dao.annotion.Field.class)){
                try {
                    Object o = f.get(t);
                    if(o!=null){
                        sql.append(" and "+f.getAnnotation(wangkaisheng.weChat.dao.annotion.Field.class).name()+" = ?");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return sql.toString();
    }


    String ins = "insert into ";
    @Override
    public <T> String insert(T t){
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuffer sql = new StringBuffer(ins + t.getClass().getSimpleName()+" value (null");
        for (Field f:fields) {
            f.setAccessible(true);
            if(f.isAnnotationPresent(wangkaisheng.weChat.dao.annotion.Field.class)){
                if(!"id".equals(f.getAnnotation(wangkaisheng.weChat.dao.annotion.Field.class).name())){
                    sql.append(",?");
                }
            }
        }
        sql.append(")");
        return sql.toString();
    }

    String del = "delete from ";
    @Override
    public <T> String delete(T t){
        Field[] fields = t.getClass().getDeclaredFields();
        int i = 0;
        StringBuffer sql = new StringBuffer(del+t.getClass().getSimpleName()+" where ");
        for (Field f:fields) {
            f.setAccessible(true);
            if(f.isAnnotationPresent(wangkaisheng.weChat.dao.annotion.Field.class)){
                try {
                    Object o = f.get(t);
                    if(o!=null){
                        if(i!=0){
                            sql.append(" and ");
                        }
                        sql.append(f.getName()+" = ?");
                        i++;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return sql.toString();
    }

    String upd = "update ";
    @Override
    public <T> String update(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        int i = 0;
        StringBuffer sql = new StringBuffer(upd+t.getClass().getSimpleName()+" set ");
        for (Field f:fields) {
            f.setAccessible(true);
            if(f.isAnnotationPresent(wangkaisheng.weChat.dao.annotion.Field.class)){
                if(!"id".equals(f.getAnnotation(wangkaisheng.weChat.dao.annotion.Field.class).name())){
                    try {
                        Object o = f.get(t);
                        if(o!=null){
                            if(i!=0){
                                sql.append(" , ");
                            }
                            sql.append(f.getName()).append(" = ?");
                            i++;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        sql.append(" where id = ?");
        return sql.toString();
    }


    @Override
    public <T> String vague(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuffer sql = new StringBuffer(sel+t.getClass().getAnnotation(Table.class).name()+" where 1=1 ");
        for (Field f:fields) {
            f.setAccessible(true);
            if(f.isAnnotationPresent(wangkaisheng.weChat.dao.annotion.Field.class)){
                try {
                    Object o = f.get(t);
                    if(o!=null){
                        sql.append(" and "+f.getAnnotation(wangkaisheng.weChat.dao.annotion.Field.class).name()+" like \"%?%\"");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return sql.toString();
    }

}
