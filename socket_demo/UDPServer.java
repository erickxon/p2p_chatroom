import java.io.*;
import java.net.*;

class UDPServer {
  public static void main(String args[]) throws Exception
    {

      DatagramSocket serverSocket = new DatagramSocket(9876);

      byte[] receiveData = new byte[1024];
      byte[] sendData  = new byte[1024];

      while(true)
        {

          DatagramPacket receivePacket =
             new DatagramPacket(receiveData, receiveData.length);

          serverSocket.receive(receivePacket);

          String sentence = new String(receivePacket.getData());

          InetAddress IPAddress = receivePacket.getAddress();

          int port = receivePacket.getPort();

                      String capitalizedSentence = sentence.toUpperCase();

          sendData = randomizeCase(capitalizedSentence).getBytes();

          DatagramPacket sendPacket =
             new DatagramPacket(sendData, sendData.length, IPAddress,
                               port);

          serverSocket.send(sendPacket);
        }
    }

      private static String randomizeCase(String s){
      String r = "";
      for(int i =0;i<s.length();i++){
        Random rand = new Random();
        if(rand.nextInt(2) > 0)
          r+=s.toUpperCase().charAt(i);
        else
          r+=s.toLowerCase().charAt(i);
      }
      return r;
    }


}