package com.example.wuziqi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：国富小哥
 * 日期：2017/6/8
 * Created by Administrator
 */

public class MyView extends View{
    private int mPanelWidth;//棋盘宽度
    private float mLineHeight;//行高
    private int MAX_LINE=10;//总行数
    private final String TAG="MyView";

    private Paint mPaint;//画笔
    //引入棋子的图片
    private Bitmap mWhitePiece;
    private Bitmap mBlackPiece;

    //比例
    private float ratioPieceOfLineHeight=3*1.0f/4;

    List<Point> whitePointList=new ArrayList<>();
    List<Point> blackPointList=new ArrayList<>();
    //当前为白棋
    private boolean isWhite=true;


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG,"构造方法执行了");
        //View的背景颜色，易于区分布局
        setBackgroundColor(0x44ff0000);
        initPaint();
        initBitmap();
    }

    private void initBitmap() {
        mWhitePiece= BitmapFactory.decodeResource(getResources(),R.drawable.stone_w2);
        mBlackPiece=BitmapFactory.decodeResource(getResources(),R.drawable.stone_b1);
    }

    /**
     * 初始化画笔
     * */
    private void initPaint() {
        mPaint=new Paint();
        mPaint.setColor(0x88000000);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置防抖动。
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
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
        Log.d(TAG,"onMeasure方法执行了");
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG,"onSizeChanged方法执行了");
        mPanelWidth=w;
        mLineHeight=mPanelWidth*1.0f/MAX_LINE;

        //棋子图片大小
        int pieceWidth= (int) (mLineHeight*ratioPieceOfLineHeight);
        mWhitePiece=Bitmap.createScaledBitmap(mWhitePiece,pieceWidth,pieceWidth,false);
        mBlackPiece=Bitmap.createScaledBitmap(mBlackPiece,pieceWidth,pieceWidth,false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先画棋盘
        drawBoard(canvas);
        //再画棋子
        drawPiece(canvas);
    }

    /**
     * 画棋子
     * */
    private void drawPiece(Canvas canvas) {
        for (int i=0;i<whitePointList.size();i++){
            Point whitePoint=whitePointList.get(i);
            canvas.drawBitmap(mWhitePiece,(whitePoint.x+(1-ratioPieceOfLineHeight)/2)*mLineHeight,(whitePoint.y+(1-ratioPieceOfLineHeight)/2)*mLineHeight,null);

        }

        for (int i=0;i<blackPointList.size();i++){
            Point blackPoint=blackPointList.get(i);
            canvas.drawBitmap(mBlackPiece,(blackPoint.x+(1-ratioPieceOfLineHeight)/2)*mLineHeight,(blackPoint.y+(1-ratioPieceOfLineHeight)/2)*mLineHeight,null);

        }
    }

    /**
     * 画棋盘
     * */
    private void drawBoard(Canvas canvas) {
    //拿到棋盘宽度和行高
        int w=mPanelWidth;
        float lineHeight=mLineHeight;

        //先画横线
        //drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
        for (int i=0;i<MAX_LINE;i++){
            //x轴起始处
            int startX= (int) (lineHeight/2);
            //y轴起始处
            int startY= (int) ((0.5+i)*lineHeight);
            //x轴结束处
            int endX= (int) (w-lineHeight/2);
            //y轴结束处
            int endY= (int) ((0.5+i)*lineHeight);
            canvas.drawLine(startX,startY,endX,endY,mPaint);
        }
        //再画纵线
        for (int i=0;i<MAX_LINE;i++){
            //x轴起始处
            int startX=(int) ((0.5+i)*lineHeight);
            //y轴起始处
            int startY=(int) (lineHeight/2);
            //x轴结束处
            int endX=(int) ((0.5+i)*lineHeight);
            //y轴结束处
            int endY=(int) (w-lineHeight/2);
            canvas.drawLine(startX,startY,endX,endY,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();
        if (action==MotionEvent.ACTION_UP){
            int x= (int) event.getX();
            int y= (int) event.getY();
            Point point=getValidPoint(x,y);
            if (whitePointList.contains(point)||blackPointList.contains(point)){
                return false;
            }
            if (isWhite){
                whitePointList.add(point);
            }else {
                blackPointList.add(point);
            }
            //重绘
            invalidate();
            isWhite=!isWhite;

        }

        return true;
    }

    private Point getValidPoint(int x, int y) {
        return new Point((int)( x/mLineHeight),(int) (y/mLineHeight));
    }
}
