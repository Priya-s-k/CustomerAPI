package com.example.springbootcrudoperation.entity;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Builder
@Entity
@Table
public class CustomerEntity {
    @Id
    @Column
    private  int id;
    @Column
    private String Fname;
    @Column
    private String Lname;
    @Column
    private String Gender;
    @Column
    private long phone;
    @Column
    private String email;
    @Column
    private boolean Is_Active;

    public CustomerEntity() {
        super();
    }

    public CustomerEntity(int id, String fname, String lname, String gender, long phone, String email, boolean is_Active) {
        this.id = id;
        Fname = fname;
        Lname = lname;
        Gender = gender;
        this.phone = phone;
        this.email = email;
        Is_Active = is_Active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIs_Active(boolean is_Active) {
        Is_Active = is_Active;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getGender() {
        return Gender;
    }

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIs_Active() {
        return Is_Active;
    }


}
