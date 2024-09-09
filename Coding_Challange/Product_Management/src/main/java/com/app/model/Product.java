package com.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product { 
	@NotNull(message = "ID cannot be null")
	private Long id;
	
	@NotBlank(message = "Title cannot be blank")
	private String title;
	
	@NotBlank(message = "Description cannot be blank")
	private String description;
	
	@NotNull(message = "Price cannot be null")
	private Double price;
	
	@NotBlank(message = "Category cannot be blank")
	private String category;
	
	@NotBlank(message = "Image cannot be blank")
	private String image;
}
