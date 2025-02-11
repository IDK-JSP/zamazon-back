package com.bart.zamazon.controller;

import com.bart.zamazon.daos.OrdersContentDao;
import com.bart.zamazon.daos.OrdersDao;
import com.bart.zamazon.entitys.Orders;
import com.bart.zamazon.entitys.OrdersContent;
import com.bart.zamazon.entitys.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/orders")
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
    @PostMapping
    public ResponseEntity<Orders> createOrder(@Valid @RequestBody Orders orders) {
        Orders createdOrder = ordersDao.saveOrder(orders);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

}
