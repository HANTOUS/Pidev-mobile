/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tevent.Entities;

/**
 *
 * @author Salim
 */
public class Feedback {
    private int id; 
    private int note; 
    private String remarque;
    private int participf;

    public Feedback() {
    }

    public Feedback(int id, int note, String remarque, int participf) {
        this.id = id;
        this.note = note;
        this.remarque = remarque;
        this.participf = participf;
    }

    public Feedback(int note, String remarque) {
        this.note = note;
        this.remarque = remarque;
    }

    
    public int getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public String getRemarque() {
        return remarque;
    }

    public int getParticipf() {
        return participf;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public void setParticipf(int participf) {
        this.participf = participf;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", note=" + note + ", remarque=" + remarque + ", participf=" + participf + '}';
    }
    
    
    
}
