package net.tianzx.freevoice;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by tianzx on 2016/3/22.
 */
public enum  CommonData {
    Instance;

    public final int UDP_PORT = 12345;
    public final String UDP_IP_PRE = "192.168.199.";
    public final String RECEIVER_UDP_MSG = "net.tianzx.freevoice.FreeVoiceActivity.UdpMsgReceiver";
    public final String UDP_MSG = "Udp_Msg";

    public static String getIP(Context ctx){
        WifiManager wifi = (WifiManager)ctx.getSystemService(ctx.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();

        return intToIp(info.getIpAddress());
    }
    private static String intToIp(int ip){
        return (ip&0xFF)+"."+((ip>>8)&0xFF)+"."+((ip>>16)&0xFF)+"."+((ip>>24)&0xFF);
    }
}
