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
* Implementing HATEOS and a UI is optional, but you should try and handle any errors you can anticipate with good responses. 
* Following this, look at the database and think about interesting endpoints you can expose. Try to avoid endpoints that do basic crud operations.

## Getting Started

Run the project using IntelliJ Ultimate Edition.
Make sure to install the dependencies and software included.

Clone the repository below.
```
git clone https://github.com/JamesKempadoo/Northwind-API.git
```

# Endpoints

## Get customer by name

### Request

`GET /customer/name/{contactName}`

```
curl -X 'GET' \
  'http://localhost:8080/customer/name/FrdriqueCiteaux' \
  -H 'accept: */*'
```

### Response
```
{
  "id": "BLONP",
  "companyName": "Blondesddsl pre et fils",
  "contactName": "Frdrique Citeaux",
  "contactTitle": "Marketing Manager",
  "address": "24, place Klber",
  "city": "Strasbourg",
  "region": null,
  "postalCode": "67000",
  "country": "France",
  "phone": "88.60.15.31",
  "fax": "88.60.15.32"
}
```

## Get customer by ID

### Request

`GET /customer/{id}`

```
curl -X 'GET' \
  'http://localhost:8080/customer/2' \
  -H 'accept: */*'
```

### Response

```
{
  "id": "AROUT",
  "companyName": "Around the Horn",
  "contactName": "Thomas Hardy",
  "contactTitle": "Sales Representative",
  "address": "120 Hanover Sq.",
  "city": "London",
  "region": null,
  "postalCode": "WA1 1DP",
  "country": "UK",
  "phone": "(171) 555-7788",
  "fax": "(171) 555-6750",
  "_links": {
    "all-customers": {
      "href": "http://localhost:8080/customer/all"
    },
    "this-customer": {
      "href": "http://localhost:8080/customer/AROUT"
    }
  }
}
```

## Get list of territories and all employees allocated to them

### Request

`GET /territories/all`

```
curl -X 'GET' \
  'http://localhost:8080/territories/all' \
  -H 'accept: */*'
```

### Response

```
[
  {
    "id": "01581",
    "territoryDescription": "Westboro",
    "links": [
      {
        "rel": "attached-employee",
        "href": "http://localhost:8080/employee/2"
      }
    ]
  },
  ...
]
```

## Get all orders between a date range

### Request

`GET /orders/range`

```
curl -X 'GET' \
  'http://localhost:8080/orders/range?from=1997-10-08&to=1997-12-27' \
  -H 'accept: */*'
```

### Response

```
{
  "_embedded": {
    "orderList": [
      {
        "id": 10700,
        "customerID": "SAVEA",
        "employeeID": 3,
        "orderDate": "1997-10-09T23:00:00Z",
        "requiredDate": "1997-11-07T00:00:00Z",
        "shippedDate": "1997-10-15T23:00:00Z",
        "freight": 65.1,
        "shipName": "Save-a-lot Markets",
        "shipAddress": "187 Suffolk Ln.",
        "shipCity": "Boise",
        "shipRegion": "ID",
        "shipPostalCode": "83720",
        "shipCountry": "USA"
      },
      ...
}
```

## Update the unit price of a product from its ID

### Request

`GET /products/update/{id}`

```
curl -X 'PATCH' \
  'http://localhost:8080/products/update/1?unitPrice=16.232' \
  -H 'accept: */*'
```

### Response

```
{
  "id": 1,
  "supplier": 1,
  "productName": "Chai",
  "quantityPerUnit": "10 boxes x 20 bags",
  "unitPrice": 16.232,
  "unitsInStock": 39,
  "unitsOnOrder": 0,
  "reorderLevel": 10,
  "discontinued": false,
  "category": 1
}
```

## Get all products with their suppliers and categories

### Request

`GET /products/suppliers&categories`

```
curl -X 'GET' \
  'http://localhost:8080/products/suppliers&categories' \
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
    "unitPrice": 16.232,
    "unitsInStock": 39,
    "unitsOnOrder": 0,
    "reorderLevel": 10,
    "discontinued": false,
    "category": 1,
    "links": [
      {
        "rel": "Supplier",
        "href": "http://localhost:8080/suppliers/1"
      },
      {
        "rel": "Category",
        "href": "http://localhost:8080/category/1"
      }
    ]
  },
  ...
]  
```


