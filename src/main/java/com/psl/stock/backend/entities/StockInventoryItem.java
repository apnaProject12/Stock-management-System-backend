package com.psl.stock.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Inventory_Item")
public class StockInventoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@NotNull(message = "product Qty is required")
	@Positive(message = "product Qty must be greater than 0")
	private int productQty;
	@NotNull(message = "price is required")
	@Positive(message = "price must be greater than 0")
	private Long price;
	@NotNull(message = "total price  is required")
	@Positive(message = "total price must be greater than 0")
	private Long totalPrice;
	@NotNull(message = "product Name is required")
	private String productName;
}
