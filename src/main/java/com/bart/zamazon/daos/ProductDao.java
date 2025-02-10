package com.bart.zamazon.daos;

import com.bart.zamazon.entitys.Product;
import com.bart.zamazon.exceptions.ResourceNotFoundException;
import org.springframework.core.PriorityOrdered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private JdbcTemplate jdbcTemplate;
    public ProductDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Product> productRowMapper =(rs,_)-> new Product(
            rs.getInt("product_id"),
            rs.getString("product_name"),
            rs.getString("poster_path"),
            rs.getString("description"),
            rs.getDouble("price"),
            rs.getInt("quantity")
    );
    public List<Product> findAllProduct(){
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql,productRowMapper);
    }
    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE product_id = ?";
        return jdbcTemplate.query(sql, productRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Produit avec l'ID : " + id + " n'existe pas"));
    }
    public Product findByName(String product_name) {
        String sql = "SELECT * FROM product WHERE product_name = ?";
        return jdbcTemplate.query(sql, productRowMapper, product_name)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Produit : " + product_name + " n'existe pas"));
    }
    public Product saveProduct(Product product) {

        String sql = "INSERT INTO product (product_name, poster_path, description, price, quantity) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getProduct_name(), product.getPoster_path(),product.getDescription(),product.getPrice(),product.getQuantity());

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        int id = jdbcTemplate.queryForObject(sqlGetId, int.class);

        product.setProduct_id(id);
        return product;
    }
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
    public Product update(int id, Product product) {
        if (!productExists(id)) {
            throw new ResourceNotFoundException("Produit avec l'ID : " + id + " n'existe pas");
        }

        String sql = "UPDATE product SET product_name = ?, poster_path = ?, description = ?, price = ?, quantity = ? WHERE product_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, product.getProduct_name(), product.getPoster_path(),product.getDescription(),product.getPrice(),product.getQuantity(), id);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour du produit avec l'ID : " + id);
        }

        return this.findById(id);
    }
    private boolean productExists(int id) {
        String checkSql = "SELECT COUNT(*) FROM product WHERE product_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, id);
        return count > 0;
    }


}
