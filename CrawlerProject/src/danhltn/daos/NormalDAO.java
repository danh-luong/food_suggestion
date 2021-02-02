/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.daos;

import danhltn.db.DBUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author leagu
 */
public class NormalDAO {

    public static void insertCategory(String categoryName) throws Exception {
        boolean checkedCategory = checkCategory(categoryName);

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null && !checkedCategory) {
                String sql = "Insert into Category(name) values (?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, categoryName);
                pst.executeUpdate();
            }
        } finally {

            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    private static boolean checkCategory(String categoryName) throws Exception {
        boolean check = false;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Select name from Category where name like ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + categoryName + "%");
                rs = pst.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public static int getIdCategory(String categoryName) throws Exception {
        String result = "";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Select categoryId from Category where name = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, categoryName);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("categoryId");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return Integer.parseInt(result);
    }

    public static int getIdOfLastItemInFood() throws Exception {
        String result = "";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Select Max(foodId) as 'foodId' from Food";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("foodId");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return Integer.parseInt(result);
    }

    public static void insertIntoCategoryOfFood(int foodId, int categoryId) throws Exception {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "Insert into CategoryOfFood(foodId, categoryId) values (?, ?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, foodId);
                pst.setInt(2, categoryId);
                pst.execute();
            }
        } finally {
            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
}
