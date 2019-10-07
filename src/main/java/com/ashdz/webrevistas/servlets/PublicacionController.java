/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.Publicacion.PublicacionDAO;
import com.ashdz.webrevistas.DAO.Publicacion.PublicacionDAOImpl;
import com.ashdz.webrevistas.model.Publicacion;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author asael
 */
@MultipartConfig(maxFileSize = 16777215, location = "")
@WebServlet(name = "PublicacionController", urlPatterns = {"/PublicacionController"})
public class PublicacionController extends HttpServlet {
    private Publicacion p;
    private PublicacionDAO publiDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        publiDAO = PublicacionDAOImpl.getPublicacionDAO();
        Part part = request.getPart("archivo");
        int idR = Integer.parseInt(request.getParameter("revista"));
        String date = request.getParameter("fecha");
        RequestDispatcher dispatcher;
        boolean flag = true;
        
        if ((part.getSubmittedFileName().isEmpty()) || date.isEmpty() || idR == 0) {
            request.setAttribute("errorEmpty", true);
            System.out.println("errorEmpty");
            flag = false;
        }
        
        if (!flag) {
            System.out.println("se han enviado los errores");
            dispatcher = request.getRequestDispatcher("publicarEdicion.jsp");
            dispatcher.forward(request, response);
        }else{
            InputStream input = part.getInputStream();
            p = new Publicacion();
            p.setFechaPublicacion(LocalDate.parse(date));
            p.setIdrevista(idR);
            p.setNoEdicion(publiDAO.getNoEdicionSig(idR));
            p.setPdf(input);
            publiDAO.create(p);
            dispatcher = request.getRequestDispatcher("publicarEdicion.jsp");
            dispatcher.forward(request, response);
        }
    }
}