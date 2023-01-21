package com.fireDetection.cot.entities;
import com.fireDetection.cot.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import com.fireDetection.cot.utils.Argon2Utils;
import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
@Table(name = "user")
public class user implements Serializable {
    @Id
    @Column("email")
    private String email;
    @Column("username")
    private String username;

    @Column("password")
    private String password;
    @Column("userRole")
    private Set<Role> roles;

    public user() {
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public user(String email, String username, String password, Set<Role> roles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user user = (user) o;
        return Objects.equals(email, user.email) && Objects.equals(username, user.username) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, username, password, roles);
    }

    @Override
    public String toString() {
        return "user{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
    public void hashPassword(String password, Argon2Utils argon2Utility) {
        this.password = argon2Utility.hash(password.toCharArray());
    }

}