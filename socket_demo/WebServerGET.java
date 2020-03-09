import java.io.*;
import java.net.*;
import java.util.*;

import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 
  

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;


class Online{

  public static void main(String argv[]) throws Exception  {

    String requestMessageLine;
    String fileName;

    ServerSocket listenSocket = new ServerSocket(6789);
    Socket connectionSocket = listenSocket.accept();

    BufferedReader inFromClient =
    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    DataOutputStream outToClient =
    new DataOutputStream(connectionSocket.getOutputStream());

    requestMessageLine = inFromClient.readLine();

    StringTokenizer tokenizedLine =
    new StringTokenizer(requestMessageLine);

  if (tokenizedLine.nextToken().equals("Online")){

    
  FileWriter myWriter = new FileWriter("/tmp/p2pDirectory/chatserver/peerdirectory.json",true);
  myWriter.write("Files in Java might be tricky, but it is fun enough!");
  myWriter.close();
  System.out.println("Successfully wrote to the file.");
  myWriter.close();


  connectionSocket.close();
}



  else if (tokenizedLine.nextToken().equals("GET")){
      fileName = tokenizedLine.nextToken();

      if (fileName.startsWith("/") == true )
       fileName  = fileName.substring(1);

     File file = new File(fileName);
     int numOfBytes = (int) file.length();

     FileInputStream inFile  = new FileInputStream (fileName);

     byte[] fileInBytes = new byte[numOfBytes];
     inFile.read(fileInBytes);

     outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n");

     if (fileName.endsWith(".jpg"))
      outToClient.writeBytes("Content-Type: image/jpeg\r\n");
    if (fileName.endsWith(".gif"))
      outToClient.writeBytes("Content-Type: image/gif\r\n");
    if (fileName.endsWith(".txt"))
      outToClient.writeBytes("Content-Type: text/plain\r\n");

    outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
    outToClient.writeBytes("\r\n");

    outToClient.write(fileInBytes, 0, numOfBytes);

    connectionSocket.close();
  }
  else System.out.println("Bad Request Message");

}
}