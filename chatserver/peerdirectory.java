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
      
      Object obj = parser.parse(new FileReader("/tmp/p2pDirectory/chatserver/peerdirectory.json"));
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

      /*
      for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
      String key = (String) iterator.next();
      System.out.println(jsonObject.get(key)+"\n");
      }
      */

    JSONArray slideContent = (JSONArray) jsonObject.get("Users");
    Iterator i = slideContent.iterator();

    while (i.hasNext()) {
        System.out.println(i.next());
        // Here I try to take the title element from my slide but it doesn't work!
        //String title = (String) jsonObject.get("username");
        //System.out.println(title);
    }
    }
  catch(Exception e){
    System.out.println("Invalid code.");
    System.out.println(e.getMessage());
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

