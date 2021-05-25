package wangkaisheng.weChat.util.Impl;


import wangkaisheng.weChat.util.BaseUtils;
import wangkaisheng.weChat.util.BeanUtils;
import wangkaisheng.weChat.util.Pool.JcConnectionPool;
import wangkaisheng.weChat.util.Pool.PooledConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Administrator
 */
public class BaseUtilesImpl extends RDBCUtilsImpl implements BaseUtils {
    private Connection conn = null;
    private PreparedStatement stmt=null;
    private ResultSet rs = null;
    private static final JcConnectionPool jcConnectionPool = new JcConnectionPool();

    @Override
    public <T> List<T> query(String sql, Object[] paramsValue, Class<T> clazz) {
        PooledConnection connection = jcConnectionPool.getConnection();
        conn = connection.getConnection();
        List<T> list = new ArrayList<T>();
        try {
            T t = null;
            stmt = conn.prepareStatement(sql);
            if (paramsValue != null && paramsValue.length > 0) {
                for (int i = 0; i < paramsValue.length; i++) {
                    stmt.setObject(i + 1, paramsValue[i]);
                }
            }
            rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            List<String> columNames = new LinkedList<>();
            for (int i = 0; i < columnCount; i++) {
                String columnName = rsmd.getColumnName(i + 1);
                columNames.add(columnName);
            }
            while (rs.next()) {
                t = clazz.getDeclaredConstructor().newInstance();
                for (String columnName:columNames) {
                    Object value = rs.getObject(columnName);
                    BeanUtils.copyProperty(t,columnName,value);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            over(stmt,rs);
            jcConnectionPool.release(connection);
        }
        return list;
    }

    @Override
    public <T> boolean addAndcancelAndmodify(String sql, Object[] paramsValue, Class<T> clazz) {
        PooledConnection connection = jcConnectionPool.getConnection();
        conn = connection.getConnection();
        int i=0;
        try {
            T t = null;
            stmt = conn.prepareStatement(sql);
            if (paramsValue != null && paramsValue.length > 0) {
                for (i = 0; i < paramsValue.length; i++) {
                    stmt.setObject(i + 1, paramsValue[i]);
                }
            }
            // 4. 执行查询
            i = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jcConnectionPool.release(connection);
        }
        return i != 0;
    }

    private void over(PreparedStatement ps, ResultSet rs){
        try {
            rs.close();
        } catch (SQLException throwables) {
            System.out.println("PreparedStatement归还失败!");
            throwables.printStackTrace();
        }
        try {
            ps.close();
        } catch (SQLException throwables) {
            System.out.println("ResultSet归还失败!");
            throwables.printStackTrace();
        }
    }

}
