package com.psl.stock.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class InventoryOutItem {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OutItem_id")
    private Long id;

    @Column(name="OutItem_pname")
    @NotBlank(message = "product name From is required")
    private String productName;
    
    @Column(name="OutItem_totalQty")
    @Positive(message = "total Qty must be greater than 0")
    @NotNull(message = "total Qty  is required")
    private int totalQty;
   






    
}