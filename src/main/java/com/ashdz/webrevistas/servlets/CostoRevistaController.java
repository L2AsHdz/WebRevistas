/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Precios.PrecioDAO;
import com.ashdz.webrevistas.DAO.Precios.PrecioDAOImpl;
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
@WebServlet(name = "CostoRevistaController", urlPatterns = {"/CostoRevistaController"})
public class CostoRevistaController extends HttpServlet {
    private String valor;
    private PrecioDAO precioDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        valor = request.getParameter("costoG");
        precioDAO = PrecioDAOImpl.getPrecioDAO();
        RequestDispatcher dispatcher;
        
        if (isFloat(valor)) {
            float val = Float.parseFloat(valor);
            if (val>0.40) {
                System.out.println("errorMax");
                request.setAttribute("errorMax", val);
                dispatcher = request.getRequestDispatcher("CostoRevista.jsp");
                dispatcher.forward(request, response);
            } else {
                try {
                    precioDAO.updateValor(1, Float.parseFloat(valor));
                    request.setAttribute("exito", valor);
                    dispatcher = request.getRequestDispatcher("CostoRevista.jsp");
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                }
            }
        } else {
            System.out.println("errorNumber");
            request.setAttribute("errorNumber", valor);
            dispatcher = request.getRequestDispatcher("CostoRevista.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean isFloat(String valor) {
        try {
            Float.parseFloat(valor);
            return true;
        } catch (Exception e) {
         return false;
        }
    }

}
