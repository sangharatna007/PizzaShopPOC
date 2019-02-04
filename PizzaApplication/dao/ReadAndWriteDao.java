package com.pizza.shop.PizzaApplication.dao;

import com.pizza.shop.PizzaApplication.model.Item;
import com.pizza.shop.PizzaApplication.service.DateSortingService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sangharatna.davane on 2/4/2019.
 */
public class ReadAndWriteDao {

    String[] dataArray;
    Resource outputResource = new ClassPathResource("/static/output_data_ordered.txt");

    public List<Item> getSortedDataList(String filePath) throws IOException {
        List<Item> orderList = new ArrayList<>();
        Resource resource = new ClassPathResource(filePath);

        File file = resource.getFile();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine(); // read the first line
        String st = null;
        st = reader.readLine();
        while ((st = reader.readLine()) != null) {
            Item order = new Item();
            dataArray = st.split("\t\t");
            order.setOrder(dataArray[0]);
            order.setTime(Long.valueOf(dataArray[1]));
            orderList.add(order);
        }
        Collections.sort(orderList, new DateSortingService());

        return orderList;
    }

    public void writeDataToFile(List<Item> orderList) throws IOException {

        File filepath= outputResource.getFile();
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

        orderList.forEach((Item order) -> {
            try {
                objectOut.writeObject("order  " + order.getOrder() + "  at  :  " + Instant.ofEpochSecond(order.getTime()) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        objectOut.close();
    }
}
