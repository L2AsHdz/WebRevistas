/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashdz.webrevistas.servlets;

import com.ashdz.webrevistas.DAO.EtiquetadoDAO.EtiquetadoDAO;
import com.ashdz.webrevistas.DAO.EtiquetadoDAO.EtiquetadoDAOImpl;
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
@WebServlet(name = "EtiquetarRevistaController", urlPatterns = {"/EtiquetarRevistaController"})
public class EtiquetarRevistaController extends HttpServlet {
    private String[] etiquetas;
    private EtiquetadoDAO etiquetaDAO;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        etiquetaDAO = EtiquetadoDAOImpl.getEtiqueadoDAO();
        etiquetas = request.getParameterValues("etiquetas");
        Revista rev = (Revista)request.getSession().getAttribute("revista");
        RequestDispatcher dispatcher;
        if (etiquetas != null) {
            try {
                int[] idsE = new int[etiquetas.length];
                int i = 0;
                for (String e : etiquetas) {
                    idsE[i] = Integer.parseInt(e);
                    i++;
                }
                for (int j : idsE) {
                    System.out.println(rev.getIdRevista()+","+j);
                    etiquetaDAO.createEtiquetado(j, rev.getIdRevista());
                }
                request.setAttribute("succesE", true);
                dispatcher = request.getRequestDispatcher("buscarRevistaEditor.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }else{
            request.setAttribute("errorNull", true);
            dispatcher = request.getRequestDispatcher("etiquetarRevista.jsp");
            dispatcher.forward(request, response);
        }
    
    }
}
