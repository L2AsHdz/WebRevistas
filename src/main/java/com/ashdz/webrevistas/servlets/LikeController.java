/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Like.LikeDAO;
import com.ashdz.webrevistas.DAO.Like.LikeDAOImpl;
import com.ashdz.webrevistas.model.Like;
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
@WebServlet(name = "LikeController", urlPatterns = {"/LikeController"})
public class LikeController extends HttpServlet {
    private LikeDAO likeDAO;
    private Usuario user;
    private Like like;
    private String email;
    private int idR;
    private String date;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        likeDAO = LikeDAOImpl.getLikeDAO();
        user = (Usuario)request.getSession().getAttribute("usuario");
        email = user.getEmailUsuario();
        idR = Integer.parseInt(request.getParameter("idR"));
        date = request.getParameter("fecha");
        like = new Like(idR,email,LocalDate.parse(date));
        try {
            if (!likeDAO.verificarLike(idR, email)) {
                likeDAO.create(like);
                //request.setAttribute("like", true);
            } else if (likeDAO.verificarLike(idR, email)){
                likeDAO.delete(idR, email);
                //request.get
            }
            request.getSession().setAttribute("idRev", idR);
            RequestDispatcher dispatcher = request.getRequestDispatcher("revista.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
