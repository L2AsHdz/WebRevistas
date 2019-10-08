/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Revista.RevistaDAO;
import com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl;
import com.ashdz.webrevistas.model.Revista;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author asael
 */
@WebServlet(name = "RevistaController", urlPatterns = {"/RevistaController"})
public class RevistaController extends HttpServlet {
    private Revista r;
    private String cuota;
    private RevistaDAO revDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        r = new Revista(request);
        cuota = request.getParameter("cuota");
        revDAO = RevistaDAOImpl.getRevistaDAO();
        
        RequestDispatcher dispatcher;
        boolean flag = true;
        
        if (r.getNombreRevista().isEmpty() || r.getIdCategoria()==0 || cuota.isEmpty()) {
            request.setAttribute("errorEmpty", r);
            flag = false;
        }
        if (isFloat(cuota)) {
            r.setCuotaSuscripcion(Float.parseFloat(cuota));
            
        } else {
            System.out.println("no es un flotante");
            request.setAttribute("errorNumber", r);
            flag = false;
        }
        
        if (!flag) {
            dispatcher = request.getRequestDispatcher("crearRevista.jsp");
            dispatcher.forward(request, response);
            System.out.println("se han enviado los errores");
        } else {
            try {
                revDAO.create(r);
                request.setAttribute("succes", r);
                dispatcher = request.getRequestDispatcher("crearRevista.jsp");
                dispatcher.forward(request, response);
            } catch (IOException | ServletException e) {
                System.out.println("error en forward");
                System.out.println(e.getMessage());
            }
        }
    }
    
    private boolean isFloat(String s){
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
