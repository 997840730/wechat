package wangkaisheng.weChat.util;

import java.util.List;

/**
 * @author Administrator
 */
public interface BaseUtils {

   <T> List<T> query(String sql, Object[] paramsValue, Class<T> clazz);

   <T> boolean addAndcancelAndmodify(String sql,Object[] paramsValue,Class<T> clazz);
}