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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Inventory_In")
public class StockInInventory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Long id;
	
	
	@Column(name = "stock_from")
	private String from;
	
	
	private String recivedBy;
	
	private String recivedDate;
	
	private String totalQty;
	
	private String totalProduct;
	
	private String totalPrice;
		
	private Boolean isApproved;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_id",referencedColumnName = "stock_id")
	private List<StockInventoryItem> stockInventoryItems;

}
