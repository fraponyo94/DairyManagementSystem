package team.project.dairymanagementsystem.model;

import javax.persistence.*;


@Entity
@Table(name = "RoleGroup")
public class RoleGroup {
    @Id
    @Column(name = "roleGroupId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "role")
    private String role;


    public RoleGroup() {
    }

    public RoleGroup(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "role='" + role + '\'' +
                '}';
    }
}
