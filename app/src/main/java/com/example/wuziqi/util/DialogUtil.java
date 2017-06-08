package com.example.wuziqi.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * 作者：国富小哥
 * 日期：2017/6/8
 * Created by Administrator
 */

public class DialogUtil {

    private Activity mActivity;
    public DialogUtil(Activity mActivity){
       this.mActivity=mActivity;
    }

    /**
     * 退出游戏的对话框
     * */
    public  void showBackDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(mActivity);
        builder.setTitle("提示");
        builder.setMessage("您确定要退出游戏吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mActivity.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
