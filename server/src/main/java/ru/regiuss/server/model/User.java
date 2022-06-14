package ru.regiuss.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;

import javax.persistence.*;
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

    @Column(name = "login", nullable = false, length = 45)
    @JsonView(Views.Full.class)
    private String login;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @JsonView(Views.Full.class)
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "staffs_laboratories",
            joinColumns = {@JoinColumn(name = "staff_id")},
            inverseJoinColumns = {@JoinColumn(name = "laboratory_id")}
    )
    @JsonView(Views.Full.class)
    private Set<Laboratory> laboratories;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}