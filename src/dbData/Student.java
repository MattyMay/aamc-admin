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
public class Student implements Comparable {

    private int id;
    private String name;
    private int instructorID;
    private String instructor;
    private int instrumentID;
    private Instrument instrument;
    boolean active;

    public Student() {

    }

    public Student(ResultSet rs) throws SQLException {
        this.id = rs.getInt("ID");
        this.name = rs.getString("student_name");
        this.instrumentID = rs.getInt("instrumentID");
        String instrumentStr = rs.getString("instrument");
        this.active = rs.getBoolean("active");
        switch (instrumentStr) {
            case "guitar":
                this.instrument = Instrument.GUITAR;
                break;
            case "drums":
                this.instrument = Instrument.DRUMS;
                break;
            case "vocal":
                this.instrument = Instrument.VOCAL;
                break;
            case "bass":
                this.instrument = Instrument.BASS;
                break;
        }
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int compareTo(Object o) {
        Student st = (Student) o;
        return this.name.compareTo(st.name);
    }
}
