import java.io.*;
import java.net.*;
import java.util.Random;

class TCPServer {

  public static void main(String argv[]) throws Exception
    {
      String clientSentence;
      String capitalizedSentence;

      ServerSocket welcomeSocket = new ServerSocket(6789);
 
      while(true) {
 
           Socket connectionSocket = welcomeSocket.accept();

           BufferedReader inFromClient =
             new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

           DataOutputStream  outToClient =
             new DataOutputStream(connectionSocket.getOutputStream());

           clientSentence = inFromClient.readLine();

           capitalizedSentence = randomizeCase(clientSentence) +'\n';

           outToClient.writeBytes(capitalizedSentence);
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