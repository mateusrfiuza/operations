Operations API
=================

This application create account and transactions for a specific account following a set of
predefined rules.

### Dependencies
* JDK 11
* Docker

### How to run tests
* Run commands on terminal inside project folder:
    ```
    ./gradlew clean test
    ```
    
### How to build and run application
You can choose build and run the application directly, using Gradle or using Docker. 
Run commands on terminal inside project folder:

#### Gradle
* Using Gradle to build and run application:
    1. Build and run
        ```
        ./gradlew clean build bootRun
        ```
    2. To stop, press CTRL+C or respective
#### Docker
* Using Gradle to build and Docker to run application:
    1. Build with Gradle
        ```
        ./gradlew clean build
       ```
    2. Run on Docker
        1. Build docker image
        ```
        docker build -t br.com.financial.operations/operations:latest .
        ```
       2. Run docker image
        ```
        docker run -p 8080:8080 -t br.com.financial.operations/operations:latest
        ```
       3. To stop, press CTRL+C or respective    

#### Documentation in `Swagger`:
1. Open your browser 
2. Type in URL bar `http://{app-ip-adress}:8080/swagger-ui.html`

#### Sample Request

1. (POST /accounts) Create account 
    1. Request: `{"document_number":"xpto"`
    2. Response: `{"account_id":1, "document_number: "xpto"}`
2. (GET /accounts/:accountId) Get account
    1. Response: `{"account_id":1, "document_number: "xpto"}`
3. (POST /transactions) Create transaction
    1. Request: `{"account_id":1, "operation_type_id: 1, amount: 10.00}`
###Types operations    
    - 1 - COMPRA A VISTA
    - 2 - COMPRA PARCELADA
    - 3 - SAQUE
    - 4 - PAGAMENTO
