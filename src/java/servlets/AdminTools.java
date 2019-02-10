/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import authentication.Authenticator;
import dbData.QuerieHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc.ConnectionPool;

/**
 *
 * @author mattm
 */
public class AdminTools extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method. Shouldn't happen.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("shouldn't do get except upon first landing.");
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/secured/home.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorMsg = "";
        String destination;
        String action = request.getParameter("action");
        ConnectionPool cp = (ConnectionPool) this.getServletContext().getAttribute("connectionPool");
        Connection connection = null;
        
        try {
            connection = cp.getConnection();
            QuerieHandler lq = new QuerieHandler(request, connection);
            lq.doQueries(action);
        } catch (SQLException | NullPointerException e) {
            errorMsg = e.toString();
            request.setAttribute("errorMsg", errorMsg);
        }
        
        if (action != null) {
            switch (action) {
                case "login":
                    boolean userIsValid;
                    try {
                        Authenticator auth = new Authenticator(request);
                        userIsValid = auth.doAuthenticate();
                    } catch (SQLException e) {
                        System.out.print(e);
                        request.setAttribute(errorMsg, e.toString());
                        userIsValid = false;
                    }
                    if (!userIsValid) {
                        destination = "/login.jsp";
                        break;
                    }
                    destination = "/secured/home.jsp";
                    break;
                case "addPayment":
                    destination = "/secured/addPayment.jsp";
                    break;
                case "newStudent":
                    destination = "/secured/newStudent.jsp";
                    break;
                case "viewPayments":
                    destination = "/secured/viewPayments.jsp";
                    break;
                default:
                    destination = "/secured/home.jsp";
            }
        } else {
            destination = "/secured/home.jsp";
        }
        cp.free(connection);
//        request.setAttribute("errorMsg", errorMsg);
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(destination);
        dispatcher.forward(request, response);
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
