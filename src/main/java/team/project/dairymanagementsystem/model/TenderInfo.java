package team.project.dairymanagementsystem.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "tenderinfo")
public class TenderInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Tender title required")
    @Column(name = "TenderTitle",length = 100)
    private String tenderTitle;

    @NotEmpty( message = "Please provide tender requirements")
    @Lob
    @Column(name = "tenderrequirements",columnDefinition = "TEXT")
    private String tenderRequirements;


    @NotEmpty( message = "provide tender description")
    @Lob
    @Column(name = "tenderdescription",columnDefinition = "TEXT")
    private String tenderDescription;

     @Lob
    @Column(name = "fileAttachment")
    private byte[] fileAttachment;

     @Column(name="status")
     private  boolean status;

    public TenderInfo(){

    }

    public TenderInfo(String tenderTitle, String tenderRequirements,
                      String tenderDescription, byte[] fileAttachment, boolean status) {
        this.tenderTitle = tenderTitle;
        this.tenderRequirements = tenderRequirements;
        this.tenderDescription = tenderDescription;
        this.fileAttachment = fileAttachment;
        this.status = status;
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

    @Override
    public String toString() {
        return "TenderInfo{" +
                "tenderTitle='" + tenderTitle + '\'' +
                '}';
    }
}
