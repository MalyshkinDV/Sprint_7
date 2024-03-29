package models.request;

import models.Model;

public class CourierModel implements Model {
    private String login;
    private String password;
    private String firstName;
    private int id;

    public CourierModel(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierModel(int id) {
        this.id = id;
    }

    public CourierModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
