package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Michał Treter on 6/11/17.
 */
public class CommunicationServer{

    /**
     *
     */
    public void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(2137);
            System.out.println("Socket ustawiono");

            while(true){
                Socket user = serverSocket.accept();

                Thread t = new Thread(new CommunicationProcess(user));
                t.start();
                System.out.println("Ustanowiono połącznie");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
