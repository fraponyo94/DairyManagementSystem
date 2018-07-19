package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contract_applicant")
public class ContractApplicant {

    @Id
    @Column(name = "NationalId",length = 15)
    private int nationalId;

    @Column(name = "firstName",length = 50,nullable = false)
    private String firstName;

    @Column(name = "middleName",length = 50,nullable = false)
    private String middleName;

    @Column(name = "lastName",length = 50,nullable = false)
    private String lastName;

    @Column(name = "phoneNumber",length = 15,nullable = false)
    private String phoneNumber;

    @Column(name = "pinNumber",nullable = false)
    private String pinNumber;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    private ContractApplicantAccount account;

    @OneToMany
    private Set<ContractApplicant> application;



    public ContractApplicant() {
    }

    public ContractApplicant(int nationalId, String firstName, String middleName, String lastName, String phoneNumber, String pinNumber,
                             ContractApplicantAccount account, Set<ContractApplicant> application) {
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pinNumber = pinNumber;
        this.account = account;
        this.application = application;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ContractApplicantAccount getAccount() {
        return account;
    }

    public void setAccount(ContractApplicantAccount account) {
        this.account = account;
    }


    public Set<ContractApplicant> getApplication() {
        return application;
    }

    public void setApplication(Set<ContractApplicant> application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "ContractApplicant{" +
                "nationalId=" + nationalId +
                '}';
    }
}
