package ConnectionUtils;

import PointerUtils.DataInfo;

import java.io.IOException;
import java.net.*;

public class UDPWrapper extends NetworkManager{
    private static final int PORT = 12234;

    public UDPWrapper(DataReceivedListener listener) {
        mListener = listener;
    }

    boolean isRunning;

    DatagramSocket socket;

    public void startListenting() {
        if(listenerThread != null)
            return;
        listenerThread = new Thread() {
            @Override
            public void run() {
                isRunning = true;
                try {
                    socket = new DatagramSocket(PORT);

                    while (isRunning) {
                        byte buffer[] = new byte[BUFFER_SIZE];
                        DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);
                        socket.receive(packet);
                        mListener.onDataReceived(packet.getData());
                    }
                    listenerThread = null;
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        listenerThread.start();
    }
}
