package com.testuser.user.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GrSub {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable=false)
    private int IdGr;          // ref ke GR id

    @Column(nullable=false)
    private Integer IdPn;       // ref ke PartNumber Id

    @Column(nullable=false , columnDefinition =  "double default 0")
    private Double Qty;

    @Column(nullable=false , columnDefinition =  "double default 0")
    private Double UnitPrice;

    @Column(nullable=false , columnDefinition =  "double default 0")
    private Double Amount;      // Qty* unitPrice
//
//    public GrSub() {
//    }
//    parameter di sini...harus sama dengan yg di Postman
//    public GrSub(long id, int idGr, int idPn, double qty, double unitPrice, double amount) {
//        this.IdGr = idGr;
//        this.IdPn = idPn;
//        this.Qty = qty;
//        this.UnitPrice = unitPrice;
//        this.Amount = amount;
//    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getIdGr() {
        return IdGr;
    }

    public void setIdGr(int idGr) {
        IdGr = idGr;
    }

    public Integer getIdPn() {
        return IdPn;
    }

    public void setIdPn(Integer idPn) {
        IdPn = idPn;
    }

    public Double getQty() {
        return Qty;
    }

    public void setQty(Double qty) {
        Qty = qty;
    }

    public Double getUnitprice() {
        return UnitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        UnitPrice = unitPrice;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(Id));
        builder.append(", ");
        builder.append(IdGr);
        builder.append(", ");
        builder.append(IdPn);
        builder.append(", ");
        builder.append(Qty);
        builder.append(", ");
        builder.append(UnitPrice);
        builder.append(", ");
        builder.append(Amount);

        return builder.toString();

    }


}

