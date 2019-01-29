/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbData;

import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author mattm
 */
public class Payment implements Comparable {
    private int id;
    private int studentID;
    private String studentName;
    private int instructorID;
    private String instructorName;
    private int number_lessons;
    private int month;
    private String monthStr;
    private int year;

    public Payment(ResultSet rs) throws SQLException {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
        this.id = rs.getInt("ID");
        this.studentID = rs.getInt("studentID");
        this.studentName = rs.getString("student_name");
        this.instructorID = rs.getInt("instructorID");
        this.instructorName = rs.getString("instructor_name");
        this.number_lessons = rs.getInt("number_lessons");
        this.month = rs.getInt("month");
        this.monthStr = monthNames[month - 1];
        this.year = rs.getInt("year");
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getNumber_lessons() {
        return number_lessons;
    }

    public void setNumber_lessons(int number_lessons) {
        this.number_lessons = number_lessons;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Object o) {
        Payment payment = (Payment) o;
        if (this.year != payment.year) {
            return payment.year - this.year;
        } else if (this.month != payment.month) {
            return payment.month - this.month;
        } else {
            return payment.number_lessons - this.number_lessons;
        }
    }
    
    @Override
    public String toString() {
        String str = this.studentName + " " + this.instructorName;
        return str;
    }

}
