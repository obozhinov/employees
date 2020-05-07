package com.example.employees;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
