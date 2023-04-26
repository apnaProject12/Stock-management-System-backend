package com.psl.stock.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Add_Product")
@NoArgsConstructor
@Getter
@Setter
public class AddProductEntities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "p_list")
	private String productList;

}
