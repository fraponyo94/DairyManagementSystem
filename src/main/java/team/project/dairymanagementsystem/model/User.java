package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User extends Authenticate implements Serializable{
    @Id
    @Column(name = "nationalId", length = 15, nullable = false)
    private int nationalId;


    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "username",nullable = false)
    private String username;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roleGroupId")
    public RoleGroup roleGroup;


    public User() {
    }

    public User(int nationalId, String password, String username, RoleGroup roleGroup) {
        this.nationalId = nationalId;
        this.password = password;
        this.username = username;
        this.roleGroup = roleGroup;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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

    public void setRoleGroup(RoleGroup roleGroup) {
        this.roleGroup = roleGroup;
    }

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }

    @Override
    public String toString() {
        return "User{" +
                "nationalId=" + nationalId +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}