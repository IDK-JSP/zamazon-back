package com.bart.zamazon.daos;

import com.bart.zamazon.entitys.OrdersContent;
import com.bart.zamazon.entitys.Product;
import com.bart.zamazon.exceptions.ResourceNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersContentDao {
    JdbcTemplate jdbcTemplate;
    public OrdersContentDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<OrdersContent> ordersContentRowMapper = (rs, _)-> new OrdersContent(
            rs.getInt("order_id"),
            rs.getInt("product_id"),
            rs.getInt("quantity")
    );
    public List<OrdersContent> findAllOrdersContent(){
        String sql = "SELECT * FROM orders_content";
        return jdbcTemplate.query(sql,ordersContentRowMapper);
    }
    public List<OrdersContent> findOrderContentByOrderId(int order_id) {
        String sql = "SELECT * FROM orders_content WHERE order_id = ?";

        // Récupérer tous les éléments de orders_content pour un order_id donné
        List<OrdersContent> orderContents = jdbcTemplate.query(sql, ordersContentRowMapper, order_id);

        // Vérifier si des résultats ont été trouvés
        if (orderContents.isEmpty()) {
            throw new ResourceNotFoundException("Facture avec l'id : " + order_id + " n'existe pas");
        }

        return orderContents;
    }
    public OrdersContent saveOrderContent(OrdersContent ordersContent) {

        String sql = "INSERT INTO orders_content (order_id, product_id, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ordersContent.getOrder_id(), ordersContent.getProduct_id(),ordersContent.getQuantity());

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        int id = jdbcTemplate.queryForObject(sqlGetId, int.class);

        ordersContent.setProduct_id(id);
        return ordersContent;
    }

}
