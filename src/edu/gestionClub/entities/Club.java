/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionClub.entities;

/**
 *
 * @author 21626
 */
public class Club {
    private int id_club ;
    private String nom_club ;
    private String adresse_club ;
    private String materiel_club ;
    private int place_dispo ;

    public Club(String nom_club, String adresse_club, String materiel_club, int place_dispo) {
        this.nom_club = nom_club;
        this.adresse_club = adresse_club;
        this.materiel_club = materiel_club;
        this.place_dispo = place_dispo;
    }

    public Club() {
    }

    @Override
    public String toString() {
        return "Club{" + "id_club=" + id_club + ", nom_club=" + nom_club + ", adresse_club=" + adresse_club + ", materiel_club=" + materiel_club + ", place_dispo=" + place_dispo + '}';
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public String getAdresse_club() {
        return adresse_club;
    }

    public void setAdresse_club(String adresse_club) {
        this.adresse_club = adresse_club;
    }

    public String getMateriel_club() {
        return materiel_club;
    }

    public void setMateriel_club(String materiel_club) {
        this.materiel_club = materiel_club;
    }

    public int getPlace_dispo() {
        return place_dispo;
    }

    public Club(String nom_club) {
        this.nom_club = nom_club;
    }

    public void setPlace_dispo(int place_dispo) {
        this.place_dispo = place_dispo;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
 
    
}
