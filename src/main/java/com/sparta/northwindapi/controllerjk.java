package com.sparta.northwindapi;

import com.sparta.northwindapi.entities.Category;
import com.sparta.northwindapi.entities.Product;
import com.sparta.northwindapi.entities.Supplier;
import com.sparta.northwindapi.repositories.CategoryRepository;
import com.sparta.northwindapi.repositories.ProductRepository;
import com.sparta.northwindapi.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class controllerjk {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;

    @Autowired
    public controllerjk (ProductRepository productRepository, CategoryRepository categoryRepository,
                         SupplierRepository supplierRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/products/Suppliers&Categories/")
    public List<Product> getProductsWithSuppliersAndCategories(){
        return productRepository.findAll();
    }

    @GetMapping("/suppliers/{id}")
    public Supplier getSupplierById(@PathVariable int id){
        return supplierRepository.findById(id).orElse(null);
    }
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable int id){
        return categoryRepository.findById(id).orElse(null);
    }
    

}
