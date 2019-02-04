package com.pizza.shop.PizzaApplication.controller;

import com.pizza.shop.PizzaApplication.dao.ReadAndWriteDao;
import com.pizza.shop.PizzaApplication.model.Item;
import com.pizza.shop.PizzaApplication.service.DateSortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.Instant;
import java.util.*;

/**
 * Created by sangharatna.davane on 1/30/2019.
 */

@RestController
public class OrderRecivedController {

    ReadAndWriteDao dao;

    @GetMapping(path="/getSortedData", produces = "application/json")
       public ResponseEntity<?> getSortedOrderDetails() throws IOException {

        try {
            List<Item> orderList = dao.getSortedDataList("/static/sample_data_ordered.txt");
            dao.writeDataToFile(orderList);
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}

