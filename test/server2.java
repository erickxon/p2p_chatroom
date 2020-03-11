    import java.io.*;
    import java.net.*;
    import java.util.*;
    import java.text.*; 
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
        return "username: "+username+"\nhostname: "+hostname+"\nhostIP: "+hostIP+"\nport: "+port+"\nrating: "+rating;
    }

    }


    class ClientHandler extends Thread  
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    final Hashtable directory; 
      
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, Hashtable directory)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
        this.directory = directory;
    } 
  
    @Override
    public void run()  
    { 
        String received; 
        String toreturn; 
        while (true)  
        { 
            try { 
  
                // Ask user what he wants 
                dos.writeUTF("Enter Request"); 
                  
                // receive the answer from client 
                received = dis.readUTF(); 
                  
                if(received.equals("Exit")) 
                {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                }

            switch (received) { 
                  
                    case "Online": 
                        dos.writeUTF("Online entry initiated."); 
                        String username = dis.readUTF(); 
                        String hostname = dis.readUTF(); 
                        String hostIP = dis.readUTF(); 
                        String port = dis.readUTF(); 
                        String rating = dis.readUTF(); 
                        directory.put(username, new User(username, hostname, hostIP, Integer.parseInt(port),Integer.parseInt(rating)));
                        dos.writeUTF(username + " is now ONLINE."); 
                        //this.s.close(); 
                        break;
                        //queryForPeers(users,outToClient);
                    case "query for peers":
                           Set<String> keySet = directory.keySet();
                            String stringbuilder = "";
                            for(String s: keySet){
                                stringbuilder += "\n----------\n";
                                stringbuilder += ((User)(directory.get(s))).toString(); 
                            }
                            dos.writeUTF(stringbuilder);
                            break;
                    default: 
                        dos.writeUTF("Invalid input"); 
                        break; 

            }



                  
 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
          
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
} 
    class server{


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
        System.out.println(stringbuilder);
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
        Hashtable<String, User> users = new Hashtable<String, User>();


        while(true){

            Socket s = null;

            try{ 
                s = listenSocket.accept(); 
                
                System.out.println("A new client is connected : " + s);
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                System.out.println("Assigning new thread for this client"); 
                Thread t = new ClientHandler(s, dis, dos, users); 
                 t.start(); 

            }
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            } 
        }

    }

        public static void waitState(BufferedReader inFromClient, DataOutputStream outToClient, Hashtable users){
            try{
            outToClient.writeBytes("Online.\r\n");
                String username = inFromClient.readLine();
                String hostname = inFromClient.readLine();
                String hostIP = inFromClient.readLine();
                String port = inFromClient.readLine();
                String rating = inFromClient.readLine();

                addToDirectory(users, new User(username, hostname, hostIP, Integer.parseInt(port),Integer.parseInt(rating)));
                queryForPeers(users,outToClient);

            //waitState(inFromClient,outToClient,users);
            }
            catch(Exception e){
                //do nothing

            }

        }

    }

