# Spring Boot 2 - Oauth 2 - Authentication Server and Client side

This repository aims to demonstrate how to set up and integrate oauth2 authentication and resource server to a web application written in Spring boot as well

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

The project supports H2 database already, so you don't need a running database to play with it, the minimum requirements is JDK 1.8.

You can access H2 database from:
```$xslt
http://localhost:8080/console
```
with following information
```$xslt
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:my_db
User Name: sa
Password: {empty}
```

### Installing

After cloning the project into your local machine, start Authentication & Resource server and Client side.

Authentication & Resource server will run on port 8080, with url:

```$xslt
http:\\localhost:8080
```
Web application will run on port 8081, with url:

```$xslt
http:\\localhost:8081
```

Right now, we have two available registered clients can be used:

```
Client name: read-client
Password: read-client-password
Scope: read
```

```
Client name: read-write-client
Password: read-write-client-password
Scope: read, write
```
We have two registered users:

```
Username: admin
Password: admin
Role: Admin
```

```$xslt
Username: user
Password: user
Role: User
```

The project offers an registration endpoint, which does not need authentication to access

```$xslt
curl -X POST \
  http://localhost:8080/api/v1/users/user \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
	"username":"cuong",
	"password":"cuong",
	"confirmedPassword":"cuong"
}'
```
As other oauth2 systems, to access resources from server, we need to get `access-token`

```$xslt
curl -X POST \
  http://localhost:8080/oauth/token \
  -H 'Authorization: Basic cmVhZC13cml0ZS1jbGllbnQ6cmVhZC13cml0ZS1jbGllbnQtcGFzc3dvcmQ=' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'cache-control: no-cache' \
  -d 'username=cuong&password=cuong&grant_type=password&client_id=read-write-client'
```

The response from above command will look like:
```$xslt
{
    "access_token": "f064fd05-84ca-4a64-992d-34794b3ca3ac",
    "token_type": "bearer",
    "refresh_token": "d8fcc9af-09f0-4c8b-817d-108eeef23131",
    "expires_in": 10791,
    "scope": "read write"
}
```

By using above `access-token`, we can access other resources, such as last 5 successful login times (default) of that user
```$xslt
curl -X GET \
  'http://localhost:8080/api/v1/users/user/histories?access_token=f064fd05-84ca-4a64-992d-34794b3ca3ac' \
  -H 'cache-control: no-cache'
```

The response will look like
```$xslt
{
    "code": "200",
    "message": "Success",
    "data": [
        "2018-11-27T15:20:27.160+0000",
        "2018-11-27T15:19:59.130+0000"
    ]
}
```
To logout from the server, we need:
```$xslt
curl -X GET \
  'http://localhost:8080/api/v1/users/user/logout?access_token=f064fd05-84ca-4a64-992d-34794b3ca3ac' \
  -H 'Postman-Token: 23e53dac-7afe-457f-8e15-f24a5d02afce' \
  -H 'cache-control: no-cache'
```
For the web application, it's just very simple web app, even it works wrong in some redirect logic, but the main purpose is to show the integration with server side.

Some url from web app
```$xslt
http://localhost:8081
http://localhost:8081/register
http://localhost:8081/user-profile
http://localhost:8081/logout
```

## Author

* [Cuong Ngo](bavuonglong93@gmail.com)