package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class NewContactInfo {
    private int id;
    private final String name;
    private final String lastname;
    private final String address;
    private final String phone;
    private final String mail;

    public NewContactInfo(String name, String lastname, String address, String phone, String mail) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
    }

    public NewContactInfo(int id, String name, String lastname, String address, String phone, String mail) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
    }

    public String getName(){
        return name;
    }

    public String getLastname(){
        return lastname;
    }

    public String getAddress(){
        return address;
    }

    public String getPhone(){
        return phone;
    }

    public String getMail(){
        return mail;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewContactInfo that = (NewContactInfo) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname);
    }

    @Override
    public String toString() {
        return "NewContactInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


}