
package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Lob
    @Column(name = "supply_history",columnDefinition="TEXT")
    private String supplyHistory;

    @Lob
    @Column(name = "reasonforapplication",nullable = false,columnDefinition = "TEXT")
    private String reasonForApplication;

    @Column(name="dateApplied")
    @Temporal(TemporalType.DATE)
    private Date dateApplied;

    @Column(name = "dateApproved")
    @Temporal(TemporalType.DATE)
    private Date dateApproved;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "applicant")
    private ContractApplicantAccount account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "application")
    private List<ContractApplicationDocuments> attachments;


    public ContractApplication() {
    }


    public ContractApplication(String status, int amountPerDay, int costPerLitre, String supplyHistory, String reasonForApplication, Date dateApplied, Date dateApproved, ContractApplicantAccount account,
                               List<ContractApplicationDocuments> attachments) {
        this.status = status;
        this.amountPerDay = amountPerDay;
        this.costPerLitre = costPerLitre;
        this.supplyHistory = supplyHistory;
        this.reasonForApplication = reasonForApplication;
        this.dateApplied = dateApplied;
        this.dateApproved = dateApproved;
        this.account = account;
        this.attachments = attachments;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public ContractApplicantAccount getAccount() {
        return account;
    }

    public void setAccount(ContractApplicantAccount account) {
        this.account = account;
    }

    public List<ContractApplicationDocuments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<ContractApplicationDocuments> attachments) {
        this.attachments = attachments;
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

    public String getSupplyHistory() {
        return supplyHistory;
    }

    public void setSupplyHistory(String supplyHistory) {
        this.supplyHistory = supplyHistory;
    }

    public String getReasonForApplication() {
        return reasonForApplication;
    }

    public void setReasonForApplication(String reasonForApplication) {
        this.reasonForApplication = reasonForApplication;
    }

    @Override
    public String toString() {
        return "ContractApplication{" +
                "id=" + id +
                '}';
    }
}
