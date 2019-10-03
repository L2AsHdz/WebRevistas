/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.CRUD;
import com.ashdz.webrevistas.DAO.Usuario.UsuarioDAOImpl;
import com.ashdz.webrevistas.model.Usuario;
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
@WebServlet(name = "RegistroUsuarioController", urlPatterns = {"/RegistroUsuarioController"})
public class RegistroUsuarioController extends HttpServlet {
    private Usuario user;
    private CRUD<Usuario> userDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user = new Usuario(request);
        userDAO = UsuarioDAOImpl.getUserDAO();
        
        try {
            userDAO.create(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("resultado.jsp");
            request.setAttribute("usuario", user);
            dispatcher.forward(request, response);
        } catch (Exception e) {
        }
    }
}
