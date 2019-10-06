/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAO;
import com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAOImpl;
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
@WebServlet(name = "EtiquetaController", urlPatterns = {"/EtiquetaController"})
public class EtiquetaController extends HttpServlet {
    private String nombre;
    private EtiquetaDAO etiquetaDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        nombre = request.getParameter("etiqueta");
        etiquetaDAO = EtiquetaDAOImpl.getEtiquetaDAO();

        if (!nombre.isEmpty()) {
            try {
                etiquetaDAO.createEtiqueta(nombre);
                dispatcher = request.getRequestDispatcher("etiquetas.jsp");
                dispatcher.forward(request, response);
            } catch (IOException | ServletException e) {
                System.out.println(e.getMessage());
            }
        } else {
            request.setAttribute("errorEmpty", true);
            dispatcher = request.getRequestDispatcher("etiquetas.jsp");
            dispatcher.forward(request, response);
        }
    }
}
