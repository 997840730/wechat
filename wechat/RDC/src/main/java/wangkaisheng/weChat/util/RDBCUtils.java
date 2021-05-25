package wangkaisheng.weChat.util;

public interface RDBCUtils {
    <T> String select(T t);
    <T> String insert(T t);
    <T> String delete(T t);
    <T> String update(T t);
    <T> String vague(T t);
}
