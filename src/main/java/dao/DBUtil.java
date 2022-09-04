//DBUtil class will be updated later
package dao;
import java.util.ResourceBundle;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DBUtil {
    private final static ResourceBundle bundle = ResourceBundle.getBundle("resources/db");
    private final static String driver = bundle.getString("driver");
    private final static String url = bundle.getString("url");
    private final static String user = bundle.getString("user");
    private final static String password = bundle.getString("password");

    private DBUtil(){}
    //Register the driver
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Get the database connection object
     * @return conn connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Convert string date to sql date types
     * @param date
     * @return
     */
    public static java.sql.Date toDate(String date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(d.getTime());
        return sqlDate;
    }

    /**
     * Release resources
     * @param conn connection object
     * @param ps statement object
     * @param rs resultSet object
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
