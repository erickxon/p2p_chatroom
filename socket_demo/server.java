import java.io.*;
import java.net.*;
import java.util.*;

//import java.io.FileReader; 
//import java.util.Iterator; 
//import java.util.Map; 
  

//import org.json.simple.JSONArray; 
//import org.json.simple.JSONObject; 
//import org.json.simple.parser.*;
class User{
  String username;
  String hostname;
  String hostIP;
  int port;
  int rating; 

  public User(String username, String hostname, String hostIP, int port, int rating){
    this.username = username;
    this.hostname = hostname;
    this.hostIP = hostIP;
    this.port = port;
    this.rating = rating;
  }

  public String toString(){
      return "username: "+username+"\nhostname"+hostname+"\nhostIP"+hostIP+"\nport"+"\nrating";
  }

}



class Server{


public static void addToDirectory(Hashtable directory, User user){
    directory.put(user.username, user);
}

public static void removeFromDirectory(Hashtable directory, String username){
    directory.remove(username);
}

public static void queryForPeers(Hashtable directory, DataOutputStream outStream){
    Set<String> keySet = directory.keySet();
    String stringbuilder = "";
    for(String s: keySet){
        stringbuilder += ((User)(directory.get(s))).toString();
    }

    try{
    outStream.writeBytes(stringbuilder);
    }
    catch(Exception e){
        //do exception;
    }
}

  public static void main(String argv[]) throws Exception  {

    String requestMessageLine;
    String fileName;

    ServerSocket listenSocket = new ServerSocket(6789);
    Socket connectionSocket = listenSocket.accept();

    BufferedReader inFromClient =
    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    DataOutputStream outToClient =
    new DataOutputStream(connectionSocket.getOutputStream());

    //requestMessageLine = inFromClient.readLine();

    //StringTokenizer tokenizedLine =
    //new StringTokenizer(requestMessageLine);
    Hashtable<String, User> users = new Hashtable<String, User>();

/*
    if (tokenizedLine.nextToken().equals("Online")){
    //if (inFromClient.readLine().equals("Online")){
    outToClient.writeBytes("Online.");
    String username = inFromClient.readLine();
    String hostname = inFromClient.readLine();
    String hostIP = inFromClient.readLine();
    String port = inFromClient.readLine();
    String rating = inFromClient.readLine();

    users.put(username, new User(username, hostname, hostIP, Integer.parseInt(port),Integer.parseInt(rating)));
    outToClient.writeBytes("username: " + users.get(username).username + "\r\n");
    outToClient.writeBytes("hostIP" + users.get(username).hostIP + "\r\n");

    connectionSocket.close();
}
*/

/*
  else  */ if (inFromClient.readLine().equals("Online")){
    outToClient.writeBytes("Online.\r\n");
    String username = inFromClient.readLine();
    String hostname = inFromClient.readLine();
    String hostIP = inFromClient.readLine();
    String port = inFromClient.readLine();
    String rating = inFromClient.readLine();
    users.put(username, new User(username, hostname, hostIP, Integer.parseInt(port),Integer.parseInt(rating)));

    outToClient.writeBytes("username: " + users.get(username).username + "\r\n");
    outToClient.writeBytes("hostname" + users.get(username).hostname + "\r\n");
    outToClient.writeBytes("hostIP" + users.get(username).hostIP + "\r\n");
    outToClient.writeBytes("port" + users.get(username).port + "\r\n");
    outToClient.writeBytes("rating" + users.get(username).rating + "\r\n");

    /*
    outToClient.writeBytes(username+"\n");
    outToClient.writeBytes(hostname+"\n");
    outToClient.writeBytes(hostIP+"\n");
    outToClient.writeBytes(port+"\n");
    outToClient.writeBytes(rating+"\n");
    */

    /*
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
*/
    connectionSocket.close();
  }

  else System.out.println("Bad Request Message");

}
}
