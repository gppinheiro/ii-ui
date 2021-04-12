package db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Arrays;

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
    private static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
        URI dbUri = new URI(heroku_url);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }

    /**
     * Method to get DB Connection.
     * @return DB Connection.
     */
    public Connection getConn() {
        return this.conn;
    }

    public void searchPath() throws SQLException {
        PreparedStatement s = this.conn.prepareStatement("SET search_path TO ii;");
        s.executeUpdate();
    }

    public Object[][] getTransform() throws SQLException {
        PreparedStatement s = this.conn.prepareStatement("SELECT COUNT(*) FROM ii.\"Transform\";");
        ResultSet rs = s.executeQuery();
        rs.next();
        Object[][] data = new Object[rs.getInt(1)][4];

        s = this.conn.prepareStatement("SELECT \"OrderNumber\", \"from\", \"to\", quantity FROM ii.\"Transform\";");
        rs = s.executeQuery();

        int i=0;
        while (rs.next()) {
            data[i][0] = rs.getInt(1);
            data[i][1] = rs.getInt(2);
            data[i][2] = rs.getInt(3);
            data[i][3] = rs.getInt(4);
            System.out.println(Arrays.toString(data[0]));
            i++;
        }

        return data;
    }

}

