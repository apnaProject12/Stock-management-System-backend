package com.psl.stock.backend.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Invertory_out")
public class InventoryOut {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "InvertoryOut_itemId")
    private Long id;

    @NotBlank(message = "InventoryOut From is required")
    @Column(name = "InvertoryOut_from")
    private String from;

       @NotBlank(message = "InventoryOut received by is required")
    @Column(name = "InvertoryOut_receivedBy")
    private String receivedBy;

       @NotBlank(message = "InventoryOut received date is required")
    @Column(name = "InvertoryOut_receiveddate")
    private String receivedDate;

       @NotNull(message = "InventoryOut  total Item is required")
       @Positive(message = "total Item must not be zero")
    @Column(name = "InvertoryOut total Item")
    private String totalItem;

       @NotNull(message = "InventoryOut  total qty is required")
       @Positive(message = "total product qty be greater than 0")
    @Column(name = "InvertoryOut total qty")
    private String totalqty;

   @JoinColumn(name="inventoryOutItem_table")
   // @NotBlank
   @OneToMany(cascade = CascadeType.ALL)
    List<InventoryOutItem> inventoryOutItem;



}
  
