package com.example.ramadhanquizamiraminuddinfadzrolazuaamirasyraf.Model;

public class User
{

    private String Name, Password, result, Admin, Id;

    public User() {
    }

    public User(String name, String password, String Result, String admin, String id) {
        Name = name;
        Password = password;
        result = Result;
        Admin = admin;
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }
}
