import java.io.*;
import java.net.*;
import java.util.*;

import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 
  

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;





class JSONSimpleExample{

  public static void main(String argv[]) throws Exception  {
  
    JSONParser parser = new JSONParser();

    try{
      
      Object obj = parser.parse(new FileReader("/tmp/p2pDirectory/chatserver/pd1.json"));
      JSONObject jsonObject = (JSONObject) obj;
      /*
      String username = (String)jsonObject.get("username");
      System.out.println("Username: "+username);
      String hostname = (String)jsonObject.get("hostname");
      System.out.println("hostname: "+hostname);
      String hostIP = (String)jsonObject.get("hostIP");
      System.out.println("hostIP: "+hostIP);
      String port = (String)jsonObject.get("port");
      System.out.println("port: "+port);
      String rating = (String)jsonObject.get("rating");
      System.out.println("rating: "+rating); */
      for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
      String key = (String) iterator.next();
      System.out.println(jsonObject.get(key));
      }
    }
  catch(Exception e){
    System.out.println("Invalid code.");
  }

    

  }
}
/*
    "username": "ChatAdmin",
    "hostname": "Ecorp01", 
    "hostIP": "1.1.1.1",
    "port": "0",
    "rating": "5"
    */

class User{ 
    private String username;
    private String hostname;
    private String hostIP;
    private int port;
    private int rating;

 }
