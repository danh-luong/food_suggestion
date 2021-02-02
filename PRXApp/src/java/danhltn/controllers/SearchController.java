/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.controllers;

import danhltn.daos.CategoryDAO;
import danhltn.daos.FoodDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leagu
 */
public class SearchController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String categoryId = request.getParameter("categoryId");
            String inputSearch = request.getParameter("inputSearch");
            String offset = request.getParameter("offset");
            StringBuilder searchedFood = new StringBuilder();
            String food = null;
            if (!categoryId.equalsIgnoreCase("default")) {
                List<Integer> listCategoryId = CategoryDAO.getFoodIdByCategoryId(Integer.parseInt(categoryId), Integer.parseInt(offset), 10);
                searchedFood.append("<foodlist>");
                for (int i = 0; i < listCategoryId.size(); i++) {
                    food = FoodDAO.getFoodBySearchAndIdXML(inputSearch, listCategoryId.get(i));
                    searchedFood.append(food);
                }
                searchedFood.append("</foodlist>");
            } else {
                food = FoodDAO.getFoodBySearchXML(inputSearch, Integer.parseInt(offset), 10);
                searchedFood.append(food);
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(searchedFood.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
