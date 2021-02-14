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
        return "GrSub{" +
                "Id=" + Id +
                ", IdGr=" + IdGr +
                ", IdPn=" + IdPn +
                ", Qty=" + Qty +
                ", UnitPrice=" + UnitPrice +
                ", Amount=" + Amount +
                '}';
    }
}

