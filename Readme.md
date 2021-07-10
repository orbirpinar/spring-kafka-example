# Spring Boot Kafka Example

To start Kafka and Zookeper you should up docker images.

> docker-compose up -d

To produce message

[http://localhost:8080/](http://localhost:8080/{you)produce/your-message

your message consume, after hit the above url

Of course you can change topic and group-id from application.yml  

example

http://localhost:8080/produce/mymessage

OUTPUT

```bash
com.exampes.consumer.Consumer: Consumer Record ---> mymessage
```
