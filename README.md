# The Retail Store Discounts
On a retail website, the following discounts apply:
1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

## Techonologies used:

- Eclipse IDE
- Spring Tool Suite (Eclipse Marketplace)
- JAVA 1.8
- Spring Boot 2.1.4
- Tomcat Embed 9.0.14
- JUnit 4.12
- Maven
- Postman / Soap UI
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### 1. Clone repository using GIT:
```
https://github.com/imjanvincent/Jan-Vincent-Ronquillo.git
```

### 2. Import project in Eclipse
```
File > Import > Existing Maven Projects
```

### 3. Run Java Application
Run RetailStoreDiscountApplication class as Java Application a d wait for the server to be started from the console logs
![Server logs](https://user-images.githubusercontent.com/50596242/57650724-7c4a2680-75dc-11e9-952c-87a1595f1f2b.PNG)

### 4. Check API if it successfully started in the browser by hitting
```
http://localhost:8080/
```
![Server is up](https://user-images.githubusercontent.com/50596242/57650839-cb905700-75dc-11e9-9f79-769b6cec92e7.PNG)
 
### 5. Open Postman to test GET method to get the list of sales. Enter the request URL and hit Send button
```
http://localhost:8080/sale
```
![GET](https://user-images.githubusercontent.com/50596242/57650879-eb277f80-75dc-11e9-9de9-dc4fab2faf0c.PNG)

### 6. Test POST method to get the user's total bill after discount(s). Select method as POST, enter request URL and request body then hit Send button
![POST](https://user-images.githubusercontent.com/50596242/57650882-eb277f80-75dc-11e9-8834-5e2af08f3b22.PNG)

### Sample request:
{  
   "id":3,
   "userType":"EMPLOYEE",
   "createdDate":"2017-01-01",
   "items":[  
      {  
         "id":0,
         "name":"Tshirt",
         "amount":"100",
         "itemType":"CLOTHING"
      },
      {  
         "id":0,
         "name":"Computer",
         "amount":"5000",
         "itemType":"OTHERS"
      },
      {  
         "id":0,
         "name":"Bread",
         "amount":"200",
         "itemType":"GROCERY"
      }
   ]
}

### Sample response:    
{
    "id": 3,
    "userType": "EMPLOYEE",
    "discountPercentage": 30,
    "totalAmountBeforeDiscount": 5300,
    "discountedAmount": 1530,
    "amountAfterDiscount": 3570,
    "groceryItemAmount": 200,
    "nonGroceryAmount": 5100,
    "totalAmount": 3770,
    "grandTotal": 3585
}

### From the sample response above, the API finds the net payable amount of the member based on its percentage discount and $5 discount for every 100 bill  

### 7. From Eclipse, run project using Coverage As to generate the JUnit test coverage
![Coverage](https://user-images.githubusercontent.com/50596242/57650878-eb277f80-75dc-11e9-9c50-8a600e7209ef.PNG)
![JUnit](https://user-images.githubusercontent.com/50596242/57650880-eb277f80-75dc-11e9-88bc-6053b5ed4c8c.PNG)


## Spring REST
| HTTP Method  | URI | Description |
| ------------- | ------------- | ------------- |
| GET  | /sale  | List all sales  |
| POST  | /sale  | Add new sale and return net payable amount  |





