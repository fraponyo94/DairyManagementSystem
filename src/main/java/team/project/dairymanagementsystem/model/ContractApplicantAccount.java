package team.project.dairymanagementsystem.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contract_Applicant_Account")
public class ContractApplicantAccount extends Authenticate {

    @Id
    @Column(name = "username",length = 30)
    private String username;

    @Column(name = "password",length = 70)
    private String password;

    @Column( name = "email",length = 32)
    @Email(message = "Please provide a valid email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "roleGroupId")
    private RoleGroup role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ContractApplicant applicant;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "account")
    private List <ContractApplication> application;


    public ContractApplicantAccount() {
    }

    public ContractApplicantAccount(String username, String password, String email, boolean enabled, RoleGroup role, ContractApplicant applicant,
                                    List<ContractApplication> application) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
        this.applicant = applicant;
        this.application = application;
    }

    public ContractApplicant getApplicant() {
        return applicant;
    }

    public void setApplicant(ContractApplicant applicant) {
        this.applicant = applicant;
    }

    public List<ContractApplication> getApplication() {
        return application;
    }

    public void setApplication(List<ContractApplication> application) {
        this.application = application;
    }

    public RoleGroup getRole() {
        return role;
    }

    public void setRole(RoleGroup role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public String toString() {
        return "ContractApplicantAccount{" +
                "username='" + username + '\'' +
                '}';
    }
}
