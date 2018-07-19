package team.project.dairymanagementsystem.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contract_Applicant_Account")
public class ContractApplicantAccount {

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

    public ContractApplicantAccount() {
    }

    public ContractApplicantAccount(String username, String password, String email, boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
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
