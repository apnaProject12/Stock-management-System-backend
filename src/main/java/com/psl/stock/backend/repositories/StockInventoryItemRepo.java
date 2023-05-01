package com.psl.stock.backend.repositories;

import java.util.List;
import java.util.Map;

// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.psl.stock.backend.entities.StockInventoryItem;

@Repository
public interface StockInventoryItemRepo extends JpaRepository<StockInventoryItem, Long> {

    @Query(value = "SELECT  sum(product_qty)  as productQty FROM dummy_stock_db.inventory_item where product_name =:product",nativeQuery = true)
   public int getProductwithtotal(String product);


}
