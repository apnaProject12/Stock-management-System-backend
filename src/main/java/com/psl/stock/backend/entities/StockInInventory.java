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
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Inventory_In")
public class StockInInventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_id")
	private Long id;

	@Column(name = "stock_from")
	@NotBlank(message = "from is required")
	private String from;
	@NotBlank(message = "receivedBy is required")
	private String recivedBy;
	@NotNull(message = "Receiving Date is required")
	private String recivedDate;
	@Positive(message = "total Qty must be greater than 0")
	@NotNull(message = "receivedBy is required")
	private int  totalQty;
    @Positive(message = "total product must be greater than 0")
	private String totalProduct;

    @Positive(message = "total product must be greater than 0")
	private String totalPrice;
	@NotNull(message = "supplier is required")
	private String supplier;
	@NotNull(message = "supplyMedium is required")
	private String supplyMedium;
	@NotNull(message = "OrderDate is required")
	private String orderdate;
	@NotNull(message = "orderBy is required")
	private String orderBy;


	private Boolean isApproved;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
	private List<StockInventoryItem> stockInventoryItems;


}
