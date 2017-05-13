/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtracing.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonel
 */

@WebServlet(urlPatterns = "/MostrarFoto")
public class MostrarFoto extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String $id = req.getParameter("idfoto");
        int myId = Integer.parseInt($id);
        FotoRepuesto myFoto = new FotoRepuesto();
        byte[] foto = myFoto.miFoto(myId);
        myFoto.close();
        resp.getOutputStream().write(foto);
    }
    
}
