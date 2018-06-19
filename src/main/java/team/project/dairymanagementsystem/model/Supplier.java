package team.project.dairymanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity

public class Supplier  {
    @Id
    @Column(name = "nationalId")
    private int national_id;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "emailAddress")
    private String email_address;

    @NotNull
    @Column(name = "phone")
    private int phone;

    public Supplier(){

    }
    public Supplier(int national_id, String address, String email_address, int phone){
        this.national_id = national_id;
        this.address = address;
        this.email_address = email_address;
        this.phone = phone;
    }
    public void setNational_id(int national_id) {
        this.national_id = national_id;
    }

    public int getNational_id() {
        return national_id;
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

    @Override
    public String toString() {
        return super.toString();
    }
}
