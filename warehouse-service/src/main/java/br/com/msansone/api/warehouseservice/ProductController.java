package br.com.msansone.api.warehouseservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.msansone.api.warehouseservice.model.Product;
import br.com.msansone.api.warehouseservice.service.ProductService;

@RestController
@RequestMapping("/api/warehouse/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("")
	public ResponseEntity<List<Product>> getAll(){
		List<Product> lista = productService.getAll();
		return new ResponseEntity<>(lista,HttpStatus.OK);
	}

}
