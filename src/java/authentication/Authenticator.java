/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import jdbc.ConnectionPool;

/**
 * Used to check user entered username/password against users in DB before
 * creating a session for user.
 *
 * @author mattm
 */
public class Authenticator {

    private HttpServletRequest request = null;
    private Connection connection = null;
    boolean userIsValid;
    String loginError;

    public Authenticator(HttpServletRequest request) throws SQLException {
        this.request = request;
        ConnectionPool cp;
        cp = (ConnectionPool) request.getServletContext().getAttribute("connectionPool");
        this.connection = cp.getConnection();
    }

    public boolean doAuthenticate() {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM "
                    + "aamc_users WHERE user=? AND pass=?");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            this.userIsValid = rs.next();
            if (!this.userIsValid) {
                this.loginError = "invalid login";
                request.setAttribute("errorMsg", this.loginError);
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            this.loginError = e.toString();
            request.setAttribute("errorMsg", this.loginError);
            return false;
        }
        request.getSession().setAttribute("user", user);
        return true;

    }
}
