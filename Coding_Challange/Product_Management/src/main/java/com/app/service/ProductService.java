package com.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.app.model.Product;

@Service
public class ProductService {
	
	@Value("${fakestore.url}")
    private String fakeStoreUrl;
	@Autowired
    private RestTemplate restTemplate;

	// Fetching products by category
    public List<Product> getProductsByCategory(String category) {
        String url = fakeStoreUrl + "/category/" + category;
        try {
            Product[] products = restTemplate.getForObject(url, Product[].class);
            if (products != null) {
                return Arrays.asList(products);
            } else {
                throw new RuntimeException("No products are found in this category: " + category);
            }
        } catch (HttpStatusCodeException e) {
            throw new RuntimeException("Error in  fetching products: " + e.getMessage());
        }
    }

    // Adding a new product
    public Product addProduct(Product product) {
        try {
            Product createdProduct = restTemplate.postForObject(fakeStoreUrl, product, Product.class);
            if (createdProduct != null) {
            	return createdProduct;
            } else {
                throw new RuntimeException("Failed to create a product.");
            }
        } catch (HttpStatusCodeException e) {
            throw new RuntimeException("Error in adding product: " + e.getMessage());
        }
    }


}
