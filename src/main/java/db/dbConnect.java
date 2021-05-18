package db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Arrays;

/**
 * Class dbConnect is used to: communicate with Heroku DataBase and create a Prepared Statements to send to the DB.
 * Date: March-3-2021.
 * @author Group A5_C.
 */
public class dbConnect {
    /**
     * Store Heroku URI: User + Password + Host + Port + Database.
     * Private - Nobody needs to know what URI we are using.
     * Static and Final - Never changes.
     */
    private static final String heroku_url="postgres://vkgvpttoqwwjti:241f4c5e49e1ff17e84ecab4bbe8c63ead0a80d1684405a3c5fa8542f36de5c0@ec2-54-74-35-87.eu-west-1.compute.amazonaws.com:5432/d2j57fljq86oa0";
    /**
     * Store DB Connection.
     * Private - Nobody needs to know the db connection.
     */
    private Connection conn = null;

    /**
     * Constructor that creates connection with DataBase.
     */
    public dbConnect() {
        try {
            this.conn = getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to make first connection.
     * @return DB Connection.
     * @throws URISyntaxException if occurs an URI error in syntax.
     * @throws SQLException if occurs an error in DB.
     */
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(heroku_url);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public Object[][] getTransform() throws SQLException {
        PreparedStatement s = this.conn.prepareStatement("SELECT COUNT(*) FROM ii.\"Transform\";");
        ResultSet rs = s.executeQuery();
        rs.next();
        Object[][] data = new Object[rs.getInt(1)][8];

        s = this.conn.prepareStatement("SELECT \"OrderNumber\", \"from\", \"to\", quantity, \"MaxDelay\", penalty, \"timeMES\", \"TransformTimeExcepted\" FROM ii.\"Transform\";");
        rs = s.executeQuery();

        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getInt(1);
            data[i][1] = rs.getInt(2);
            data[i][2] = rs.getInt(3);
            data[i][3] = rs.getInt(4);
            data[i][4] = rs.getInt(5);
            data[i][5] = rs.getInt(6);
            data[i][6] = rs.getInt(7);
            data[i][7] = rs.getInt(8);
            i++;
        }

        return data;
    }

    public Object[][] getElapseTransform() throws SQLException {
        PreparedStatement s = this.conn.prepareStatement("SELECT COUNT(*) FROM ii.\"ElapseTransform\";");
        ResultSet rs = s.executeQuery();
        rs.next();
        Object[][] data = new Object[rs.getInt(1)][9];

        s = this.conn.prepareStatement("SELECT \"OrderNumber\", \"from\", \"to\", quantity, \"MaxDelay\", penalty, \"timeMES\", side, st FROM ii.\"ElapseTransform\";");
        rs = s.executeQuery();

        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getInt(1);
            data[i][1] = rs.getInt(2);
            data[i][2] = rs.getInt(3);
            data[i][3] = rs.getInt(4);
            data[i][4] = rs.getInt(5);
            data[i][5] = rs.getInt(6);
            data[i][6] = rs.getInt(7);
            data[i][7] = rs.getString(8);
            data[i][8] = rs.getInt(9);
            i++;
        }

        return data;
    }

    public  Object[][] getEndTransform() throws SQLException {
        PreparedStatement s = this.conn.prepareStatement("SELECT COUNT(*) FROM ii.\"EndTransform\";");
        ResultSet rs = s.executeQuery();
        rs.next();
        Object[][] data = new Object[rs.getInt(1)][12];

        s = this.conn.prepareStatement("SELECT \"OrderNumber\", \"from\", \"to\", quantity, \"MaxDelay\", \"InitialPenalty\", \"timeMES\", \"TransformSide\", st, et, \"TransformTime\", penalty FROM ii.\"EndTransform\";");
        rs = s.executeQuery();

        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getInt(1);
            data[i][1] = rs.getInt(2);
            data[i][2] = rs.getInt(3);
            data[i][3] = rs.getInt(4);
            data[i][4] = rs.getInt(5);
            data[i][5] = rs.getInt(6);
            data[i][6] = rs.getInt(7);
            data[i][7] = rs.getString(8);
            data[i][8] = rs.getInt(9);
            data[i][9] = rs.getInt(10);
            data[i][10] = rs.getInt(11);
            data[i][11] = rs.getInt(12);
            i++;
        }

