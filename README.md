### **Overview:**
* This application processes User Authentication and Authorization.
* Perform CRUD on Products and Sellers/.
* CSRF Protection

### **Technology Stack:**
* **Java 11 or higher + Springboot** : For robust RestFul service and scalable backend.
* **Postgres**: For storing product and seller data.
* **Spring Security**: To handle authentication, Authorization and CSRF protection.
* **Maven** : For build and dependency management.

### **Project Structure:**

The project follows a standard Spring Boot structure, with separate packages for configuration, controllers, models, repositories, services, and exceptions.

## API Endpoints:
### Authentication:

* POST - /api/auth/register - Register a new seller
* POST - /api/auth/login - Login a seller

 HTTP Method | Endpoint           | Description                | Auth Required |
|-------------|--------------------|----------------------------|---------------|
| POST        | /auth/register     | Register a new seller      | No            |
| GET         | /sellers/{id}      | Get seller by ID           | Yes           |
| GET         | /sellers           | Get seller by ID           | Yes           |
| PUT         | /sellers/{id}      | Update seller information  | Yes           |
| DELETE      | /sellers/{id}      | Delete seller by ID        | Yes           |
| GET         | /api/products      | Get all products           | Yes           |
| GET         | /api/products/{id} | Get product by ID          | Yes           |
| POST        | /api/products      | Create a new product       | Yes           |
| PUT         | /api/products/{id} | Update product information | Yes           |
| DELETE      | /api/products/{id} | Delete product by ID       | Yes           |
| GET         | /csrf              | Retrieve CSRF token        | Yes           |

### Security

The application uses Spring Security for authentication and authorization. Only registered sellers can perform CRUD operations on products. CSRF protection is enabled to protect against cross-site request forgery attacks.

### CSRF Protection

CSRF protection is enabled by default except for register and login.
Each request to endpoint(Except GET) must include a valid CSRF token. The token can be retrieved from the /csrf endpoint.

To use CSRF protection with Postman:
1. Make a GET request to csrf endpoint.
2. Include the token in header if request with key `X-CSRF-TOKEN`.