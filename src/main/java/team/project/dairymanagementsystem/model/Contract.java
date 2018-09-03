
package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "supplierId", nullable = false, unique = true)
    private int supplierId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "amountPerDay", nullable = false)
    private int amountPerDay;

    @Column(name = "costPerLitre", nullable = false)
    private int costPerLitre;

    @Column(name = "description", nullable = false)
    private String description;


    @ManyToOne
    @JoinColumn(name = "tenderInfo",nullable = false)
    private TenderInfo tenderInfoId;

    public Contract() {
    }

    public Contract(int supplierId, String status, int amountPerDay, int costPerLitre, String description, TenderInfo tenderInfoId) {
        this.supplierId = supplierId;
        this.status = status;
        this.amountPerDay = amountPerDay;
        this.costPerLitre = costPerLitre;
        this.description = description;
        this.tenderInfoId = tenderInfoId;
    }

    public TenderInfo getTenderInfoId() {
        return tenderInfoId;
    }

    public void setTenderInfoId(TenderInfo tenderInfoId) {
        this.tenderInfoId = tenderInfoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", status='" + status + '\'' +
                ", amountPerDay=" + amountPerDay +
                ", costPerLitre=" + costPerLitre +
                ", description='" + description + '\'' +
                ", tenderInfoId=" + tenderInfoId +
                '}';
    }
}
