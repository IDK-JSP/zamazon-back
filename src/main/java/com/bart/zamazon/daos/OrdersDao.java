package com.bart.zamazon.daos;

import com.bart.zamazon.entitys.Orders;
import com.bart.zamazon.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDao {
    JdbcTemplate jdbcTemplate;

    public OrdersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Orders> ordersRawMapper = (rs, _)-> new Orders(
            rs.getInt("order_id"),
            rs.getString("email"),
            rs.getDouble("total")
            );
    public List<Orders> findAllOrders() {
        String sql = "SELECT * FROM orders";
        List<Orders> orders = jdbcTemplate.query(sql, ordersRawMapper);
        return orders;
    }
    public List<Orders> findAllOrdersByEmail(String email){
        String sql = "SELECT * FROM orders where email = ?";
        List<Orders> orders = jdbcTemplate.query(sql, ordersRawMapper, email);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Aucune facture à l'email " + email);
        }
        return orders;
    }
    public Orders findLastOrdersByEmail(String email){
        String sql = "SELECT * FROM orders WHERE email = ? ORDER BY order_id DESC LIMIT 1";
        List<Orders> ordersList = jdbcTemplate.query(sql, ordersRawMapper, email);

        if (ordersList.isEmpty()) {
            throw new ResourceNotFoundException("Aucune commande trouvée pour l'email " + email);
        }

        return ordersList.get(0); // Retourner la première commande (la plus récente)
    }


    public Orders saveOrder(Orders orders) {

        String sql = "INSERT INTO orders (email, total) VALUES (?, ?)";
        jdbcTemplate.update(sql, orders.getEmail(),orders.getTotal());

        String sqlGetId = "SELECT LAST_INSERT_ID()";
        int id = jdbcTemplate.queryForObject(sqlGetId, int.class);

        orders.setOrder_id(id);
        return orders;
    }

}
