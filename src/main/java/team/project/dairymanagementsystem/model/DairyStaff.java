package team.project.dairymanagementsystem.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import team.project.dairymanagementsystem.model.enumerated.Gender;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name = "DairyStaff")
public class DairyStaff extends Authenticate{

    @Id
    @Column(name = "payrollid", length = 10, nullable = false,unique = true)
    @NotEmpty(message = "Please provide your payroll id.")
    @Pattern(regexp = "\\d{4}", message = "Pay roll number must be at least 4 digits.")
    private String payrollId;

    @Column(name = "staffname", length = 30, nullable = false)
    @Size(min = 3, message = "Name should at least be 3 characters")
    @NotEmpty(message = "Please provide your full name.")
    private String fullName;

    @Email(message = "Invalid email")
    @Column(name = "email", length = 30, nullable = false,unique = true)
    @NotEmpty(message = "Please provide your email")
    private String email;

    @Column(name = "gender",columnDefinition = "enum('MALE','FEMALE') ")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "password",length = 90)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roleGroupId")
    private RoleGroup roleGroup;

    public DairyStaff() {
    }

    public DairyStaff(String payrollId, String fullName,  String email, Gender gender,  RoleGroup roleGroup,String password) {
        this.payrollId = payrollId;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.roleGroup = roleGroup;
    }

    public String getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId = payrollId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(RoleGroup roleGroup) {
        this.roleGroup = roleGroup;
    }

    @Override
    public String toString() {
        return "DairyStaff{" +
                "payrollId='" + payrollId + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
