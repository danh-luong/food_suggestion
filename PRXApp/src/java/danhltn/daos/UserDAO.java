/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.daos;

import danhltn.db.DBUtilities;
import danhltn.dtos.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author leagu
 */
public class UserDAO implements Serializable {

    public static UserDTO checkLogin(String email, String password) throws Exception {
        UserDTO user = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select userId, firstName, lastName, year, sex, email from  Users where email = ? and password = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    user = new UserDTO();
                    user.setUserId(rs.getInt("userId"));
                    user.setFirstname(rs.getString("firstName"));
                    user.setLastname(rs.getString("lastName"));
                    user.setSex(rs.getString("sex"));
                    user.setYear(rs.getInt("year"));
                    user.setEmail(rs.getString("email"));
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
        return user;
    }

    public static boolean checkCurrentEmail(String email) throws Exception {
        boolean check = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select userId, firstName, lastName from  Users where email = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
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

    public static boolean registerUser(UserDTO userDTO) throws Exception {
        boolean success = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into Users(email, firstName, lastName, password, sex, year) values (?, ?, ?, ?, ?, ?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, userDTO.getEmail());
                pst.setString(2, userDTO.getFirstname());
                pst.setString(3, userDTO.getLastname());
                pst.setString(4, userDTO.getPassword());
                pst.setString(5, userDTO.getSex());
                pst.setInt(6, userDTO.getYear());
                success = pst.execute();
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
        return success;
    }

    public static UserDTO getUserAfterRegister() throws Exception {
        UserDTO userDTO = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select userId, firstName, lastName, sex, year from Users where userId = (select max(userId) from Users)";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    userDTO = new UserDTO();
                    userDTO.setUserId(rs.getInt("userId"));
                    userDTO.setFirstname(rs.getString("firstName"));
                    userDTO.setLastname(rs.getString("lastName"));
                    userDTO.setSex(rs.getString("sex"));
                    userDTO.setYear(rs.getInt("year"));
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
        return userDTO;
    }

    public static UserDTO getUserById(int id) throws Exception {
        UserDTO userDTO = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select userId, firstName, lastName, sex, year, email from Users where userId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    userDTO = new UserDTO();
                    userDTO.setUserId(rs.getInt("userId"));
                    userDTO.setSex(rs.getString("sex"));
                    userDTO.setYear(rs.getInt("year"));
                    userDTO.setLastname(rs.getString("lastName"));
                    userDTO.setFirstname(rs.getString("firstName"));
                    userDTO.setEmail(rs.getString("email"));
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
        return userDTO;
    }
    
    public static void updateAgeAndSexOfUser(UserDTO userDTO) throws Exception {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "update Users set sex = ?, year = ? where userId = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, userDTO.getSex());
                pst.setInt(2, userDTO.getYear());
                pst.setInt(3, userDTO.getUserId());
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
    
    public static void updateInfoOfUser(UserDTO userDTO) throws Exception {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "update Users set sex = ?, year = ?, firstName = ?, lastName = ?, email = ? where userId = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, userDTO.getSex());
                pst.setInt(2, userDTO.getYear());
                pst.setString(3, userDTO.getFirstname());
                pst.setString(4, userDTO.getLastname());
                pst.setString(5, userDTO.getEmail());
                pst.setInt(6, userDTO.getUserId());
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
}
