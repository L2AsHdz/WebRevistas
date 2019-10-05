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
    private Usuario usuario;
    private UsuarioDAO userDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        usuario = new Usuario(request);
        userDAO = UsuarioDAOImpl.getUserDAO();
        RequestDispatcher dispatcher;
        
        /*boolean flag = true;

        if (usuario.getNombreUsuario().length()<2 || usuario.getEmailUsuario().length()<2
            || usuario.getNombreUsuario().length()<2 || usuario.getPassword().length()<2
            || usuario.getTipoUsuario() == 1) {
            request.setAttribute("errorEmpty", usuario);
            flag = false;
        }
        if (userDAO.getByEmail(usuario.getEmailUsuario()) != null || userDAO.getByUserName(usuario.getNombreUsuario()) != null) {
            request.setAttribute("errorExiste", usuario);   
            flag = false;
        }
        if (flag) {
            
        }
        
        if (!flag) {
            dispatcher = request.getRequestDispatcher("registro.jsp");
            dispatcher.forward(request, response);
        } else {*/
            try {
                userDAO.create(usuario);
                dispatcher = request.getRequestDispatcher("resultado.jsp");
                dispatcher.forward(request, response);
            } catch (IOException | ServletException e) {
            }
       // }
    }
}
