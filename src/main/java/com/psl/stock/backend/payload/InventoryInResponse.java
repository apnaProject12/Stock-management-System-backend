package com.psl.stock.backend.payload;

import java.util.List;
import com.psl.stock.backend.entities.StockInInventory;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
public class InventoryInResponse {
	
	private List<StockInInventory> content;
	private int pageNumber;
	private int pageSize;
	private Long totalElements;
	private int totalPages;
	private boolean lastPage;

}
