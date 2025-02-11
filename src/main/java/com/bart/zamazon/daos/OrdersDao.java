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
        if (orders.isEmpty()){
            throw new ResourceNotFoundException("Aucune facture trouvé");
        }
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

}
