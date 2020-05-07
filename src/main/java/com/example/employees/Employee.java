package com.example.employees;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long SSN;
    private String firstname;
    private String lastname;
    private Date start_date;
    private Date end_date;
    private Date created_at;
    private Date updated_at;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public Long getSSN() {
        return SSN;
    }

    public void setSSN(Long SSN) {
        this.SSN = SSN;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @PrePersist
    protected void onCreate(){this.created_at = new Date();}

    @PreUpdate
    protected void onUpdate() {this.updated_at = new Date();}
}
