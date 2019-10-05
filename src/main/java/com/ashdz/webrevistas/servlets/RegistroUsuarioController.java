/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Usuario.UsuarioDAO;
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
    private Usuario u;
    private UsuarioDAO userDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        u = new Usuario(request);
        String pass2 = request.getParameter("pass2");
        userDAO = UsuarioDAOImpl.getUserDAO();
        RequestDispatcher dispatcher;
        
        System.out.println(u.toString());
        System.out.println(u.getEmailUsuario().isEmpty());
        boolean flag = true;

        if (u.getNombreUsuario().isEmpty() || u.getEmailUsuario().isEmpty() || u.getNombreUsuario().isEmpty() ||
            u.getPassword().isEmpty() || u.getTipoUsuario() == 1) {
            request.setAttribute("errorEmpty", u);
            flag = false;
        }
        if (userDAO.getByEmail(u.getEmailUsuario()) != null || userDAO.getByUserName(u.getUsuarioSistema()) != null) {
            request.setAttribute("errorExiste", u);   
            flag = false;
        }
        if (!u.getPassword().equals(pass2)) {
            request.setAttribute("errorPass", u);
            flag = false;
            System.out.println("Passwords distintas");
        }
        
        if (!flag) {
            dispatcher = request.getRequestDispatcher("registro.jsp");
            dispatcher.forward(request, response);
            System.out.println("se han enviado los errores");
        } else {
            System.out.println("Que esta pasando doctor garcia");
            try {
                userDAO.create(u);
                request.setAttribute("succes", u);
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            } catch (IOException | ServletException e) {
                System.out.println("error en forward");
                System.out.println(e.getMessage());
            }
        }
    }
}
