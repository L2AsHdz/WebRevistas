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
import javax.servlet.http.HttpSession;

/**
 *
 * @author asael
 */
@WebServlet(name = "ValidarLogin", urlPatterns = {"/ValidarLogin"})
public class ValidarLogin extends HttpServlet {

    private Usuario user;
    private Usuario user2;
    private UsuarioDAO userDAO = UsuarioDAOImpl.getUserDAO();
    private String nameUser;
    private String pass;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nameUser = request.getParameter("user");
        pass = request.getParameter("pass");
        try {
            user = userDAO.getByEmail(nameUser.toLowerCase());
            user2 = userDAO.getByUserName(nameUser.toLowerCase());
            if (user != null && user.getEmailUsuario().equalsIgnoreCase(nameUser)) {
                validarPass(request, response, user);
            } else if (user2 != null && user2.getUsuarioSistema().equalsIgnoreCase(nameUser)) {
                validarPass(request, response, user2);
            } else {
                request.setAttribute("errorUser", nameUser);
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (IOException | ServletException e) {
            request.setAttribute("errorDB", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void validarPass(HttpServletRequest request, HttpServletResponse response, Usuario user) {
        try {
            if (user.getPassword().equals(pass)) {
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("usuario", user);
                sesion.setMaxInactiveInterval(1800);
                //request.setAttribute("usuario", user);
                switch (user.getTipoUsuario()) {
                    case 1:
                        RequestDispatcher dispatcher = request.getRequestDispatcher("inicioAdmin.jsp");
                        dispatcher.forward(request, response);
                        //response.sendRedirect("inicioAdmin.jsp");
                        break;
                    case 2:
                        RequestDispatcher dispatcher2 = request.getRequestDispatcher("inicioEditor.jsp");
                        dispatcher2.forward(request, response);
                        break;
                    case 3:
                        RequestDispatcher dispatcher3 = request.getRequestDispatcher("inicioSuscriptor.jsp");
                        dispatcher3.forward(request, response);
                        break;
                }
            } else {
                request.setAttribute("errorPass", nameUser);
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (IOException | ServletException e) {
        }
    }

}
