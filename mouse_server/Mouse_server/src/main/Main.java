package main;

import ConnectionUtils.DataReceivedListener;
import ConnectionUtils.NetworkManager;
import ConnectionUtils.UDPWrapper;
import DataUtils.DataManager;
import PointerUtils.Keyboard;
import PointerUtils.Mouse;
import PointerUtils.UtilItem;

import java.awt.*;

public class Main {
    public static NetworkManager networkManager;
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            Mouse mouse = new Mouse(robot);
            Keyboard keyboard = new Keyboard(robot);
            DataManager manager = new DataManager(mouse, keyboard);
            networkManager = new NetworkManager(new DataReceivedListener() {
                @Override
                public void onDataReceived(byte[] bytes) {
                    UtilItem data = new UtilItem(new String(bytes));
                    manager.onDataReceived(data);
                }
            }) {
            };
            manager.setNetworkManager(networkManager);

            networkManager.receiveConnection();
            System.out.println("Server Started!");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
