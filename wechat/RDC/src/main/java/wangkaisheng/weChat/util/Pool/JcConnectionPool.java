package wangkaisheng.weChat.util.Pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Administrator
 */
public class JcConnectionPool {
    private static String url;
    private static String username;
    private static String password;
    private static String driver;
    private static int initialSize;
    private static int maxActive;
    private static int maxWait;
    private List<PooledConnection> connectionPool= new ArrayList<PooledConnection>();

    @Override
    public String toString() {
        return "JcConnectionPool{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driver='" + driver + '\'' +
                ", initialSize=" + initialSize +
                ", maxActive=" + maxActive +
                ", maxWait=" + maxWait +
                '}';
    }

    static {
        try {
            Properties pro = new Properties();
            InputStream is = JcConnectionPool.class.getClassLoader().getResourceAsStream("MySQL.properties");
            pro.load(is);
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            initialSize = Integer.parseInt(pro.getProperty("initialSize"));
            maxActive = Integer.parseInt(pro.getProperty("maxActive"));
            maxWait = Integer.parseInt(pro.getProperty("maxWait"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public JcConnectionPool() {
        initPool();
    }

    private void initPool() {
        //加载驱动
        try {
            Class.forName(driver);
            //初始化initSize个连接到列表中
            for(int i=0;i<initialSize;i++) {
                addConnToPool();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void addConnToPool() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username,password);
            PooledConnection pooledConnection = createPooledConnection(connection);
            connectionPool.add(pooledConnection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private PooledConnection createPooledConnection(Connection connection) {
        PooledConnection pooledConnection = new PooledConnection(connection);
        pooledConnection.setBusy(false);
        return pooledConnection;
    }

    public synchronized PooledConnection getConnection() {
        int size = connectionPool.size();
        if(size<maxActive) {
            //现有链接不超过最大值时
            for(PooledConnection connection:connectionPool) {

                if(!connection.isBusy()) {
                    //现有链接不繁忙
                    return connection;
                    //返回连接
                }
            }
            //当没有空闲链接时,加入一个新的链接并返回；
            addConnToPool();
            return connectionPool.get(size+1);
        }else {
            //当数量达到最大值时
            PooledConnection connection = null;
            while(connection==null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //监听等待空闲连接
                for(PooledConnection c:connectionPool) {
                    if(!c.isBusy()) {
                        //现有链接不繁忙
                        connection=c;
                        //返回连接
                        break;
                    }
                }
            }
            return connection;
        }
    }

    public synchronized void release(PooledConnection connection) {
        connection.setBusy(false);
    }
    /**
     * 关闭连接池，释放资源
     */
    public void close(ResultSet res, Statement stmt, Connection conn) {
        if( res != null){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if( stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if( conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
