# Project: Splitwise 🔥
# 📁 Collection: Expense 


## End-point: add expense
**Expense Types**

1. EQUAL
2. EXACT
3. INDIVIDUAL
4. PERCENT
5. SHARE
    

_For every expense type json is different_

## **EQUAL**

``` json
{
    "payerId": "d5a9058a-22e3-4092-b375-dca7901a280a",
    "expenseType": "EQUAL",
    "category": "RENT",
    "desc": "Monthly rent payment",
    "amount": 100,
    "participants": {
        "type": "USERS",
        "ids": [
            "9d8345b6-895b-40bd-bbe6-a4656cfd61e8",
            "8a191692-f99a-464e-8e42-2ac1101ccbea"
        ]
    }
}

 ```

## EXACT

``` json
{
    "payerId": "d5a9058a-22e3-4092-b375-dca7901a280a",
    "expenseType": "EXACT",
    "category": "RENT",
    "desc": "Monthly rent payment",
    "amount": 100,
    "participants": {
        "type": "USERS",
        "shares" : [50, 50]
        "ids": [
            "9d8345b6-895b-40bd-bbe6-a4656cfd61e8",
            "8a191692-f99a-464e-8e42-2ac1101ccbea"
        ]
    }
}

 ```

## **INDIVIDUAL**

``` json
{
    "payerId": "9d8345b6-895b-40bd-bbe6-a4656cfd61e8",
    "expenseType": "INDIVIDUAL",
    "category": "FOOD",
    "desc": "Pizza",
    "amount": 250
}

 ```

## **PERCENT**

``` json
{
    "payerId": "d5a9058a-22e3-4092-b375-dca7901a280a",
    "expenseType": "PERCENT",
    "category": "RENT",
    "desc": "Monthly rent payment",
    "amount": 100,
    "participants": {
        "type": "USERS",
        "shares" : [20, 80]
        "ids": [
            "9d8345b6-895b-40bd-bbe6-a4656cfd61e8",
            "8a191692-f99a-464e-8e42-2ac1101ccbea"
        ]
    }
}

 ```

## **SHARE**

``` json
{
    "payerId": "d5a9058a-22e3-4092-b375-dca7901a280a",
    "expenseType": "SHARE",
    "category": "RENT",
    "desc": "Monthly rent payment",
    "amount": 100,
    "participants": {
        "type": "USERS",
        "shares" : [2, 3]
        "ids": [
            "9d8345b6-895b-40bd-bbe6-a4656cfd61e8",
            "8a191692-f99a-464e-8e42-2ac1101ccbea"
        ]
    }
}

 ```
### Method: POST
>```
>{{localhost}}/expense
>```


### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: get all expense
### Method: GET
>```
>{{localhost}}/expense
>```
### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: Auth 


## End-point: register
### Method: POST
>```
>{{localhost}}/auth/register
>```
### Body (**raw**)

```json
{
    "name": "shiva",
    "email": "shiva@gmail.com",
    "mobile": "9088839089",
    "password": "shiva12345"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: get all users
### Method: GET
>```
>{{localhost}}/user
>```
### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: login
### Method: POST
>```
>{{localhost}}/auth/login
>```
### Body (**raw**)

```json
{
    "email": "parish@gmail.com",
    "password": "shiva12345"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: verify email
### Method: GET
>```
>{{localhost}}/auth/verify?code=315a8178-61b9-4b9a-ae38-e9360985c7a3
>```
### Query Params

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: group 


## End-point: create group
### Method: POST
>```
>{{localhost}}/groups
>```
### Body (**raw**)

```json
{
    "name": "test group",
    "createdBy": "1be89a67-3666-46dd-8f37-e020feec6165",
    "description": "Test group description"
}
```

### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: add user to group
### Method: GET
>```
>{{localhost}}/groups/{{groupId}}/41dcbfaf-9a6d-4ede-8e24-ba8afffc6d11
>```
### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: get group members
### Method: GET
>```
>{{localhost}}/groups/{{groupId}}
>```
### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: delete group
### Method: DELETE
>```
>{{localhost}}/groups/{{groupId}}
>```
### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: remove group member
### Method: DELETE
>```
>{{localhost}}/groups/{{groupId}}/9d8345b6-895b-40bd-bbe6-a4656cfd61e8
>```


### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: get group expenses
### Method: GET
>```
>{{localhost}}/groups/expenses/{{groupId}}
>```
### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: Debts 


## End-point: get debts by user id
### Method: GET
>```
>{{localhost}}/debts/f5a4283f-1df1-4f9a-88ee-8f3031f77c
>```
### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃
# 📁 Collection: wallet 


## End-point: get wallet
### Method: GET
>```
>{{localhost}}/wallet/0c83f9fb-56ad-46c4-9037-98e64c7957c1
>```
### 🔑 Authentication bearer

***Headers:***

| Key | Value | Description |
| --- | ------|-------------|
| Authorization | Bearer {{access_token}} | **access_token** must be a valid JWT token. |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

## End-point: swagger
### Method: GET
>```
>http://localhost:8080/v3/api-docs
>```

⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## Tech Stack :heart:


**Server:** Spring Boot, Spring Security

## [Postman](https://documenter.getpostman.com/view/24887117/2s8Z6savNK) ![postman-logo-icon-orange](https://user-images.githubusercontent.com/52816688/209198935-d0bfdd57-c236-4ed4-b717-90ca6dd4e290.svg) 
Get the postman files [here.](https://documenter.getpostman.com/view/24887117/2s9YJW76sJ)



## Support :beer:

For support, connect on [linkedin](https://www.linkedin.com/in/shivakumar139/)


## Feedback :pray:

If you have any feedback, please reach out to us at [linkedin](https://www.linkedin.com/in/shivakumar139/)

