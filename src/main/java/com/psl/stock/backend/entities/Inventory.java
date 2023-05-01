package com.psl.stock.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private Long productQty;

    
}
