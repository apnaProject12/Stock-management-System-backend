package com.psl.stock.backend.entities;

import lombok.*;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OrderIn")
public class OrderIn {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id; 


    private String totalProduct;

    private String totalQuentity;



    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<OrderItem> orderItem;


    
}
