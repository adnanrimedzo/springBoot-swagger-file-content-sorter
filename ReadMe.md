# Small SpringBoot Application With Swagger

How to run this application :

```sh
## build jar file and run unit tests
mvn clean package

##run application
java -jar sorter-0.0.1-SNAPSHOT.jar

```

Once the application is up, the following URLs will be available 

Address | Description
--- | ---
http://localhost:8080 | application
http://localhost:8080/swagger-ui.html | swagger ui


## Usage With Curl

**Upload a a.txt file**
```sh
curl -X POST "http://localhost:8080/fileOperation/getSortedLists" -H "accept: */*" -H "Content-Type: multipart/form-data" -F "file=@a.txt;type=text/plain"```
```

## Usage With Swagger UI:

**Upload a a.txt file**

`http://localhost:8080/swagger-ui.html#/file-content-operation-controller/handleFileUploadUsingPOST
`

### References:
https://github.com/adnanrimedzo/Java-MicroService-App-With-Docker

https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
