package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Supplier  {
    @Id
    @Column(name = "national_id")
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
    @Column(name = "pic")
    private byte[] pic;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contract")
    private Contract contract;

    public Supplier(){

    }

    public Supplier(int nationalId, @NotNull String address, @NotNull String email_address, @NotNull int phone, @NotNull byte[] pic, Contract contract) {
        this.nationalId = nationalId;
        this.address = address;
        this.email_address = email_address;
        this.phone = phone;
        this.pic = pic;
        this.contract = contract;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public int getNationalId() {
        return nationalId;
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

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public byte[] getPic() {
        return pic;
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
                "nationalId=" + nationalId +

                '}';
    }
}
