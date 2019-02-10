/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mattm
 */
public class QuerieHandler {

    private Connection connection = null;
    private HttpServletRequest request = null;

    public QuerieHandler(HttpServletRequest request, Connection connection) {
        this.connection = connection;
        this.request = request;
    }

    public void doQueries(String action) throws SQLException, NullPointerException {
        switch (action) {
            case "addPayment":
                this.loadStudents();
                this.loadInstructors();
                if (this.request.getParameter("submit").equals("Submit")) {
                    this.addPayment();
                }
                break;
            case "newStudent":
                this.loadInstructors();
                if (this.request.getParameter("submit").equals("Submit")) {
                    this.registerStudent();
                }
                break;
            case "viewPayments":
                this.loadStudents();
                this.loadInstructors();
                this.loadPayments();
                break;
        }
    }

    private void loadInstructors() throws SQLException, NullPointerException {
        ResultSet rs;
        ArrayList<Instructor> instructorArr = new ArrayList<>();
        PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM instructors "
                + "INNER JOIN instruments ON instructors.instrumentID=instruments.ID;");
        rs = ps.executeQuery();
        while (rs.next()) {
            Instructor inst = new Instructor(rs);
            instructorArr.add(inst);
            Collections.sort(instructorArr);
        }
        this.request.setAttribute("instructorArr", instructorArr);
    }

    private void loadStudents() throws SQLException, NullPointerException {
        ResultSet rs;
        ArrayList<Student> studentArr = new ArrayList<>();
        PreparedStatement ps = this.connection.prepareStatement("SELECT students.ID, "
                + "students.student_name, students.active, instructors.instructor_name, "
                + "students.instructorID, instruments.instrument, students.instrumentID "
                + "FROM students INNER JOIN instructors ON students.instructorID=instructors.ID "
                + "INNER JOIN instruments ON students.instrumentID = instruments.ID;");

        rs = ps.executeQuery();
        while (rs.next()) {
            Student st = new Student(rs);
            studentArr.add(st);
            Collections.sort(studentArr);
        }
        this.request.setAttribute("studentArr", studentArr);
    }

    private void loadPayments() throws SQLException, NullPointerException {
        ResultSet rs;
        ArrayList<Payment> paymentArr = new ArrayList<>();
        String query = "SELECT payments.ID, "
                + "payments.number_lessons, payments.month, payments.year, payments.studentID, "
                + "payments.instructorID, students.student_name, instructors.instructor_name "
                + "FROM payments INNER JOIN students ON payments.studentID=students.ID "
                + "INNER JOIN instructors ON payments.instructorID=instructors.ID ";

        ArrayList<Object> queryVars = new ArrayList<>();
        if (this.request.getParameter("filter") != null) {
            query += "WHERE ";
            String[] filters = this.request.getParameterValues("filter");
            for (String filter : filters) {
                System.out.println(filter);
                switch (filter) {
                    case "student":
                        queryVars.add(request.getParameter("student"));
                        query += "payments.studentID=? AND ";
                        break;
                    case "instructor":
                        queryVars.add(request.getParameter("instructor"));
                        query += "payments.instructorID=? AND ";
                        break;
                    case "month":
                        queryVars.add(Integer.parseInt(request.getParameter("month")));
                        query += "payments.month=? AND ";
                        break;
                    case "year":
                        queryVars.add(Integer.parseInt(request.getParameter("year")));
                        query += "payments.year=? AND ";
                        break;
                }
            }
            query += "1=1;";
        } else {
            query += ";";
        }

        PreparedStatement ps = this.connection.prepareStatement(query);
        for (int i = 0; i < queryVars.size(); i++) {
            Object o = queryVars.get(i);
            if (o instanceof String) {
                ps.setString(i + 1, (String) o);
            } else {
                ps.setInt(i + 1, (int) o);
            }
        }

        rs = ps.executeQuery();
        while (rs.next()) {
            Payment pmnt = new Payment(rs);
            paymentArr.add(pmnt);
            Collections.sort(paymentArr);
        }
        this.request.setAttribute("paymentArr", paymentArr);
    }

    private void addPayment() throws SQLException, NullPointerException {
        int studentID = Integer.parseInt(this.request.getParameter("student"));
        int instructorID = Integer.parseInt(this.request.getParameter("instructor"));
        int number_lessons = Integer.parseInt(this.request.getParameter("number_lessons"));
        int month = Integer.parseInt(this.request.getParameter("month"));
        int year = Integer.parseInt(this.request.getParameter("year"));
        PreparedStatement ps = this.connection.prepareStatement("INSERT INTO payments "
                + "(studentID, instructorID, number_lessons, month, year)"
                + "VALUES (?, ?, ?, ?, ?);");
        ps.setInt(1, studentID);
        ps.setInt(2, instructorID);
        ps.setInt(3, number_lessons);
        ps.setInt(4, month);
        ps.setInt(5, year);
        boolean execute = ps.execute();
    }

    private void registerStudent() throws SQLException, NullPointerException {
        String name = this.request.getParameter("name");
        int instructorID = Integer.parseInt(this.request.getParameter("instructor"));
        int instrumentID = Integer.parseInt(this.request.getParameter("instrument"));
        if (name.length() > 2) {
            PreparedStatement ps = this.connection.prepareStatement("INSERT INTO students (student_name, instructorID, instrumentID, active)"
                    + "VALUES (?, ?, ?, true);");
            ps.setString(1, name);
            ps.setInt(2, instructorID);
            ps.setInt(3, instrumentID);
            boolean execute = ps.execute();
        } else {
            this.request.setAttribute("errorMsg", "Please enter student's full name.");
        }
    }
}
