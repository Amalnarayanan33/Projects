import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        // Create a datagram socket
        DatagramSocket clientSocket = new DatagramSocket();

        // Prompt user to enter a message
        System.out.print("Enter a message: ");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String sentence = userInput.readLine();

        // Convert message to bytes and send to the server
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 12345);
        clientSocket.send(sendPacket);

        // Receive response from the server
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        // Display the response from the server
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("From server: " + modifiedSentence);

        // Close the socket
        clientSocket.close();
    }
}
