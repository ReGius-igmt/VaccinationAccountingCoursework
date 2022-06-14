package ru.regiuss.server.model;

import com.fasterxml.jackson.annotation.JsonView;
import ru.regiuss.server.Views;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Simple.class)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    @JsonView(Views.Simple.class)
    private String name;

    @Column(name = "display_name", nullable = false, length = 45)
    @JsonView(Views.Simple.class)
    private String displayName;

    @Column(name = "is_default", nullable = false)
    @JsonView(Views.Simple.class)
    private Boolean isDefault;

    @ManyToMany
    @JoinTable(
            name = "roles_permissions",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")}
    )
    @JsonView(Views.Full.class)
    private Set<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean isDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}