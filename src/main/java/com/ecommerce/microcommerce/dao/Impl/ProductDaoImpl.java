package com.ecommerce.microcommerce.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	public static List<Product>products=new ArrayList<>();
	    static {
	        products.add(new Product(1, new String("Ordinateur portable"), 350,300));
	        products.add(new Product(2, new String("Aspirateur Robot"), 500,400)); 
	        products.add(new Product(3, new String("Table de Ping Pong"), 750,600));
	    }

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return products;
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		Product product = products.stream()
	            .filter(x -> x.getId() == id)
	            .findFirst()
	            .get();
		return product;
	}

	@Override
	public Product save(Product product) {
		products.add(product);
        return product;
	}

}
