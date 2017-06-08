package com.example.wuziqi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wuziqi.util.DialogUtil;

public class MainActivity extends AppCompatActivity {

    private MyView mMyView;
    private DialogUtil dialogUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mMyView= (MyView) findViewById(R.id.id_my_view);
        dialogUtil=new DialogUtil(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.recome:
                mMyView.start();
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
                // 按下BACK，同时没有重复  
            dialogUtil.showBackDialog();

          }
        return false;
    }




//    private long exitTime=0;
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
//            if ((System.currentTimeMillis()-exitTime)>2000){
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                exitTime=System.currentTimeMillis();
//            }else {
//                finish();
//            }
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }


}
