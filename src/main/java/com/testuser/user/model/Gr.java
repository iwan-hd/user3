package com.testuser.user.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Gr {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable=false, unique = true)
    private String GrCode;   //GrKode= BGR/21/01/001

    @Column(nullable=false)
    private String GrPeriode;  //202101

    @Column(nullable = false, columnDefinition =  "integer default 0")
    private Integer kunci;

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    @Column(nullable=false)
    private String Tanggal;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getGrCode() {
        return GrCode;
    }

    public void setGrCode(String grCode) {
        GrCode = grCode;
    }

    public String getGrPeriode() {
        return GrPeriode;
    }

    public void setGrPeriode(String grPeriode) {
        GrPeriode = grPeriode;
    }

    public Integer getKunci() {
        return kunci;
    }

    public void setKunci(Integer kunci) {
        this.kunci = kunci;
    }

    @Override
    public String toString() {
        return "Gr{" +
                "Id=" + Id +
                ", GrCode='" + GrCode + '\'' +
                ", GrPeriode='" + GrPeriode + '\'' +
                ", kunci=" + kunci +
                ", Tanggal='" + Tanggal + '\'' +
                '}';
    }
}