        return data;
    }

    public Object[][] getCurrentStores() throws SQLException {
        PreparedStatement s = this.conn.prepareStatement("SELECT COUNT(*) FROM ii.\"CurrentStores\";");
        ResultSet rs = s.executeQuery();
        rs.next();
        Object[][] data = new Object[rs.getInt(1)][2];

        s = this.conn.prepareStatement("SELECT \"type\", quantity FROM ii.\"CurrentStores\";");
        rs = s.executeQuery();

        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getInt(1);
            data[i][1] = rs.getInt(2);
            i++;
        }

        return data;
    }

    public Object[][] getUnloaded() throws SQLException {
        PreparedStatement s = this.conn.prepareStatement("SELECT COUNT(*) FROM ii.\"EndUnload\";");
        ResultSet rs = s.executeQuery();
        rs.next();
        Object[][] data = new Object[rs.getInt(1)][4];

        s = this.conn.prepareStatement("SELECT \"OrderNumber\", \"type\", \"destination\", \"quantity\" FROM ii.\"EndUnload\";");
        rs = s.executeQuery();

        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getInt(1);
            data[i][1] = rs.getInt(2);
            data[i][2] = rs.getInt(3);
            data[i][3] = rs.getInt(4);
            i++;
        }

        return data;
    }

    public Object[][] getUnload() throws  SQLException {
        PreparedStatement s = this.conn.prepareStatement("SELECT COUNT(*) FROM ii.\"Unload\";");
        ResultSet rs = s.executeQuery();
        rs.next();
        Object[][] data = new Object[rs.getInt(1)][4];

        s = this.conn.prepareStatement("SELECT \"OrderNumber\", \"type\", \"destination\", \"quantity\"  FROM ii.\"Unload\";");
        rs = s.executeQuery();

        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getInt(1);
            data[i][1] = rs.getInt(2);
            data[i][2] = rs.getInt(3);
            data[i][3] = rs.getInt(4);
            i++;
        }

        return data;
    }

    public Object[][] getMaquinas() throws SQLException {
        Object[][] data = new Object[8][10];

        PreparedStatement s = this.conn.prepareStatement("SELECT  \"machine\", \"t1\", \"t2\", \"t3\", \"t4\", \"t5\", \"t6\", \"t7\", \"t8\", \"total\" FROM ii.\"MachinesStatistic\";");
        ResultSet rs = s.executeQuery();

        return getObjects(rs, data);
    }

    public Object[][] getTemposMaquinas() throws SQLException {
        Object[][] data = new Object[8][10];

        PreparedStatement s = this.conn.prepareStatement("SELECT  \"machine\", \"t1\", \"t2\", \"t3\", \"t4\", \"t5\", \"t6\", \"t7\", \"t8\", \"total\" FROM ii.\"MachinesTimes\";");
        ResultSet rs = s.executeQuery();

        return getObjects(rs, data);
    }

    private Object[][] getObjects(ResultSet rs, Object[][] data) throws SQLException {
        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getString(1);
            data[i][1] = rs.getInt(2);
            data[i][2] = rs.getInt(3);
            data[i][3] = rs.getInt(4);
            data[i][4] = rs.getInt(5);
            data[i][5] = rs.getInt(6);
            data[i][6] = rs.getInt(7);
            data[i][7] = rs.getInt(8);
            data[i][8] = rs.getInt(9);
            data[i][9] = rs.getInt(10);
            i++;
        }

        return data;
    }

    public Object[][] getPusher() throws SQLException{
        Object[][] data = new Object[3][11];

        PreparedStatement s = this.conn.prepareStatement("SELECT  \"pusher\", \"p1\", \"p2\", \"p3\", \"p4\", \"p5\", \"p6\", \"p7\", \"p8\", \"p9\", \"total\" FROM ii.\"PushersStatistic\";");
        ResultSet rs = s.executeQuery();

        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getString(1);
            data[i][1] = rs.getInt(2);
            data[i][2] = rs.getInt(3);
            data[i][3] = rs.getInt(4);
            data[i][4] = rs.getInt(5);
            data[i][5] = rs.getInt(6);
            data[i][6] = rs.getInt(7);
            data[i][7] = rs.getInt(8);
            data[i][8] = rs.getInt(9);
            data[i][9] = rs.getInt(10);
            data[i][10] = rs.getInt(11);
            i++;
        }

        return data;
    }

}

