/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.daos;

import danhltn.db.DBUtilities;
import danhltn.dtos.HistoryOfUserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author leagu
 */
public class HistoryOfUserDAO implements Serializable {

    public static void insertHistoryOfUser(HistoryOfUserDTO history) throws Exception {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into HistoryOfUser(height, weight, calories, activity, userId, timeSearch) values (?, ?, ?, ?, ?, ?)";
                pst = con.prepareStatement(sql);
                pst.setFloat(1, (float) history.getHeight());
                pst.setFloat(2, (float) history.getWeight());
                pst.setInt(3, history.getCalories());
                pst.setString(4, history.getActivity());
                pst.setInt(5, history.getUserId());
                pst.setString(6, history.getDateCreated());
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

    public static List<HistoryOfUserDTO> getListHistory(int offset, int limit, int userId) throws Exception {
        List<HistoryOfUserDTO> list = null;
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select historyId, height, weight, activity, calories, timeSearch, userId from HistoryOfUser where userId = ? \n"
                        + "order by historyId offset ? rows fetch next ? rows only";
                pst = con.prepareStatement(sql);
                pst.setInt(1, userId);
                pst.setInt(2, offset);
                pst.setInt(3, limit);
                list = new ArrayList<>();
                rs = pst.executeQuery();
                java.sql.Date dbSqlDate = null;
                Date dbUtilDate = null;
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                while (rs.next()) {
                    HistoryOfUserDTO historyOfUserDTO = new HistoryOfUserDTO();
                    historyOfUserDTO.setHistoryId(rs.getInt("historyId"));
                    historyOfUserDTO.setUserId(rs.getInt("userId"));
                    historyOfUserDTO.setWeight(rs.getFloat("weight"));
                    historyOfUserDTO.setHeight(rs.getFloat("height"));
                    historyOfUserDTO.setActivity(rs.getString("activity"));
                    historyOfUserDTO.setCalories(rs.getInt("calories"));
                    dbSqlDate = rs.getDate("timeSearch");
                    dbUtilDate = new Date(dbSqlDate.getTime());
                    String createdDate = formatter.format(dbUtilDate);
                    historyOfUserDTO.setDateCreated(createdDate);
                    list.add(historyOfUserDTO);
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
    
    public static void deleteHistory(int historyId) throws Exception {
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "delete from HistoryOfUser where historyId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, historyId);
                pst.executeUpdate();
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
    }
}
