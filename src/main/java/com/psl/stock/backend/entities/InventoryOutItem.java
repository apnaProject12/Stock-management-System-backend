package com.psl.stock.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
   
    private String productName;
    
    @Column(name="OutItem_totalQty")
   
    private int totalQty;
   






    
}
