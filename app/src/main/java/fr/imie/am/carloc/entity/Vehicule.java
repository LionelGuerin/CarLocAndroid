package fr.imie.am.carloc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tact on 28/11/16.
 */

public class Vehicule implements Serializable{

    private long id;
    private String brand;
    private String model;
    private Date dateCreated;
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
