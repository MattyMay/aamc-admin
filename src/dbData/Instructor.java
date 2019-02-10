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
public class Instructor implements Comparable{

    private int id;
    private String name;
    private int instrumentID;
    private Instrument instrument;

    public Instructor() {

    }

    public Instructor(ResultSet rs) throws SQLException {
        this.id = rs.getInt("ID");
        this.name = rs.getString("instructor_name");
        this.instrumentID = rs.getInt("instrumentID");
        String instrumentStr = rs.getString("instrument");
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

    public int getId() {
        return id;
    }

    public int getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
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

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public int compareTo(Instructor o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public int compareTo(Object o) {
        Instructor inst = (Instructor) o;
        return this.name.compareTo(inst.name);
    }
}
