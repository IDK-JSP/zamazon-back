package com.bart.zamazon.controller;

import com.bart.zamazon.daos.ProductDao;
import com.bart.zamazon.entitys.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productDao.findAllProduct());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByName(@PathVariable int id){
        return  ResponseEntity.ok(productDao.findById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam String query) {
        List<Product> products = productDao.findByName(query);

        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retourne un code 404 si aucune commande n'est trouvée
        }

        return ResponseEntity.ok(products);
    }
    @PostMapping
    public ResponseEntity<Product> createMovie(@Valid @RequestBody Product product) {
        Product createdProduct = productDao.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        if (productDao.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = productDao.update(id, product);
        return ResponseEntity.ok(updatedProduct);
    }
}
