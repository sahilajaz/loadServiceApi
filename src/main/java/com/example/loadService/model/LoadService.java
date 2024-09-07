package com.example.loadService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Entity
@Getter
@Setter
public class LoadService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loadingPoint;
    private String unloadingPoint;
    private String productType;
    private String truckType;
    private Long noOfTrucks;
    private Long weight;

    @Column(nullable = true)
    private String comment;

    @Column(nullable = false, unique = true)
    private String shipperId;

    private String date;

    @PrePersist
    public void prePersist() {
        this.shipperId = "shipper:" + UUID.randomUUID().toString();
    }
}
