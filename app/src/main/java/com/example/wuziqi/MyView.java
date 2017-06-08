package com.example.wuziqi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：国富小哥
 * 日期：2017/6/8
 * Created by Administrator
 */

public class MyView extends View{


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //View的背景颜色，易于区分布局
        setBackgroundColor(0x44ff0000);
    }

    /**
     * 当xml文件集成自定义View时调用此方法
     * 该方法指定该控件在屏幕上的大小
     * 第一：计算控件的实际大小
     * 第二：设置控件的实际大小
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //通过辅助类获取size尺寸和mode模式
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);

        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        //当widthMode和heightMode为精确模式时
        int width=Math.min(widthSize,heightSize);
        //当widthMode和heightMode为未指定尺寸模式时
        if (widthMode==MeasureSpec.UNSPECIFIED){
            width=heightSize;
        }else if (heightMode==MeasureSpec.UNSPECIFIED){
            width=widthSize;
        }

        //调用setMeasuredDimension(int, int)设置实际大小
        setMeasuredDimension(width,width);
    }
}
