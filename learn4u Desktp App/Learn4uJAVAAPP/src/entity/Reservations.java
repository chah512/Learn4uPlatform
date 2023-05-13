/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author solta
 */
public class Reservations {
    private String EventName;
    private Integer Tel;
    private Integer NbrReservations;

    public Reservations(String EventName, Integer Tel, Integer NbrReservations) {
        this.EventName = EventName;
        this.Tel = Tel;
        this.NbrReservations = NbrReservations;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    

    public Integer getTel() {
        return Tel;
    }

    public Integer getNbrReservations() {
        return NbrReservations;
    }

    public void setTel(Integer Tel) {
        this.Tel = Tel;
    }

    public void setNbrReservations(Integer NbrReservations) {
        this.NbrReservations = NbrReservations;
    }
    
    
}
