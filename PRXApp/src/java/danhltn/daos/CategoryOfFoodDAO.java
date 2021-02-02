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
public class CategoryOfFoodDAO {
    
    public static List<Integer> getFoodByCategory(int categoryId) throws Exception {
        List<Integer> list = new ArrayList<>();
         
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select foodId from CategoryOfFood where categoryId = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, categoryId);
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
