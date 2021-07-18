# Payment gateway

`Project emulate payment gateway and contains with 2 subprojects: Payment gateway API and Audit
`

### Payment gateway API

It's a simple Spring boot application with 2 REST end points:

``` JSON
Submit payment and create tansaction with related data
POST /api/payment/submit 

Request tamplate:
 {
  "amount": 0,
  "card": {
    "cvv": "string",
    "expiry": "string",
    "pan": "string"
  },
  "cardHolder": {
    "email": "string",
    "name": "string"
  },
  "currency": "string",
  "invoice": 0
}

Success Response with Response code 200:
{
  "approved": true
}

Response with code 400 in case if validation is failed
{
  "approved": false,
  "errors": {
    "amount": "must be greater than 0",
    "expiry": "Invalid expiry date",
    "pan": "Invalid pan number",
    "email": "must be a well-formed email address"
  }
}

After transaction saving app will send transaction data to `Audit app` through  Apache Kafka

Find unique transaction by invoiceNumber
GET /api/transaction/{invoiceNumber}

Response example in case if the transaction was found
{
  "amount": 0,
  "card": {
    "cvv": "string",
    "expiry": "string",
    "pan": "string"
  },
  "cardHolder": {
    "email": "string",
    "name": "string"
  },
  "currency": "string",
  "invoice": 0
}
```

### Swagger

Swagger enabled at
```/api/swagger-ui```

### Build

To build and start app need to run command from `credorax\api\` folder in terminal:

```
mvn spring-boot:run
```

Apache Kafka must be installed for communication with `Audit app`

## Audit

It's Apache Kafka consumer application based on Spring boot. Has only one functionality - to getting messages from
Kafka `audit` topic and write them to audit.json file. Path to audit.json can be configured by editing `audit.log.path`
variable in `application.properties`

### Build

To build and start app need to run command from `credorax\audit\` folder in terminal:

```
mvn spring-boot:run
```
