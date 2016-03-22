package net.tianzx.freevoice.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by tianzx on 2016/3/21.
 */
public class UdpSend extends Thread {

    private String sendMsg;
    private String toIp;

    private UdpSend(String msg, String toIp) {
        this.sendMsg = msg;
        this.toIp = toIp;
    }

    @Override
    public void run() {

        if (toIp != null && toIp.trim().length() > 0) {
            sendUDP(toIp);
        }
        for (int i = 2; i < 255; i++) {
            String ip = "192.168.199." + i;
            sendUDP(ip);
        }
    }

    private void sendUDP(String ip) {
        DatagramPacket datagramPacket = null;
        DatagramSocket udpSocket = null;
        try {
            udpSocket = new DatagramSocket();
            datagramPacket = new DatagramPacket(new byte[1024], 1024);
            datagramPacket.setData(this.sendMsg.getBytes());
            datagramPacket.setLength(this.sendMsg.length());
            datagramPacket.setPort(12345);
            InetAddress addr = InetAddress.getByName(ip);
            datagramPacket.setAddress(addr);

            udpSocket.send(datagramPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            udpSocket.close();
        }

    }
}
