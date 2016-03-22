package net.tianzx.freevoice.udp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import net.tianzx.freevoice.CommonData;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by tianzx on 2016/3/22.
 */
public class UDPAcceptService extends Service{

    private boolean needRunServer = true;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //running along,response to receive udp message

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket udpSocket = null;
                byte data[] = new byte[1024];
                DatagramPacket udpPacket = new DatagramPacket(data,data.length);
                try {
                    udpSocket = new DatagramSocket(CommonData.Instance.UDP_PORT);
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                while (needRunServer){
                    try {
                        udpSocket.receive(udpPacket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(udpPacket.getLength()!=0){
                        String theMsg = new String(data,0,udpPacket.getLength());
                        //1:return to surface
                            //func: use broadcast

                    }
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        needRunServer = false;
        return super.stopService(name);
    }
}
