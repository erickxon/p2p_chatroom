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

//GET EXISTING USER FILE
//TURN USERFILE INTO JAVA USER ARRAY
//ADD NEW USER TO ARRAY
//TURN IT INTO JSON AND REWRITE FILE
//JSON TO HASH --> DOES IT NEED TO BE A JSON FILE AT ALL? --> SERVER ALWAYS ON
//MESSAGE IS JUST COMPOSED OF NEW LINES (NO OBJECT STRUCTURE)

  JSONArray array = (JSONArray) jsonObject.get("Users");
  Iterator iter = array.iterator();

  while (iter.hasNext()) {
          System.out.println( ( iter.next() ).get("title") );
      }
//

    String username = tokenizedLine.nextToken();
    String hostname = tokenizedLine.nextToken();
    String hostIP = tokenizedLine.nextToken();
    String port = tokenizedLine.nextToken();
    String rating = tokenizedLine.nextToken();
    
    FileWriter myWriter = new FileWriter("/tmp/p2pDirectory/chatserver/peerdirectory.json",true);
    myWriter.write("{");
    myWriter.write("\"username\": "+"\""+username+"\""+"\n",);
    myWriter.write("\"hostname\": "+"\""+hostname+"\""+"\n",);
    myWriter.write("\"hostIP\": "+"\""+hostIP+"\""+"\n",);
    myWriter.write("\"port\": "+"\""+port+"\""+"\n",);
    myWriter.write("\"rating\": "+"\""+rating+"\""+"\n",);
    myWriter.write("}");
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