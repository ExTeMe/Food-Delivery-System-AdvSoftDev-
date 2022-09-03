package dao;

import java.sql.Connection;
public class DB {

    // Benz's DB URL --- jdbc:mysql://localhost:3306, db, root, root, com.mysql.cj.jdbc.Driver
    protected String URL = "jdbc:mysql://localhost:3306";
    protected String db = "db"; // Please Use This DB Name
    protected String dbuser = "root"; // Please Use This DB Username here
    protected String dbpass = "root"; // Please Use This DB Password here
    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected Connection conn;

}
