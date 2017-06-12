package sample;

import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.*;

/**
 * Main class of server apllication
 */
public class Main{

    public static void main(String[] args){
        CommunicationServer mainServer = new CommunicationServer();
        mainServer.runServer();

    }
}