package ConnectionUtils;

import PointerUtils.DataInfo;

import java.io.IOException;
import java.net.*;

public abstract class NetworkManager {
    static final int BUFFER_SIZE = 16000;
    DataReceivedListener mListener;
    static final int BROADCASTER_PORT = 12235;
    static final int BROADCAST_RECEIVER_PORT = 12236;

    Thread broadCasterThread;
    Thread receiverThread;
    Thread listenerThread;

    DatagramSocket broadcastSocket;
    DatagramSocket receiverSocket;

    public boolean isBroadcasting;
    public boolean isReceiving;
    String IP_Address;

    UDPWrapper udpWrapper;
    TCPWrapper tcpWrapper;

    public NetworkManager() {};

    public NetworkManager(DataReceivedListener listener) {
        mListener = listener;
        tcpWrapper = new TCPWrapper(listener);
        udpWrapper = new UDPWrapper(listener);
    }

    public void setBroadcasting(boolean broadcasting) {
        isBroadcasting = broadcasting;
    }

    public void setReceiving(boolean receiving) {
        isReceiving = receiving;
    }

    public void sendConnectionPacket() {
        if(broadCasterThread != null) {
            return;
        }
        isBroadcasting = true;
        try {
            if(broadcastSocket == null) {
                broadcastSocket = new DatagramSocket(BROADCAST_RECEIVER_PORT);
            }
            String data = System.currentTimeMillis() + "," + DataInfo.BROADCAST_MESSAGE + "," + InetAddress.getLocalHost().getHostName() + "," +System.getProperty("os.name") + "," +System.getProperty("os.version");
            byte[] buffer = data.getBytes();
            broadCasterThread = new Thread() {
                public void run() {
                    while(isBroadcasting) {
                        try {
                            InetAddress mIPAddress = InetAddress.getByName(IP_Address);
                            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, mIPAddress, BROADCAST_RECEIVER_PORT);
                            broadcastSocket.send(packet);
                            startListening();
                            //System.out.println("Sent Packet to " + mIPAddress.getHostAddress() + " " +mIPAddress.getHostName());
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Error sending packet!");
                        }
                    }
                    broadCasterThread = null;
                }
            };
            broadCasterThread.start();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void receiveConnection() {
        if(receiverThread != null)
            return;
        isReceiving = true;

        try {
            receiverSocket = new DatagramSocket(BROADCASTER_PORT);
            receiverThread = new Thread() {
                public void run() {
                    while (isReceiving) {
                        byte buffer[] = new byte[BUFFER_SIZE];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        try {
                            receiverSocket.receive(packet);
                            IP_Address = packet.getAddress().getHostAddress();
                            mListener.onDataReceived(packet.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    receiverThread = null;
                }
            };
            receiverThread.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
        //tcpWrapper.startListening();
        udpWrapper.startListenting();
    }
}
