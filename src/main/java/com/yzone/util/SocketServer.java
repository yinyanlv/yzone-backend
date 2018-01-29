package com.yzone.util;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class SocketServer {

    public static void run() {

        int port = 8889;

        Configuration config = new Configuration();

        config.setHostname("localhost");
        config.setPort(port);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {

                String address = client.getRemoteAddress().toString();

                System.out.println(address + " has connected");

                client.sendEvent("progress", Math.random());
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {

                String address = client.getRemoteAddress().toString();

                System.out.println(address + " has disconnected");
            }
        });

        server.addEventListener("progress", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {

            }
        });

        server.start();
        System.out.println("socket io server is listening on port " + port + "!");
    }
}
