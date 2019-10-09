/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Suscripcion.SuscripcionDAO;
import com.ashdz.webrevistas.DAO.Suscripcion.SuscripcionDAOImpl;
import com.ashdz.webrevistas.model.Revista;
import com.ashdz.webrevistas.model.Suscripcion;
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
@WebServlet(name = "SuscribirController", urlPatterns = {"/SuscribirController"})
public class SuscribirController extends HttpServlet {
    private SuscripcionDAO suscripDAO;
    private Usuario user;
    private Revista rev;
    private Suscripcion suscrip;
    private String date;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        suscripDAO = SuscripcionDAOImpl.getSuscripcionDAO();
        date = request.getParameter("fecha");
        rev = (Revista)request.getSession().getAttribute("revista");
        user = (Usuario)request.getSession().getAttribute("usuario");
        suscrip = new Suscripcion();
        suscrip.setIdRevista(rev.getIdRevista());
        suscrip.setEmailSuscriptor(user.getEmailUsuario());
        suscrip.setFechaSuscripcion(LocalDate.parse(date));
        try {
            suscripDAO.create(suscrip);
            request.getSession().removeAttribute("revista");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
        }
    }
}
