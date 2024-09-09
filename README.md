# Coding Challenge: API for Product Management

This Spring Boot application provides a RESTful API that interacts with the [Fake Store API](https://fakestoreapi.com/) to manage products. The application uses RestTemplate for API communication.

## Getting Started

### Prerequisites

- Java 17 or later
- Maven
- Postman (for API testing)

### Setup

1. **Clone the Repository:**

    ```bash
    https://github.com/sagarkumarojha/Prospecta_Assignment.git
    cd Coding_Challange/Product_Management
    ```
2. **Build and Run the Application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## API Endpoints

#### 1. Get Products by Category

- **URL:** `/products/category/{category}`
- **Method:** `GET`
- **Description:** Fetches a list of products by category from the Fake Store API.
- **URL Example:** `http://localhost:8080/products/category/jewelery`
- **Path Variable:**
  - `category` (string): The category to filter products by.
- **Response:**
  - **200 OK:** Returns a list of products in JSON format.
  - **404 Not Found:** If no products are found for the specified category.
- **Response Body:**
    ```json
    [
        {
        "id": 8,
        "title": "Pierced Owl Rose Gold Plated Stainless Steel Double",
        "description": "Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel",
        "price": 10.99,
        "category": "jewelery",
        "image": "https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg"
    }
      // More products
    ]
    ```

#### 2. Add New Product
- **URL:** `/products`
- **Method:** `POST`
- **Description:** Adds a new product to the store.
- **URL Example:** `http://localhost:8080/products`
- **Request Body:**

    ```json
      {
        "title": "Title",
        "price": 9.99,
        "description": "Product Description",
        "category": "jewelery",
        "image": "http://test.com/image.jpg",
      }
    ```

- **Response:**
  - **200 OK:** Returns a list of products in JSON format.
  - **404 Not Found:** If no products are found for the specified category.
- **Response Body:**

    ```json
      {
    "id": 21,
    "title": "Title",
    "price": 9.99,
    "description": "Product Description",
    "image": "http://test.com/image.jpg",
    "category": "jewelery"
    }
    ```

## How to Test
1. **Get Products:**

    Send a `GET` request to `http://localhost:8080/products/category/{category}` with the category as path variable.

2. **Add Product:**

    Send a `POST` request to `http://localhost:8080/products` with the product details in the request body.


# CSV Processing API

This project provides a REST API for processing CSV files by evaluating formulas within cells using openCSV and Apache POI.

### Prerequisites

- Java 17 or later
- Maven
- Postman (for API testing)

### Setup

1. **Clone the Repository:**

    ```bash
      https://github.com/sagarkumarojha/Prospecta_Assignment.git    
      cd Theoretical_Challange/CSV_Operations
    ```

2. **Build and Run the Application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## API Endpoints

### Process CSV

#### 1. Upload and Process CSV

- **URL:** `/csv/process`
- **Method:** `POST`
- **Description:** Uploads a CSV file, processes it by evaluating formulas within cells, and returns the processed CSV file.
- **Request:**
  - **Multipart File:** `file` (CSV file)
- **Response:**
  - **Content-Disposition Header:** `attachment;filename=output.csv`
  - **Content-Type:** `csv`
  - **Body:** Processed CSV file

## Error Handling

The API provides specific error handling for CSV processing:

- **FileNotFoundException:** Returns a `404 Not Found` response with the error message if csv is not found.
- 
- **IllegalArgumentException:** Returns a `400 Bad request` response with the error message if csv is not found.
- **General Exceptions:** Returns a `500 Internal Server Error` response with a generic error message.

## How to Test

1. **Upload and Process CSV:**

    Use Postman to send a `POST` request to `http://localhost:8080/csv/process` with the CSV file in the `file` parameter.

