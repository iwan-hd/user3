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

public class T_Counter {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable=false, unique = true)
    private String periode;   // periode= 202001

    @Column(nullable=false ,columnDefinition =  "integer default 0")
    private Integer Counter;     // 0

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Integer getCounter() {
        return Counter;
    }

    public void setCounter(Integer counter) {
        Counter = counter;
    }

    @Override
    public String toString() {
        return "T_Counter{" +
                "Id=" + Id +
                ", periode='" + periode + '\'' +
                ", Counter=" + Counter +
                '}';
    }
}
