/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

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
@WebServlet(name = "BuscarRevistaEController", urlPatterns = {"/BuscarRevistaEController"})
public class BuscarRevistaEController extends HttpServlet {
    private String[] etiquetas;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        etiquetas = request.getParameterValues("etiquetas");
        RequestDispatcher dispatcher;
        if (etiquetas != null) {
            try {
                String sql = "";
                for (String e : etiquetas) {
                    sql += " OR IdEtiqueta=" + e;
                }
                request.setAttribute("sql", sql);
                dispatcher = request.getRequestDispatcher("buscarRevistaE.jsp");
                dispatcher.forward(request, response);
            } catch (IOException | ServletException e) {
                System.out.println(e.getMessage());
            }
        } else {
            request.setAttribute("errorNull", true);
            dispatcher = request.getRequestDispatcher("buscarRevistaE.jsp");
            dispatcher.forward(request, response);
        }
    }
}
