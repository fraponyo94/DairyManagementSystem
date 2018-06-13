package team.project.dairymanagementsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(name = "supplierId", unique = false)
    private int supplierId;
    @NotNull
    @Column(name = "status")
    private String status;
    @NotNull
    @Column(name = "amountPerDay")
    private int amountPerDay;
    @NotNull
    @Column(name = "costPerLitre")
    private int costPerLitre;

    public Contract() {
    }

    public Contract(@NotNull int supplierId, @NotNull String status, @NotNull int amountPerDay, @NotNull int costPerLitre) {
        this.supplierId = supplierId;
        this.status = status;
        this.amountPerDay = amountPerDay;
        this.costPerLitre = costPerLitre;
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

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", status='" + status + '\'' +
                ", amountPerDay=" + amountPerDay +
                ", costPerLitre=" + costPerLitre +
                '}';
    }
}