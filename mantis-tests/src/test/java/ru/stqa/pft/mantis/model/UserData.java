package ru.stqa.pft.mantis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "mantis_user_table")
public class UserData {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "email")
    private String email;

    public void withId(int id) {
        this.id = id;
    }

    public void withName(String name) {
        this.name = name;
    }

    public void withEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
