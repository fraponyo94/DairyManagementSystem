
package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "contract_applications")
public class ContractApplication implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "status",nullable = false)
    private String status;

    @Column(name = "amountPerDay",nullable = false)
    private int amountPerDay;

    @Column(name = "costPerLitre",nullable = false)
    private int costPerLitre;

    @Column(name = "supply_history")
    private String supply_history;

    @Lob
    @Column(name = "reasonforapplication",nullable = false,columnDefinition = "TEXT")
    private String reasonForApplication;

    @Lob
    @Column( name = "nationalId",nullable = false)
    private  byte[] nationalId;

    @Lob
    @Column(name = "attachment")
    private byte[] attachment;

    @ManyToOne
    @JoinColumn(name = "applicant",nullable = false)
    private ContractApplicant applicant;


    public ContractApplication() {
    }

    public ContractApplication(String status, int amountPerDay, int costPerLitre, String supply_history, String reasonForApplication, byte[] nationalId,
                               byte[] attachment, ContractApplicant applicant) {
        this.status = status;
        this.amountPerDay = amountPerDay;
        this.costPerLitre = costPerLitre;
        this.supply_history = supply_history;
        this.reasonForApplication = reasonForApplication;
        this.nationalId = nationalId;
        this.attachment = attachment;
        this.applicant = applicant;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmountPerDay() {
        return amountPerDay;
    }

    public void setAmountPerDay(int amountPerDay) {
        this.amountPerDay = amountPerDay;
    }

    public int getCostPerLitre() {
        return costPerLitre;
    }

    public void setCostPerLitre(int costPerLitre) {
        this.costPerLitre = costPerLitre;
    }

    public String getSupply_history() {
        return supply_history;
    }

    public void setSupply_history(String supply_history) {
        this.supply_history = supply_history;
    }

    public String getReasonForApplication() {
        return reasonForApplication;
    }

    public void setReasonForApplication(String reasonForApplication) {
        this.reasonForApplication = reasonForApplication;
    }

    public byte[] getNationalId() {
        return nationalId;
    }

    public void setNationalId(byte[] nationalId) {
        this.nationalId = nationalId;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public ContractApplicant getApplicant() {
        return applicant;
    }

    public void setApplicant(ContractApplicant applicant) {
        this.applicant = applicant;
    }

    @Override
    public String toString() {
        return "ContractApplication{" +
                "id=" + id +
                '}';
    }
}
