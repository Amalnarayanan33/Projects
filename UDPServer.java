import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        // Create a datagram socket on port 12345
        DatagramSocket serverSocket = new DatagramSocket(12345);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            // Receive a datagram packet from a client
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            // Extract the data from the packet
            String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Convert the data to uppercase and send it back to the client
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
