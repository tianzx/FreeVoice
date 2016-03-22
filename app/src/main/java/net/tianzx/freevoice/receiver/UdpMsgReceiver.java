package net.tianzx.freevoice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import net.tianzx.freevoice.CommonData;
import net.tianzx.freevoice.FreeVoiceActivity;

/**
 * Created by tianzx on 2016/3/22.
 */
public class UdpMsgReceiver extends BroadcastReceiver{

    private FreeVoiceActivity activity = null;

    public UdpMsgReceiver(FreeVoiceActivity activity) {
        this.activity = activity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        //receive broadcast, and process really message
        intent.getExtras().get(CommonData.Instance.UDP_MSG);

    }
}
