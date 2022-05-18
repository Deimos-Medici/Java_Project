package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "photo")
    private String photo;
    @Expose
    @Column(name = "address")
    private String address;
    @Expose
    @Column(name = "home")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    private String mobilePhone;
    @Expose
    @Column(name = "work")
    private String workPhone;
    @Expose
    @Column(name = "email")
    private String firstMail;
    @Expose
    @Column(name = "email2")
    private String secondMail;
    @Expose
    @Column(name = "email3")
    private String thirdMail;

 //   private int group = Integer.MAX_VALUE;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id")
            ,inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    public Groups getGroups() {
        return new Groups(groups);
    }


    public ContactData withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getPhoto() {
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



  //  public ContactData withAllPhones(String allPhones) {
   //     this.allPhones = allPhones;
 //       return this;
  //  }

 //   public String getAllPhones() {
  //      return allPhones;
  //  }

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

    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public String getAddress(){
        return address;
    }

  //  public String getAllMails(){
   //     return allMails;
   // }

    public int getId(){
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

 //   public int getGroup(){
   //     return group;
    //}

  //  public ContactData withGroup(int group) {
  //      this.group = group;
   //     return this;
   // }


    public ContactData withFirstName(String firstname) {
        this.firstname = firstname;
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


 //   public ContactData withAllMails(String mail) {
  //      this.allMails = mail;
  //      return this;
  //  }

    @Override
    public String toString() {
        return "NewContactInfo{" +
                "id=" + id +
                ", name='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(address, that.address) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(firstMail, that.firstMail) && Objects.equals(secondMail, that.secondMail) && Objects.equals(thirdMail, that.thirdMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, homePhone, mobilePhone, workPhone, firstMail, secondMail, thirdMail);
    }

    public ContactData inGroup(GroupData group){
        groups.add(group);
    return this;
    }


}