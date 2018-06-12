package team.project.dairymanagementsystem.domain;

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
    private short amountPerDay;
    @NotNull
    @Column(name = "costPerLitre")
    private byte costPerLitre;

    public Contract() {
    }

    public Contract(@NotNull int supplierId, @NotNull String status, @NotNull short amountPerDay, @NotNull byte costPerLitre) {
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

    public short getAmountPerDay() {
        return amountPerDay;
    }

    public void setAmountPerDay(short amountPerDay) {
        this.amountPerDay = amountPerDay;
    }

    public byte getCostPerLitre() {
        return costPerLitre;
    }

    public void setCostPerLitre(byte costPerLitre) {
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
