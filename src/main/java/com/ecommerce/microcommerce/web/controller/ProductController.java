package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.Impl.ProductDaoImpl;
import com.ecommerce.microcommerce.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class ProductController {
	
	@Autowired 
	ProductDaoImpl productDao;
	
	@RequestMapping(value="/Produits" ,method=RequestMethod.GET)
	public List<Product>  getListProduct() {
		List<Product> products = productDao.findAll();
//		SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
//		FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
//		MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);
//		produitsFiltres.setFilters(listDeNosFiltres);
//		return produitsFiltres;
		return products;
	}

	
	
	
	//@RequestMapping(value="/Produits/{id}" , method=RequestMethod.GET)
	@GetMapping(value="Produits/{id}")
	public Product getProduct(@PathVariable int id) {
		return productDao.findById(id);
	}
	
	@PostMapping(value="/Produits")
	public ResponseEntity<Void> saveProduct(@RequestBody Product product) {
		Product productAdded =  productDao.save(product);
		if (productAdded == null)
			return ResponseEntity.noContent().build();
		URI location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(productAdded.getId())
	                .toUri();
		return ResponseEntity.created(location).build();
	}
}
