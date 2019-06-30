Auto-Declaring RabbitMQ Declarable Components
---
This is a sample project to demonstrate an issue with Spring Boot and RabbitMQ integration.

## TL;DR
I've defined a RabbitMQ queue:
```kotlin
@Configuration
class Queues {

    @Bean
    fun firstQ() = Queue("first.queue", true)
}
```
Since the `autoStartup` flag of the messaging container is `true` by default, I was expecting the queue to be declared
at the application startup, but it wasn't until the first time I publish something to it!

## How to Run?
First off, make sure the `up.sh` is executable by you:
```bash
$ chmod u+x up.sh
```
Then, do a `./up.sh`:
```bash
./up.sh
```
After a few moments, our App and a RabbitMQ instance would be up and running. Wait for the App to start completely,
then run the following command:
```bash
$ docker exec -it rabbit rabbitmqctl list_queues
Timeout: 60.0 seconds ...
Listing queues for vhost / ...
```
As you can see, there is no declared queues there. Now if we publish something to the `first.queue`, then the 
queue would be declared:
```bash
$ curl localhost:8080/declare
```
Again:
```bash
$ docker exec -it rabbit rabbitmqctl list_queues
Timeout: 60.0 seconds ...
Listing queues for vhost / ...
name    messages
first.queue     1
```
That's it! The queue has been created lazily, despite the value of `autoStartup` flag.

