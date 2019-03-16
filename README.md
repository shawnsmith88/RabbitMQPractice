# First exposure to RabbitMQ
please note, to run this on your machine, you will have to have installed Erlang and RabbitMQ
This is basically just the tutorial, with a bit of spice, from https://www.rabbitmq.com/tutorials/tutorial-one-java.html
I highly recommend doing this tutorial on your own. It is great practice for the first exposure to rabbitMQ
## basic credentials:
if you are running defaults, both your username and passoword will be guest
the default port is 5672

## other side notes:
ensure that you enable the management plugin with rabbitmq-plugins enable rabbitmq\_management
go to http://localhost:15672 in your preferred and login with the above credentials to view the transmission of data in gui form
I had to revert to a slightly older version of ampq, I used ampq-client-5.4.0 even though ampq-client-5.5.1 had been released. I also used slf4j-api-1.7.25 and slf4j-simple-1.7.25. make sure to include these in your libraries.

# Second Tutorial
This part of the tutorial demonstrates how sometimes tasks are sent over the wire and how the queue works when it gets such a backlog of tasks. It also demonstrates how you can put a few of the recievers in parallel in order to distribute such tasks and make it go faster, with the tradeoff being processing power of course.
