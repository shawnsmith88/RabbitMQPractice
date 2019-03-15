import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class recieve {
    private final static String QUEUE_NAME = "helloQueue";

    public static void main(String[] argv) throws Exception {
        Scanner scanner=new Scanner(System.in);
        System.out.println("username:");
        String name=scanner.next();
        System.out.println("password:");
        String pass=scanner.next();
        System.out.println("port:");
        int port=scanner.nextInt();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(port);
        factory.setUsername(name);
        factory.setPassword(pass);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            try{
                doWork(message);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }finally{
                System.out.println("[x] done");
            }
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
    public static void doWork(String message) throws InterruptedException {
        for(char c:message.toCharArray()){
            if (c=='.'){
                Thread.sleep(3000);
            }
        }
    }
}