package com.bart.zamazon.controller;

import com.bart.zamazon.daos.OrdersContentDao;
import com.bart.zamazon.daos.OrdersDao;
import com.bart.zamazon.entitys.Orders;
import com.bart.zamazon.entitys.OrdersContent;
import com.bart.zamazon.entitys.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

public class OrdersController {
    private final OrdersDao ordersDao;

    public OrdersController(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok(ordersDao.findAllOrders());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Orders>> getOrdersByEmail(@RequestParam String query) {
        List<Orders> orders = ordersDao.findAllOrdersByEmail(query);

        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retourne un code 404 si aucune commande n'est trouvée
        }

        return ResponseEntity.ok(orders);
    }

}
