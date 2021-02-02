/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.controllers;

import danhltn.daos.CategoryDAO;
import danhltn.daos.FoodDAO;
import danhltn.daos.HistoryOfUserDAO;
import danhltn.dtos.CategoryDTO;
import danhltn.dtos.FoodDTO;
import danhltn.dtos.HistoryOfUserDTO;
import danhltn.dtos.UserDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author leagu
 */
public class CalculateController extends HttpServlet {

    private final String URI_WS_JDBC = "http://localhost:8080/PRXApp_WS_JDBC/webresources/generic";

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
            Client client = ClientBuilder.newClient();

            String result = request.getParameter("result");
            double caloByMeal = Double.parseDouble(result) / 3;
            List<FoodDTO> foodContainer = FoodDAO.getListFoodByCalo(caloByMeal);
            request.setAttribute("foodContainer", foodContainer);
            request.setAttribute("CALOMEAL", (int) Math.round(caloByMeal));
            request.setAttribute("CALODAY", result);

            Response res = client.target(URI_WS_JDBC).path("/getProductByRange")
                    .queryParam("bmr", (int) caloByMeal)
                    .request()
                    .accept(MediaType.APPLICATION_XML_TYPE)
                    .get(Response.class);
            List<FoodDTO> list = res.readEntity(new GenericType<List<FoodDTO>>() {
            });
            request.setAttribute("LISTPRODUCT", list);
            request.setAttribute("TITLE", "Maintain Weight Suggestion Foods For 3 main meals");
            request.setAttribute("OFFSET", list.size());

            WebTarget webTarget = client.target(URI_WS_JDBC).path("/getProductByRangeXML");
            res = webTarget.queryParam("bmr", (int) caloByMeal)
                    .request().get();
            String listXML = res.readEntity(String.class);
            request.setAttribute("MAINTAIN", listXML.replaceAll("\"", ""));

            double totalCaloFor50Percent = Double.parseDouble(result) - 556;
            double percentage50OnFirstCalo = Math.round((totalCaloFor50Percent * 100) / Double.parseDouble(result));
            double caloByMeal50Percent = Math.round(totalCaloFor50Percent / 3);
            request.setAttribute("CALODAY50", (int) totalCaloFor50Percent);
            request.setAttribute("CALOMEAL50", (int) caloByMeal50Percent);
            request.setAttribute("PERCENT50", (int) percentage50OnFirstCalo);

            double totalCaloFor1Kg = Double.parseDouble(result) - 1111;
            double percentage1KgOnFirstCalo = Math.round((totalCaloFor1Kg * 100) / Double.parseDouble(result));
            double caloByMeal1Kg = Math.round(totalCaloFor1Kg / 3);
            request.setAttribute("CALODAY1", (int) totalCaloFor1Kg);
            request.setAttribute("CALOMEAL1", (int) caloByMeal1Kg);
            request.setAttribute("PERCENT1", (int) percentage1KgOnFirstCalo);

            String advise = "";
            String heightBMI = request.getParameter("height");
            String weightBMI = request.getParameter("weight");
            double heightInCM = Double.parseDouble(heightBMI) / 100;
            double resultBIM = Double.parseDouble(weightBMI) / (heightInCM * heightInCM);
            if (resultBIM < 18.5) {
                advise = "You should gain weight";
            } else if (resultBIM >= 18.5 && resultBIM <= 25) {
                advise = "You should maintain weight";
            } else {
                advise = "You should lose weight";
            }
            request.setAttribute("ADVISE", advise);

            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String checkMe = request.getParameter("me");
            if (checkMe != null) {
                if (checkMe.equalsIgnoreCase("me")) {
                    String height = request.getParameter("height");
                    String weight = request.getParameter("weight");
                    String activity = request.getParameter("activity");
                    HistoryOfUserDTO historyOfUserDTO = new HistoryOfUserDTO();
                    historyOfUserDTO.setHeight(Double.parseDouble(height));
                    historyOfUserDTO.setWeight(Double.parseDouble(weight));
                    historyOfUserDTO.setActivity(activity);
                    historyOfUserDTO.setCalories(Integer.parseInt(result));
                    historyOfUserDTO.setUserId(user.getUserId());
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    historyOfUserDTO.setDateCreated(formatter.format(date));
                    HistoryOfUserDAO.insertHistoryOfUser(historyOfUserDTO);
                }
            }
            List<CategoryDTO> listCategory = CategoryDAO.getAllCategory();
            request.setAttribute("CATEGORY", listCategory);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("result.jsp").forward(request, response);
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
