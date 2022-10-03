package dao;

import java.sql.Connection;

public class DB {

    // Benz's DB URL --- jdbc:mysql://localhost:3306, db, root, root,
    // com.mysql.cj.jdbc.Driver
    protected String URL = "jdbc:mysql://localhost:3306/";
    protected String db = "db";
    protected String dbuser = "root";
    protected String dbpass = "rootroot1";
    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected Connection conn;

}
