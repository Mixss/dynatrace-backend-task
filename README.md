# Simple NBP REST API

This REST API fetches data from the Narodowy Bank Polski's public API and returns relevant information from them (http://api.nbp.pl/).

## Table of contents

- [Compiling and running](#compiling-and-running)
- [Usage](#usage)
- [Endpoints](#endpoints)

## Compiling and running
- Using `maven package` and `java -jar`: 
```
$ ./mvnw package
$ java -jar target/dynatrace-backend-task-1.0.jar
```
- Executing directly with maven
```
$ ./mvnw compile exec:java
```
- Using docker (in this case app will be available at port 8080):
```
$ ./mvnw spring-boot:build-image
$ docker run -p 8080:8080 docker.io/library/dynatrace-backend-task:1.0
```

## Usage

Application by default will available at port `8080`. You can test the API using your application of choice, but there is also a swagger UI available at `/swagger-ui/index.html`

## Endpoints
- **GET** `/api/avg/{currencyCode}/{date}`:
    - takes arguments:
      - `currencyCode` is the code of currency defined at https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/
      - `date` is the date in format *YYYY-MM-DD*
    - returns:
      - single number which is the average exchange rate of the currency; e.g:
      ```
      4.2067
      ```
- **GET** `/api/minmax/{currencyCode}/{numberOfLastQuotations}`:
    - takes arguments:
        - `currencyCode` is the code of currency defined at https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/
        - `numberOfLastQuotations` is the number of last quotations of the currency. Max value is 255
    - returns:
        - two values, first is the minimum and second one is the maximum value of the currency in the last `numberOfLastQuotations` quotations; e.g:
      ```
      {
         "min": 5.2086,
         "max": 5.3648
      }
      ```
- **GET** `/api/maxdiff/{currencyCode}/{numberOfQuotations}`:
    - takes arguments:
        - `currencyCode` is the code of currency defined at https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/
        - `numberOfLastQuotations` is the number of last quotations of the currency. Max value is 255
    - returns:
        - single number which is the major difference between the buy and ask rate in the last `numberOfLastQuotations` quotations; e.g:
      ```
      0.1096
      ```