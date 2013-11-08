/*
 * Copyright (c) 2013 Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
package br.eti.romel.rfs.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LerArquivoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        byte[] buffer = new byte[KILOBYTE];
        FileOutputStream fos = new FileOutputStream("/Users/cromel/NetBeansProjects/RFS/uploaded.jpg");

        while (request.getInputStream().read(buffer) > 0) {
            fos.write(buffer);
        }

        fos.close();
        Map<String, String> parameterMap = request.getParameterMap();
        out.print("<table border='1'>");
        for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
            String chave = entry.getKey();
            String valor = entry.getValue();
            out.println(String.format("<tr><td>Chave</td><td>%s</td><td>Valor</td><td>%s</td></tr>", chave, valor));
        }
        out.println("</table>");
        try {

            System.out.print(request.getInputStream().toString());
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LerArquivoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LerArquivoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    private static final int KILOBYTE = 10240;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
