# quill-async-akka-http
The Quill Async Akka Http is a very simple json rest api showing one way of using akka http with [quill](https://github.com/quilljs/quill) using postgres async.


It supports the following features:

* Generic Data Access layer, create a DAL with crud for an entity with just one line
* Models as case classes (quill main feature)
* Compile time query generation (quill main feature)
* Cake pattern for DI
* Spray-json to parse json
* Tests for DAL
* tests for routes

Utils: 

* Typesafe config for property management
* Typesafe Scala Logging (LazyLogging)
* Swagger for api documentation

The project was thought to be used as an activator template.

#Running

You should pre-configure 2 databases on postgres, quill and quilltest, an run the script postgresql-schema.sql to initiate the schema.
Take a look at application.properties and change the db configuration as you need.
After that, just:


       $ sbt run

#Testing

To run all tests (routes and persistence tests):


        $ sbt test

#Using

	curl --request POST localhost:8080/supplier -H "Content-type: application/json" --data "{\"name\" : \"sup1\",\"desc\" : \"low prices\"}"

	curl localhost:8080/supplier/valid-uuid

#Credits

To make this template, I just mixed the tutorials and templates, so credits for akka and quill guys, and swagger-akka-http.
