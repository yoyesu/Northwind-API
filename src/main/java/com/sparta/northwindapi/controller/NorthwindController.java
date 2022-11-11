package com.sparta.northwindapi.controller;

import com.sparta.northwindapi.entities.*;
import com.sparta.northwindapi.entities.Employee.Employee;
import com.sparta.northwindapi.entities.Employee.Employeeterritory;
import com.sparta.northwindapi.entities.Order.Order;
import com.sparta.northwindapi.exceptions.IDNotFoundException;
import com.sparta.northwindapi.repositories.*;
import com.sparta.northwindapi.repositories.Employee.EmployeeRepository;
import com.sparta.northwindapi.repositories.Employee.EmployeeterritoryRepository;
import com.sparta.northwindapi.repositories.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class NorthwindController {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;
    private CustomerRepository customerRepository;
    private TerritoryRepository territoryRepo;
    private EmployeeterritoryRepository employeeTerritoryRepo;
    private EmployeeRepository employeeRepo;
    private OrderRepository orderRepository;

    @Autowired
    public NorthwindController(ProductRepository productRepository, CategoryRepository categoryRepository,
                               SupplierRepository supplierRepository, CustomerRepository customerRepository,
                               TerritoryRepository territoryRepo, EmployeeterritoryRepository employeeTerritoryRepo,
                               EmployeeRepository employeeRepo, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
        this.customerRepository = customerRepository;
        this.territoryRepo = territoryRepo;
        this.employeeTerritoryRepo = employeeTerritoryRepo;
        this.employeeRepo = employeeRepo;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/products/all")
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) throws IDNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new IDNotFoundException(404, id, "ID not found"));
    }

    @PatchMapping("/products/update/{id}")
    public Product updateUnitPrice(@PathVariable int id, @RequestParam BigDecimal unitPrice) {
        Product product = null;
        if (productRepository.findById(id).isPresent()) {
            product = productRepository.findById(id).get();
            product.setUnitPrice(unitPrice);
            productRepository.save(product);
        }
        return product;
    }

    @GetMapping("/products/Suppliers&Categories")
    public List<EntityModel<Product>> getProductsWithSuppliersAndCategories() throws IDNotFoundException {
        List<Product> products = productRepository.findAll();
        List<EntityModel<Product>> listOfEntityModels = new ArrayList<>();
        for (Product product: products){
            EntityModel<Product> entityModel = EntityModel.of(product);
            WebMvcLinkBuilder linkSupplier = linkTo(methodOn(this.getClass()).getSupplierById(product.getSupplier()));
            WebMvcLinkBuilder linkCategory= linkTo(methodOn(this.getClass()).getCategoryById(product.getCategory()));
            entityModel.add(linkSupplier.withRel("Supplier"));
            entityModel.add(linkCategory.withRel("Category"));
            listOfEntityModels.add(entityModel);
        }

        return listOfEntityModels;
    }

    @GetMapping("/suppliers/{id}")
    public Supplier getSupplierById(@PathVariable int id) throws IDNotFoundException{
        return supplierRepository.findById(id).orElseThrow(() -> new IDNotFoundException(404, id, "ID not found"));
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable int id) throws IDNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new IDNotFoundException(404, id, "ID not found"));
    }


    @GetMapping("/customer/all")
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

    @GetMapping("/customer/{id}")
    public EntityModel<Customer> getCustomerById (@PathVariable String id){
        List<Customer> customerList = customerRepository.findAll();
        for (Customer customer: customerList){
            if (customer.getId().equals(id)){
                EntityModel<Customer> entityModel=EntityModel.of(customer);
                WebMvcLinkBuilder webMvcLinkBuilder= linkTo(methodOn(this.getClass()).getAllCustomers());
                entityModel.add(webMvcLinkBuilder.withRel("all-customers"));
                WebMvcLinkBuilder webMvcLinkBuilder1= linkTo(methodOn(this.getClass()).getCustomerById(id));
                entityModel.add(webMvcLinkBuilder1.withRel("this-customer"));

                return entityModel;
            }
        }
        return null;
    }


    @GetMapping("/customer/name/{contactName}")
    public Customer getCustomerByName(@PathVariable String contactName){
        List<Customer> customerList = customerRepository.findAll();
        for(Customer customer : customerList){
            if((customer.getContactName()).replace(" ","").equals(contactName)){
                return customer;
            }
        }
        return null;
    }

    @GetMapping("/territories/all")
    public List<EntityModel<Territory>> getAllTerritories() throws IDNotFoundException {
        List<EntityModel<Territory>> entityModelTerritory = new ArrayList<>();
        List<Territory> territories = territoryRepo.findAll();
        List<Employee> employees = employeeRepo.findAll();
        List<Employeeterritory> employeeTerritories = employeeTerritoryRepo.findAll();

        for (Employeeterritory employeeTerritory : employeeTerritories) {
            Territory territoryAdd;
            Employee employeeAdd;
            for (Territory territorySearch : territories) {
                if (Integer.parseInt(territorySearch.getId()) == Integer.parseInt(employeeTerritory.getId().getTerritoryID())) {
                    territoryAdd = territorySearch;
                    for (Employee employeeSearch : employees) {
                        if (employeeSearch.getId().equals(employeeTerritory.getEmployeeID().getId())) {
                            employeeAdd = employeeSearch;
                            EntityModel<Territory> entityModel=EntityModel.of(territoryAdd);
                            WebMvcLinkBuilder webMvcLinkBuilder=WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getEmployee(employeeAdd.getId()));
                            entityModel.add(webMvcLinkBuilder.withRel("attached-employee"));
                            entityModelTerritory.add(entityModel);
                        }
                    }
                }
            }
        }
        return entityModelTerritory;
    }

    @GetMapping("/employee/{id}") //relative URL
    public Employee getEmployee(@PathVariable Integer id)  throws IDNotFoundException {
        return employeeRepo.findById(id).orElseThrow(() -> new IDNotFoundException(404, id, "ID not found"));
    }

    @GetMapping("/orders/{id}")
    public Order getOrdersById(@PathVariable int id) throws IDNotFoundException {
        return orderRepository.findById(id).orElseThrow(() -> new IDNotFoundException(404, id, "ID not found"));
    }

    @GetMapping("/orders/range")
    public CollectionModel<Order> getOrdersByOrderDateRange(
            @RequestParam("from")
            String startDate,
            @RequestParam("to")
            String endDate){

        LocalDate startTimeDate = LocalDate.parse(startDate);
        LocalDate endTimeDate = LocalDate.parse(endDate);
        List<Order> listOfOrders = orderRepository.findAll();

        List<Order> ordersInRange = new ArrayList<>();

        for(Order order : listOfOrders){

            LocalDate orderDate = LocalDate.ofInstant(order.getOrderDate(), ZoneOffset.UTC);

            if(orderDate.isAfter(startTimeDate) && orderDate.isBefore(endTimeDate) ){
                ordersInRange.add(order);
            }
        }
        CollectionModel<Order> entityOrderList = CollectionModel.of(ordersInRange);

        return entityOrderList;
    }

    @ExceptionHandler(IDNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String>  throwIDNotFoundException(IDNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.toString());
    }

}


