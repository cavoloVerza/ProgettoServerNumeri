package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main( String[] args ) {

        System.out.println("");

        //Server

        try {

            System.out.println( "Server in avvio!" );
            System.out.println("");

            ServerSocket server = new ServerSocket(3000);
            Socket s = server.accept();

            System.out.println("un client si è collegato");
            System.out.println("");

            int randomNum = (int)(Math.random() * 101);
            System.out.println("Numero da indovinare: " + randomNum);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String numRicevuto = "";
            int count = 0;

            do{

                numRicevuto = in.readLine();
                System.out.println("Numero ricevuto: " + numRicevuto);

                count++;
                System.out.println("Contatore: " + count);

                if(Integer.parseInt(numRicevuto) > randomNum)
                    out.writeBytes( "2" + '\n');

                else if(Integer.parseInt(numRicevuto) < randomNum)
                    out.writeBytes( "1" + '\n');

                else if(Integer.parseInt(numRicevuto) == randomNum) {
                    out.writeBytes( "3" + '\n');
                    out.writeBytes( Integer.toString(count) + '\n');
                }

                System.out.println("");

            }while(randomNum != Integer.parseInt(numRicevuto));

            s.close();
            server.close();


        } catch(Exception e) {


        }

    }
}
