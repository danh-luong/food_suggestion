/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.controllers;

import danhltn.daos.FavoriteDAO;
import danhltn.daos.FoodDAO;
import danhltn.dtos.FoodDTO;
import danhltn.dtos.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leagu
 */
public class ProductDetailController extends HttpServlet {

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
            String productId = request.getParameter("foodId");
            FoodDTO food = FoodDAO.getProductById(Integer.parseInt(productId));
            request.setAttribute("FOOD", food);
            String[] step = food.getSteps().split("&");
            List<String> list = new ArrayList<>();
            if (food.getSteps().contains("Step 1:")) {
                for (int i = 0; i < step.length; i++) {
                    list.add(step[i].split(":")[1].trim());
                }
            }
            request.setAttribute("INGREDIENTS", food.getIngredients().split("&"));
            if (list.size() != 0) {
                request.setAttribute("STEPS", list);
            } else {
                request.setAttribute("STEPS", step);
            }
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user != null) {
                int userId = user.getUserId();
                boolean check = FavoriteDAO.checkFavoriteOfUser(userId, Integer.parseInt(productId));
                if (check) {
                    request.setAttribute("isFavorite", true);
                } else {
                    request.setAttribute("isFavorite", false);
                }
            }
            request.getRequestDispatcher("productDetail.jsp").forward(request, response);
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
