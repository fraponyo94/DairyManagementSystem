package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Supplier  {
    @Id
    @Column(name = "nationalId")
    private int nationalId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "emailAddress")
    private String email_address;

    @NotNull
    @Column(name = "phone")
    private int phone;

    @Lob
    @Column(name = "attachment")
    private byte[] attachment;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contract")
    private Contract contract;

    public Supplier(){

    }


    public Supplier(int nationalId, String name, String address, String email_address, int phone, byte[] attachment, Contract contract) {
        this.nationalId = nationalId;
        this.name = name;
        this.address = address;
        this.email_address = email_address;
        this.phone = phone;
        this.attachment = attachment;
        this.contract = contract;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "national_id=" + nationalId +

                '}';
    }
}
