import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Send {
    private final static String QUEUE_NAME = "helloQueue";
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("username:");
        String name=scanner.next();
        System.out.println("password:");
        String pass=scanner.next();
        System.out.println("port:");
        int port=scanner.nextInt();
        String message="";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(port);
        factory.setUsername(name);
        factory.setPassword(pass);
        for (int i=0; i<10; i++) {
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null) ;
                System.out.println("What shall I send???");
                message = scanner.nextLine();
                for(int j=0; j<3; j++) {
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                    System.out.println(" [x] Sent '" + message + "'");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
