import java.io.*;
import java.net.*;
class client {
	public static void main(String argv[]) throws Exception
	{
		String sentence;
		String modifiedSentence ="";
		Socket clientSocket = null;

		BufferedReader inFromUser 		= new BufferedReader(new InputStreamReader(System.in));
		clientSocket = new Socket("52.138.13.93", 6789);
		DataOutputStream outToServer 	= new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer 	= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        String method = inFromUser.readLine() + '\n';
        method += inFromUser.readLine() + '\n';
        method += inFromUser.readLine() + '\n';
        method += inFromUser.readLine() + '\n';
        method += inFromUser.readLine() + '\n';
        method += inFromUser.readLine() + '\n';

	//if(!method.equals("END")){
		//sentence = inFromUser.readLine();
		//outToServer.writeBytes(sentence + '\n');
		//modifiedSentence += sentence + '\n';
        /*
        outToServer.writeBytes(inFromUser.readLine() + '\n');
        outToServer.writeBytes(inFromUser.readLine()+ '\n');
        outToServer.writeBytes(inFromUser.readLine() + '\n');
        outToServer.writeBytes(inFromUser.readLine()+ '\n');*/
        outToServer.writeBytes(method+ '\n');
        String line;
        while ((line = inFromServer.readLine()) != null)
            System.out.println("" + inFromServer.read());
         
        
     
        
//		System.out.println("Hello "+ inFromServer.readLine());
	//}
	//System.out.println("FROM SERVER: " + modifiedSentence);
		//if(clientSocket!=null)
		//	clientSocket.close();

	}
}