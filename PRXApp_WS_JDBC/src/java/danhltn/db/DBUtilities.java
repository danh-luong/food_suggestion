/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author leagu
 */
public class DBUtilities {
    
    public static Connection makeConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=PRX_Project;instanceName=SE130015/SQLEXPRESS", "sa", "sa123456");
        return conn;
    }

}
