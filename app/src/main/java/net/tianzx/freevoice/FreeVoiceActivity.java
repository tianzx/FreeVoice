package net.tianzx.freevoice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FreeVoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_voice);
        initMain();
    }

    private void initMain() {
        this.initSelfInfo();
        this.initRefreshInfo();
        this.initOnlineList();
    }

    private void initSelfInfo() {

    }
    private void initRefreshInfo(){

    }
    private void initOnlineList(){
        final ListView online = (ListView) this.findViewById(R.id.list_online);
//        online.setAdapter(adaptor);
        List<UserModel> list = new ArrayList<UserModel>();
        UserModel um = new UserModel();
        um.setName("User One");
        list.add(um);
        online.setAdapter(new UserOnlineListAdapter(this,list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_free_voice, menu);
        menu.add(1,1,1,"参数设置");
        menu.add(1,2,2,"退出");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case 1:
                break;
            case 2:
                exit();
                break;
        }
        return true;
    }

    private  void  exit() {
        //exit
        this.finish();
        //
        System.exit(0);
    }
}
