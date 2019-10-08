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
@WebServlet(name = "Previsualizar", urlPatterns = {"/Previsualizar"})
public class Previsualizar extends HttpServlet {
    private Integer id;
    private Revista r = null;
    private RevistaDAO revDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        revDAO = RevistaDAOImpl.getRevistaDAO();
        id = Integer.parseInt(request.getParameter("id"));
        try {
            r = revDAO.getObject(id);
            request.setAttribute("rev", r);
            RequestDispatcher dispatcher = request.getRequestDispatcher("buscarRevistaE.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }
                
    }
}
