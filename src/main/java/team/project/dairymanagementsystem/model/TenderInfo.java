package team.project.dairymanagementsystem.model;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tenderinfo")
public class TenderInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Tender title required")
    @Column(name = "TenderTitle", length = 100)
    private String tenderTitle;

    @NotEmpty(message = "Please provide tender requirements")
    @Lob
    @Column(name = "tenderrequirements", columnDefinition = "TEXT")
    private String tenderRequirements;


    @NotEmpty(message = "provide tender description")
    @Lob
    @Column(name = "tenderdescription", columnDefinition = "TEXT")
    private String tenderDescription;

    @Lob
    @Column(name = "fileAttachment")
    private byte[] fileAttachment;

    @Column(name = "status")
    public boolean status;

    @Column(name = "milkAmount")
    private int milkAmount;

    @Column(name = "totalCost")
    private int totalCost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "deadline")
    private Date deadline;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "tenderInfoId")
    private List<Contract> contracts;

    public TenderInfo() {
    }

    public TenderInfo(String tenderTitle, String tenderRequirements, String tenderDescription, byte[] fileAttachment, boolean status,
                      int milkAmount, int totalCost, Date deadline, List<Contract> contracts) {
        this.tenderTitle = tenderTitle;
        this.tenderRequirements = tenderRequirements;
        this.tenderDescription = tenderDescription;
        this.fileAttachment = fileAttachment;
        this.status = status;
        this.milkAmount = milkAmount;
        this.totalCost = totalCost;
        this.deadline = deadline;
        this.contracts = contracts;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenderTitle() {
        return tenderTitle;
    }

    public void setTenderTitle(String tenderTitle) {
        this.tenderTitle = tenderTitle;
    }

    public String getTenderRequirements() {
        return tenderRequirements;
    }

    public void setTenderRequirements(String tenderRequirements) {
        this.tenderRequirements = tenderRequirements;
    }

    public String getTenderDescription() {
        return tenderDescription;
    }

    public void setTenderDescription(String tenderDescription) {
        this.tenderDescription = tenderDescription;
    }

    public byte[] getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(byte[] fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(int milkAmount) {
        this.milkAmount = milkAmount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "TenderInfo{" +
                "id=" + id +
                ", tenderTitle='" + tenderTitle + '\'' +
                ", tenderRequirements='" + tenderRequirements + '\'' +
                ", tenderDescription='" + tenderDescription + '\'' +
                ", status=" + status +
                ", milkAmount=" + milkAmount +
                ", totalCost=" + totalCost +
                ", deadline=" + deadline +
                '}';
    }
}
