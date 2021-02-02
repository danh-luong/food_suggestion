/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.restul;

import danhltn.daos.FoodDAO;
import danhltn.dtos.FoodDTO;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author leagu
 */
@Path("generic")
public class CalculateBMR {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CalculateBMR
     */
    public CalculateBMR() {
    }

    /**
     * Retrieves representation of an instance of danhltn.restul.CalculateBMR
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CalculateBMR
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }

    @Path("/getProductByRange")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<FoodDTO> getProductByRange(@QueryParam("bmr") int bmrNumber) {
        List<FoodDTO> container = null;
        try {
            container = FoodDAO.getListFoodProduct(bmrNumber, 0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return container;
    }

    @Path("/getProductByRangeXML")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getProductXML(@QueryParam("bmr") int bmrNumber) {
        String result = "";
        try {
            result = FoodDAO.getListFoodProductXML(bmrNumber, 0, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Path("/searchProductByNameXML")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getProductXML(@QueryParam("search") String search,
            @QueryParam("offset") int offset,
            @QueryParam("limit") int limit,
            @QueryParam("calo") int calo) {
        String result = "";
        try {
            result = FoodDAO.getListFoodProductByNameXML(search, calo, offset, limit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
