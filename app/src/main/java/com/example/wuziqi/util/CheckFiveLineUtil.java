package com.example.wuziqi.util;

import android.graphics.Point;

import java.util.List;

/**
 * 作者：国富小哥
 * 日期：2017/6/8
 * Created by Administrator
 */

public class CheckFiveLineUtil {

    private static int MAX_COUNT_IN_LINE=5;

    /**
     * 检查横向是否有五子连珠
     * */
    public static  boolean checkHorizontal(int x, int y, List<Point> pointList) {
        int count=1;
        //左边
        for (int i=1;i<MAX_COUNT_IN_LINE;i++){
            if (pointList.contains(new Point(x-i,y))){
                count++;
            }else {
                break;
            }
        }
        if (count==MAX_COUNT_IN_LINE){return true;}
        //右边
        for (int i=1;i<MAX_COUNT_IN_LINE;i++){
            if (pointList.contains(new Point(x+i,y))){
                count++;
            }else {
                break;
            }
        }
        if (count==MAX_COUNT_IN_LINE){return true;}
        return false;
    }


    /**
     * 检查纵向是否有五子连珠
     * */
    public static boolean checkVertical(int x, int y, List<Point> pointList) {
        int count=1;
        //上边
        for (int i=1;i<MAX_COUNT_IN_LINE;i++){
            if (pointList.contains(new Point(x,y-i))){
                count++;
            }else {
                break;
            }
        }
        if (count==MAX_COUNT_IN_LINE){return true;}
        //下边
        for (int i=1;i<MAX_COUNT_IN_LINE;i++){
            if (pointList.contains(new Point(x,y+i))){
                count++;
            }else {
                break;
            }
        }
        if (count==MAX_COUNT_IN_LINE){return true;}
        return false;
    }


    /**
     * 检查左斜向是否有五子连珠
     * */
    public static boolean checkLeftDiagonal(int x, int y, List<Point> pointList) {
        int count=1;
        //上边
        for (int i=1;i<MAX_COUNT_IN_LINE;i++){
            if (pointList.contains(new Point(x-i,y+i))){
                count++;
            }else {
                break;
            }
        }
        if (count==MAX_COUNT_IN_LINE){return true;}
        //下边
        for (int i=1;i<MAX_COUNT_IN_LINE;i++){
            if (pointList.contains(new Point(x+i,y-i))){
                count++;
            }else {
                break;
            }
        }
        if (count==MAX_COUNT_IN_LINE){return true;}
        return false;
    }


    /**
     * 检查右斜向向是否有五子连珠
     * */
    public static boolean checkRightDiagonal(int x, int y, List<Point> pointList) {
        int count=1;
        //上边
        for (int i=1;i<MAX_COUNT_IN_LINE;i++){
            if (pointList.contains(new Point(x-i,y-i))){
                count++;
            }else {
                break;
            }
        }
        if (count==MAX_COUNT_IN_LINE){return true;}
        //下边
        for (int i=1;i<MAX_COUNT_IN_LINE;i++){
            if (pointList.contains(new Point(x+i,y+i))){
                count++;
            }else {
                break;
            }
        }
        if (count==MAX_COUNT_IN_LINE){return true;}
        return false;
    }
}
