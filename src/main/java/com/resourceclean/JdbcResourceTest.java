package com.resourceclean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcResourceTest {
    
    // Test 1. Statis Database Connection String
    static  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String JDBC_URL = "";
    static String JDBC_ID = "";
    static String JDBC_PASS = "";

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_ID, JDBC_PASS);
    }

    // Test 2. Uncleaned Resources

    public void selectAndNotClosed() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareCall("select 1");
            rs = pstmt.executeQuery();

            while(rs.next()) {
                String value = rs.getString(1);
                System.out.println(value);
            }
        } finally {
            // Uncleaned !!!
        }
    }

    // Test 3. Partial Resource Leak. 
    public void selectAndNotClosedPartial() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareCall("select 1");
            rs = pstmt.executeQuery();

            while(rs.next()) {
                String value = rs.getString(1);
                System.out.println(value);
            }
        } finally {
            if(rs != null) try { rs.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }

    // Test 4. Clean resources with mis ordering. 
    public void selectAndNotClosedMisOrder() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareCall("select 1");
            rs = pstmt.executeQuery();

            while(rs.next()) {
                String value = rs.getString(1);
                System.out.println(value);
            }
        } finally {
            if(pstmt != null) try { pstmt.close(); } catch (Exception e) {e.printStackTrace();}
            if(rs != null) try { rs.close(); } catch (Exception e) {e.printStackTrace();}
        }
    }


}