package model;

import model.enums.Roles;

import java.time.LocalDate;

public class User extends BaseModel{
    private String first_name; //NOT NULL
    private String last_name; //NOT NULL
    private String email; //NOT NULL
    private String password; //NOT NULL
    private String driver_license;//NOT NULL
    private LocalDate birth_date; //NOT NULL
    private Roles role; //NOT NULL


    //CONSTRUCTOR
    public User(String first_name, String last_name, String email, String password, String driver_license, LocalDate birth_date, Roles role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email.toLowerCase();
        this.password = password;
        this.driver_license = driver_license;
        this.birth_date = birth_date;
        this.role = role;

    }

    public User() {

    }


    //GETTER/SETTER

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver_license() {
        return driver_license;
    }

    public void setDriver_license(String driver_license) {
        this.driver_license = driver_license;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }
}
