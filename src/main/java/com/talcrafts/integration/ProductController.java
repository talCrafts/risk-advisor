package com.talcrafts.integration;

import com.talcrafts.core.domain.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashishw on 28/1/16.
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

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