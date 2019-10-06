/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Categoria.CategoriaDAO;
import com.ashdz.webrevistas.DAO.Categoria.CategoriaDAOImpl;
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
@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    private String nombre;
    private CategoriaDAO categoDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        nombre = request.getParameter("etiqueta");
        categoDAO = CategoriaDAOImpl.getCategoriaDAO();

        if (!nombre.isEmpty()) {
            try {
                categoDAO.createCat(nombre);
                dispatcher = request.getRequestDispatcher("categorias.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            request.setAttribute("errorEmpty", true);
            dispatcher = request.getRequestDispatcher("categorias.jsp");
            dispatcher.forward(request, response);
        }
    }
}
