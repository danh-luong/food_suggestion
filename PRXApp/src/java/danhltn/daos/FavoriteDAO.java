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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leagu
 */
public class FavoriteDAO {
    
    public static void insertFavoriteOfUser(int userId, int foodId) throws Exception {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into Favorite(userId, foodId) values (?, ?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setInt(2, foodId);
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
    
    public static boolean checkFavoriteOfUser(int userId, int foodId) throws Exception {
        boolean check = false;
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select favoriteId from Favorite where userId = ? and foodId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setInt(2, foodId);
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
    
    public static void removeFavoriteOfUser(int userId, int foodId) throws Exception {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "delete from Favorite where userId = ? and foodId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setInt(2, foodId);
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
    
    public static List<Integer> getFavoriteOfUser(int userId) throws Exception {
        List<Integer> list = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select foodId from Favorite where userId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(rs.getInt("foodId"));
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
        return list;
    }
}
