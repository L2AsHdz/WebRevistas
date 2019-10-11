/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Comentario.ComentarioDAO;
import com.ashdz.webrevistas.DAO.Comentario.ComentarioDAOImpl;
import com.ashdz.webrevistas.model.Comentario;
import com.ashdz.webrevistas.model.Usuario;
import java.io.IOException;
import java.time.LocalDate;
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
@WebServlet(name = "CommentController", urlPatterns = {"/CommentController"})
public class CommentController extends HttpServlet {
    private ComentarioDAO commentDAO;
    private Usuario user;
    private Comentario comment;
    private String date;
    private int idR;
    private String email;
    private String comentario;
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        commentDAO = ComentarioDAOImpl.getComentarioDAO();
        user = (Usuario)request.getSession().getAttribute("usuario");
        idR = Integer.parseInt(request.getParameter("idR"));
        email = user.getEmailUsuario();
        comentario = request.getParameter("commentText");
        date = request.getParameter("fecha");
        comment = new Comentario(idR, email, LocalDate.parse(date), comentario);
        
        try {
            commentDAO.create(comment);
            request.getSession().setAttribute("idRev", idR);
            RequestDispatcher dispatcher = request.getRequestDispatcher("revista.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
        }
    }
}
