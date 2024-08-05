# Chitter

A web-based Twitter clone using ReactJS for the frontend and Java Spring for the backend.

## Installation

To build and run the backend server:

```sh
cd backend
export SECRET_KEY=<YOUR-512-BIT-KEY>
export SERVER_ADDRESS=<YOUR-IP>
mvn clean spring-boot:run
```

To build and run the frontend server:
```sh
cd frontend
npm install
REACT_APP_SERVER_ADDRESS=<YOUR-IP> npm start
```
