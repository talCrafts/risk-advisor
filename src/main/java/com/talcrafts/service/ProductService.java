package com.talcrafts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talcrafts.core.domain.Product;

/**
 * Created by ashishw on 28/1/16.
 */
@RestController
@RequestMapping("/api/product")
public class ProductService {

    @RequestMapping("/all")
    public List<Product> getAll() {
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(new Product("ABC", "Jeevan", "LIC"));
        allProducts.add(new Product("XYZ", "Alegro", "MetLife"));
        allProducts.add(new Product("CBA", "Saral", "ICICI"));
        allProducts.add(new Product("NIA", "Sun", "Birala"));
        return allProducts;
    }
}