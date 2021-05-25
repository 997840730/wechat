package wangkaisheng.weChat.util.Pool;

import java.sql.Connection;

/**
 * @author Administrator
 */
public class PooledConnection {

    private Connection connection;
    private boolean busy;

    public PooledConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void release() {

    }
}
