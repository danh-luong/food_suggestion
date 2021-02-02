/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.controllers;

import danhltn.daos.FoodDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leagu
 */
public class SearchAdvanceResultController extends HttpServlet {

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
            String inputSearch = request.getParameter("inputSearch");
            String offset = request.getParameter("offset");
            String calo = request.getParameter("calo");
            String categoryId = request.getParameter("categoryId");
            String level = request.getParameter("level");
            String food = "";
            if (level.equalsIgnoreCase("Whatever")) {
                level = "";
            }
            if (level.equalsIgnoreCase("Be")) {
                level = "A little effort";
            }
            if (categoryId.equalsIgnoreCase("default")) {
                food = FoodDAO.getFoodByAdvanceSearchWithoutCategoryXML(inputSearch, Integer.parseInt(offset),
                        10, Integer.parseInt(calo), level);
            } else {
                food = FoodDAO.getFoodByAdvanceSearchCategoryXML(inputSearch, Integer.parseInt(offset),
                        10, Integer.parseInt(calo), level, Integer.parseInt(categoryId));
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            if (food == null) {
                response.getWriter().write("");
            } else {
                response.getWriter().write(food);
            }
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
