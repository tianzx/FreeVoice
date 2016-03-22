package net.tianzx.freevoice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tianzx on 2016/3/21.
 */
public class UserOnlineListAdapter extends BaseAdapter {
    private FreeVoiceActivity activity = null;
    private List<UserModel> userList;

    public UserOnlineListAdapter(FreeVoiceActivity activity, List<UserModel> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.onlinelist_item, null);
        ImageButton otherImg = (ImageButton) itemView.findViewById(R.id.other_img);
        TextView otherInfo = (TextView) itemView.findViewById(R.id.other_info);
        ImageButton sendMsg = (ImageButton) itemView.findViewById(R.id.send_msg);
        ImageButton sendTel = (ImageButton) itemView.findViewById(R.id.send_tel);

//        otherInfo.setText(this.activity.getResources().getString(R.string.hello));
        otherInfo.setText(this.userList.get(position).getName());
        return itemView;
    }
}
