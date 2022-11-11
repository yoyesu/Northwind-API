# Northwind-API

**Developed by <ins>Pirates of the CaribBEAN</ins>: Hamzah, James,
Riya,
Calum,
Maria,
Igran.**

### **Table of Contents**
* [**About Project**](#about-project)
  * [Built with](#built-with)
  * [Dependencies](#dependencies)
* [**Requirements**](#requirements)
* [**Getting Started**](#getting-started)
* [**Results**](#results)
* [**Future Development**](#future-development)

## About Project

This project is developed as a team of 6, following agile methodologies, good programming practices in OOP, SOLID, design patterns, testing.

The project's functionality is creating a REST API to access different endpoints to retrieve data from the Northwind database.

### <span style="color: blue;">**Built With**</span>

* IntelliJ IDEA (Community Edition)

### <span style="color: blue;">**Dependencies**</span>

* spring-boot-starter-data-jpa
* spring-boot-starter-hateoas
* spring-boot-starter-test
* spring-boot-starter-web
* springdoc-openapi-ui
* mysql-connector-j

## Requirements
* A customers endpoint which lets you search for customers by name and find by ID
* A territories end point which shows a list of territories and all employees allocated to them
* An orders endpoint which shows me all orders between a date range
* A products endpoints which lets me change the unit price of a product from its ID
* A products endpoint which shows all products with their suppliers and categories

* Implementing HATEOS and a UI is optional but you should try and handle any errors you can anticipate with good responses. Following this, look at the database and think about interesting endpoints you can expose. Try to avoid endpoints that do basic crud operations

## Getting Started

Run the project using IntelliJ Ultimate Edition.
Make sure to install the dependencies and software included.

Clone the repository below.
```
git clone https://github.com/JamesKempadoo/Northwind-API.git
```

## Endpoints

### Request

`GET /products/all`

```
curl -X 'GET' \
  'http://localhost:8080/products/all' \
  -H 'accept: */*'
```

### Response

```
[
  {
    "id": 1,
    "supplier": 1,
    "productName": "Chai",
    "quantityPerUnit": "10 boxes x 20 bags",
    "unitPrice": 16.2345,
    "unitsInStock": 39,
    "unitsOnOrder": 0,
    "reorderLevel": 10,
    "discontinued": false,
    "category": 1
  },
  ...
  ]
  ```
