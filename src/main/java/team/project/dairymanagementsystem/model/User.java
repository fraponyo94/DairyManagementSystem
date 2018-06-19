package team.project.dairymanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @Column(name = "nationalId", nullable = false, unique = true)
    private int nationalId;
    @NotNull
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(@NotNull int nationalId, @NotNull String password) {
        this.nationalId = nationalId;
        this.password = password;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "nationalId=" + nationalId +
                ", password='" + password + '\'' +
                '}';
    }
}