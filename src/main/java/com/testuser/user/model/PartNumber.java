package com.testuser.user.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PartNumber {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable=false, unique = true)
    private String partCode;

    @Column(nullable=false)
    private String partName;

     @Column(nullable=false)
     private Double stock;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    @Override
    public String toString() {
        return "PartNumber{" +
                "Id=" + Id +
                ", partCode='" + partCode + '\'' +
                ", partName='" + partName + '\'' +
                '}';
    }
}
