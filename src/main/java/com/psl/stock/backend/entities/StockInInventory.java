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


@Entity
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
	@JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
	private List<StockInventoryItem> stockInventoryItems;

	public Long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getRecivedBy() {
		return recivedBy;
	}

	public String getRecivedDate() {
		return recivedDate;
	}

	public String getTotalQty() {
		return totalQty;
	}

	public String getTotalProduct() {
		return totalProduct;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	
	public List<StockInventoryItem> getStockInventoryItems() {
		return stockInventoryItems;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setRecivedBy(String recivedBy) {
		this.recivedBy = recivedBy;
	}

	public void setRecivedDate(String recivedDate) {
		this.recivedDate = recivedDate;
	}

	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}

	public void setTotalProduct(String totalProduct) {
		this.totalProduct = totalProduct;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public void setStockInventoryItems(List<StockInventoryItem> stockInventoryItems) {
		this.stockInventoryItems = stockInventoryItems;
	}

}
