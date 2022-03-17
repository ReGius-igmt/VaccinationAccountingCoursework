package ru.regiuss.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Simple.class)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 45)
    @JsonView(Views.Simple.class)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    @JsonView(Views.Simple.class)
    private String lastName;

    @Column(name = "patronymic", length = 45)
    @JsonView(Views.Simple.class)
    private String patronymic;

    @Column(name = "sex", nullable = false)
    @JsonView(Views.Simple.class)
    private Integer sex;

    @Column(name = "phone", nullable = false, length = 11)
    @JsonView(Views.Simple.class)
    private String phone;

    @Column(name = "pass", nullable = false, length = 128)
    @JsonIgnore
    private String pass;

//    @ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    //@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "role", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="user_roles")
    @Column(name="role")
    private Set<Role> roles = new HashSet<Role>();

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role){
        roles.add(role);
    }
}