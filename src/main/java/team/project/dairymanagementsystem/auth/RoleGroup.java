package team.project.dairymanagementsystem.auth;

import javax.persistence.*;

@Entity
@Table(name = "roleGroup")
public class RoleGroup {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "userId", nullable = false)
    private int userId;
    @Column(name = "role")
    private String role;

    public RoleGroup() {
    }

    public RoleGroup(int userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleGroup{" +
                "id=" + id +
                ", userId=" + userId +
                ", role='" + role + '\'' +
                '}';
    }
}
