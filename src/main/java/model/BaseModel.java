package model;

import java.time.LocalDateTime;

public class BaseModel {

    private Long id;
    private LocalDateTime currentDate;
    private LocalDateTime updatedDate;
    private long createdUser;
    private long updatedUser;



    //GETTER/SETTER METHODS:


    public long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(long createdUser) {
        this.createdUser = createdUser;
    }

    public long getUpdatedUser() {

        return updatedUser;
    }

    public void setUpdatedUser(long updatedUser) {

        this.updatedUser = updatedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = LocalDateTime.now();
    }


    //TO STRING


    @Override
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                ", currentDate=" + currentDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
