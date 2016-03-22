package net.tianzx.freevoice;

/**
 * Created by tianzx on 2016/3/22.
 */
public enum  CommonData {
    Instance;

    public final int UDP_PORT = 12345;
    public final String UDP_IP_PRE = "192.168.199.";
    public final String RECEIVER_UDP_MSG = "net.tianzx.freevoice.FreeVoiceActivity.UdpMsgReceiver";
    public final String UDP_MSG = "Udp_Msg";
}
