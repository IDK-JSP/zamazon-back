package com.bart.zamazon.controller;

import com.bart.zamazon.daos.OrdersContentDao;
import com.bart.zamazon.entitys.OrdersContent;
import com.bart.zamazon.entitys.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/orderContent")
public class OrdersContentController {
    private final OrdersContentDao ordersContentDao;

    public OrdersContentController(OrdersContentDao ordersContentDao) {
        this.ordersContentDao = ordersContentDao;
    }
    @GetMapping("/all")
    public ResponseEntity<List<OrdersContent>> getOrdersContent(){
        return ResponseEntity.ok(ordersContentDao.findAllOrdersContent());
    }
    @GetMapping("/search")
    public ResponseEntity<List<OrdersContent>> getOrderContentById(@RequestParam int query){
        return  ResponseEntity.ok(Collections.singletonList((OrdersContent) ordersContentDao.findOrderContentByOrderId(query)));
    }

    @PostMapping
    public ResponseEntity<OrdersContent> createOrderContent(@Valid @RequestBody OrdersContent ordersContent) {
        OrdersContent createdOrderContent = ordersContentDao.saveOrderContent(ordersContent);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderContent);
    }
}
