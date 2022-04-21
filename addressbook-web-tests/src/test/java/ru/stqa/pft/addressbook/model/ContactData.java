package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    private String name;
    private String lastname;
    private String address;
    private String allMails;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String firstMail;
    private String secondMail;
    private String thirdMail;
    private File photo;

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public File getPhoto() {
        return photo;
    }

    public ContactData withFirstMail(String firstMail) {
        this.firstMail = firstMail;
        return this;
    }

    public ContactData withSecondMail(String secondMail) {
        this.secondMail = secondMail;
        return this;
    }

    public ContactData withThirdMail(String thirdMail) {
        this.thirdMail = thirdMail;
        return this;
    }

    public String getFirstMail() {
        return firstMail;
    }

    public String getSecondMail() {
        return secondMail;
    }

    public String getThirdMail() {
        return thirdMail;
    }



    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }



    public String getHomePhone() {
        return homePhone;
    }

    public ContactData withHomePhone(String homePhone){
        this.homePhone = homePhone;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public ContactData withMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactData withWorkPhone(String workPhone){
        this.workPhone = workPhone;
        return this;
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

    public String getAllMails(){
        return allMails;
    }

    public int getId(){
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }


    public ContactData withAllMails(String mail) {
        this.allMails = mail;
        return this;
    }

    @Override
    public String toString() {
        return "NewContactInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname);
    }

}