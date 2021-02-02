/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.daos;

import danhltn.db.DBUtilities;
import danhltn.dtos.FoodDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leagu
 */
public class FoodDAO implements Serializable {

    public static List<FoodDTO> getListFoodByCalo(double calo) throws Exception {
        List<FoodDTO> container = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food where calo <= ?";
                pst = con.prepareStatement(sql);
                pst.setDouble(1, (double) calo + 5);
                rs = pst.executeQuery();
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

    public static List<FoodDTO> getProductSpecificQuantityOneTime(int limit, int offset) throws Exception {
        List<FoodDTO> container = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, "
                        + "tip from Food order by foodId OFFSET ? rows\n"
                        + "fetch next ? rows only";
                pst = con.prepareStatement(sql);
                pst.setInt(1, offset);
                pst.setInt(2, limit);
                rs = pst.executeQuery();
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

    public static String getProductSpecificQuantityOneTimeXML(int limit, int offset, String inputSearch, int slectedValue) throws Exception {
        String xmlText = "";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select (select c.foodId, f.name, f.imageUrl, f.time, f.serves, f.levels, f.description, f.ingredient, f.step, f.calo, f.tip from CategoryOfFood c left join\n"
                        + "(select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, \n"
                        + "tip from Food) f on f.foodId = c.foodId where f.name like ? and categoryId = ? order by c.foodId OFFSET ? rows fetch next ? rows only FOR XML PATH('food'), ROOT ('foodlist')) as 'xml'";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + inputSearch + "%");
                pst.setInt(2, slectedValue);
                pst.setInt(3, offset);
                pst.setInt(4, limit);
                rs = pst.executeQuery();
                if (rs.next()) {
                    xmlText = rs.getString("xml");
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
        return xmlText;
    }

    public static String getProductSpecificQuantityOneTimeXMLWithoutCategory(int limit, int offset, String inputSearch) throws Exception {
        String xmlText = "";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select (select distinct f.foodId, f.name, f.imageUrl, f.time, f.serves, f.levels, f.description, f.ingredient, f.step, f.calo, \n"
                        + "f.tip from Food f left join (select foodId from CategoryOfFood) c on f.foodId = c.foodId \n"
                        + " where f.name like ? order by foodId OFFSET ? rows fetch next ? rows only FOR XML PATH('food'), ROOT ('foodlist')) as 'xml'";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + inputSearch + "%");
                pst.setInt(2, offset);
                pst.setInt(3, limit);
                rs = pst.executeQuery();
                if (rs.next()) {
                    xmlText = rs.getString("xml");
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
        return xmlText;
    }

    public static FoodDTO getProductById(int foodId) throws Exception {
        FoodDTO food = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food where foodId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, foodId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    food = new FoodDTO();
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
        return food;
    }

    public static String getProductByIdXML(int foodId) throws Exception {
        String food = "";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select (select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food where foodId = ? FOR XML PATH('food')) as 'xml'";
                pst = con.prepareStatement(sql);
                pst.setInt(1, foodId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    food = rs.getString("xml");
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
        return food;
    }

    public static String getFoodBySearchAndIdXML(String inputSearch, int foodId) throws Exception {
        String food = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select (select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food where foodId = ? and name like ? order by foodId FOR XML PATH('food')) as 'xml'";
                pst = con.prepareStatement(sql);
                pst.setInt(1, foodId);
                pst.setString(2, "%" + inputSearch + "%");
                rs = pst.executeQuery();
                if (rs.next()) {
                    food = rs.getString("xml");
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
        return food;
    }

    public static String getFoodBySearchXML(String inputSearch, int offset, int limit) throws Exception {
        String food = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select (select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food where name like ? order by foodId OFFSET ? rows fetch next ? rows only FOR XML PATH('food'), ROOT ('foodlist')) as 'xml'";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + inputSearch + "%");
                pst.setInt(2, offset);
                pst.setInt(3, limit);
                rs = pst.executeQuery();
                if (rs.next()) {
                    food = rs.getString("xml");
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
        return food;
    }

    public static String getFoodByAdvanceSearchWithoutCategoryXML(String inputSearch, int offset, int limit, int calo, String level) throws Exception {
        String food = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select cast ((select foodId, name, imageUrl, time, serves, levels, description, ingredient, step, calo, tip from Food \n"
                        + "where name like ? and calo < ? and levels like ? order by foodId OFFSET ? rows \n"
                        + "fetch next ? rows only FOR XML PATH('food'), ROOT ('foodlist')) as varchar(max)) as 'xml'";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + inputSearch + "%");
                pst.setInt(2, calo);
                pst.setString(3, "%" + level + "%");
                pst.setInt(4, offset);
                pst.setInt(5, limit);
                rs = pst.executeQuery();
                if (rs.next()) {
                    food = rs.getString("xml");
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
        return food;
    }

    public static String getFoodByAdvanceSearchCategoryXML(String inputSearch, int offset, int limit, int calo, String level, int categoryId) throws Exception {
        String food = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select cast ((select f.foodId, f.name, f.imageUrl, f.time, f.serves, f.levels, f.description, f.ingredient, f.step, f.calo, \n"
                        + "f.tip from Food f right join (select c.foodId from CategoryOfFood c where c.categoryId = ?) x on f.foodId = x.foodId\n"
                        + "where f.name like ? and f.levels like ? and f.calo < ? order by foodId OFFSET ? rows fetch next ? rows only FOR XML PATH('food'), ROOT ('foodlist')) as varchar(max)) as 'xml'";
                pst = con.prepareStatement(sql);
                pst.setInt(1, categoryId);
                pst.setString(2, "%" + inputSearch + "%");
                pst.setString(3, "%" + level + "%");
                pst.setInt(4, calo);
                pst.setInt(5, offset);
                pst.setInt(6, limit);
                rs = pst.executeQuery();
                if (rs.next()) {
                    food = rs.getString("xml");
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
        return food;
    }
}
