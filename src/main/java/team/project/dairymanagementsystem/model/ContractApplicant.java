package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contract_applicant")
public class ContractApplicant {

    @Id
    @Column(name = "id")
    private String id;

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

   
    @Column(name="postalAddress" ,nullable=false)
    private String postalAddress;

    @MapsId
    @OneToOne(targetEntity = ContractApplicantAccount.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "account")
    private ContractApplicantAccount account;





    public ContractApplicant() {
    }


    public ContractApplicant(String id, int nationalId, String firstName, String middleName, String lastName, String phoneNumber, String postalAddress,
                             ContractApplicantAccount account) {
        this.id = id;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.postalAddress = postalAddress;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ContractApplicantAccount getAccount() {
        return account;
    }

    public void setAccount(ContractApplicantAccount account) {
        this.account = account;
    }




    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Override
    public String toString() {
        return "ContractApplicant{" +
                "nationalId=" + nationalId +
                '}';
    }
}
