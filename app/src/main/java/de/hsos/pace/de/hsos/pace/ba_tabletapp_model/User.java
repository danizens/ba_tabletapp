package de.hsos.pace.de.hsos.pace.ba_tabletapp_model;

/**
 * Created by danielzens on 27.01.17.
 */

public class User {
    private int id;
    private String username;
    private String passwort;

    public User(){

    }

    public User(String username, String passwort){
        this.username = username;
        this.passwort = passwort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
