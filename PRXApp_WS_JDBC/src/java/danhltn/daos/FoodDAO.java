/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.daos;

import danhltn.db.DBUtilities;
import danhltn.dtos.FoodDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leagu
 */
public class FoodDAO {

    public static List<FoodDTO> getListFoodProduct(int calo, int offset, int limit) throws Exception {
        List<FoodDTO> container = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            String sql = "select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food where calo < ? order by foodId \n"
                    + "OFFSET ? rows fetch next ? rows only";
            pst = con.prepareStatement(sql);
            pst.setInt(1, calo);
            pst.setInt(2, offset);
            pst.setInt(3, limit);
            rs = pst.executeQuery();
            if (con != null) {
                while (rs.next()) {
                    FoodDTO food = new FoodDTO();
                    food.setFoodId(rs.getInt("foodId"));
                    food.setName(rs.getString("name"));
                    food.setImageUrl(rs.getString("imageUrl"));
                    food.setTime(rs.getString("time"));
                    food.setServes(rs.getString("serves"));
                    food.setLevel(rs.getString("levels"));
                    food.setDescription(rs.getString("description"));
                    food.setIngredients(rs.getString("ingredient"));
                    food.setSteps(rs.getString("step"));
                    food.setCals(rs.getInt("calo"));
                    food.setTip(rs.getString("tip"));
                    container.add(food);
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
        return container;
    }

    public static String getListFoodProductXML(int calo, int offset, int limit) throws Exception {
        StringBuilder result = new StringBuilder();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            String sql = "select cast ((select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food where calo < ? order by foodId \n"
                    + "OFFSET ? rows fetch next ? rows only FOR XML PATH('food'), ROOT ('foodlist'))  as varchar(max)) as 'xml'";
            pst = con.prepareStatement(sql);
            pst.setInt(1, calo);
            pst.setInt(2, offset);
            pst.setInt(3, limit);
            rs = pst.executeQuery();
            if (con != null) {
                if (rs.next()) {
                    result.append(rs.getString("xml"));
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
        return result.toString();
    }

    public static String getListFoodProductByNameXML(String search, int calo, int offset, int limit) throws Exception {
        StringBuilder result = new StringBuilder();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            String sql = "select cast ((select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food where name like ? and calo < ? order by foodId\n"
                    + "OFFSET ? rows fetch next ? rows only FOR XML PATH('food'), ROOT ('foodlist'))  as varchar(max)) as 'xml'";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + search + "%");
            pst.setInt(2, calo);
            pst.setInt(3, offset);
            pst.setInt(4, limit);
            rs = pst.executeQuery();
            if (con != null) {
                if (rs.next()) {
                    result.append(rs.getString("xml"));
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
        return result.toString();
    }
}
