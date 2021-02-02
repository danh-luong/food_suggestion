/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.daos;

import danhltn.db.DBUtilities;
import danhltn.dtos.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leagu
 */
public class CategoryDAO {

    public static List<CategoryDTO> getAllCategory() throws Exception {
        List<CategoryDTO> list = new ArrayList<>();
        CategoryDTO categoryDTO = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select categoryId, name from Category";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    categoryDTO = new CategoryDTO();
                    categoryDTO.setCategoryId(rs.getInt("categoryId"));
                    String result = rs.getString("name");
                    if (result.contains("-")) {
                        String[] newArr = result.split("-");
                        String tmpNameCategory = "";
                        for (String ele : newArr) {
                            tmpNameCategory = tmpNameCategory + ele + " ";
                        }
                        categoryDTO.setName(tmpNameCategory.trim());
                    } else {
                        categoryDTO.setName(rs.getString("name"));
                    }
                    list.add(categoryDTO);
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

    public static List<Integer> getFoodIdByCategoryId(int categoryId, int offset, int limit) throws Exception {
        List<Integer> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "select distinct foodId from CategoryOfFood where categoryId = ? order by foodId OFFSET ? rows fetch next ? rows only";
                pst = con.prepareStatement(sql);
                pst.setInt(1, categoryId);
                pst.setInt(2, offset);
                pst.setInt(3, limit);
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
