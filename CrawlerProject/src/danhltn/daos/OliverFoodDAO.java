/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.daos;

import danhltn.db.DBUtilities;
import danhltn.dtos.OliverFood;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author leagu
 */
public class OliverFoodDAO {

    public static void insertOliverFood(OliverFood food) throws Exception {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "insert into Food(name, imageUrl, time, serves, levels, description, ingredient, step, calo)\n"
                        + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, food.getName());
                pst.setString(2, food.getImageUrl());
                pst.setString(3, food.getTime());
                pst.setString(4, food.getServes());
                pst.setString(5, food.getLevel());
                pst.setString(6, food.getDescription());
                pst.setString(7, food.getIngredients());
                pst.setString(8, food.getSteps());
                pst.setInt(9, (int)food.getKcals());
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
