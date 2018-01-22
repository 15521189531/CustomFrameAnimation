package com.example.zengcanwen.myframeanimation;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.lang.ref.WeakReference;


/**
 * Created by zengcanwen on 2018/1/10.
 */

public class CustomFrameAnimation extends Handler {

    private FrameAnimationContral field ;
    private ImageView imageView ;
    private static volatile boolean isPause ;
    private int[] picRes ;
    private int delay ;
    private int durations ;
    private boolean repeat ;
    private int lastFrame ;
    private int currentFrame ;


    public CustomFrameAnimation(FrameAnimationContral field , ImageView imageView){
        this.field = field ;
        this.imageView = imageView ;
        init() ;
    }

    private void init(){
        picRes = field.getPicRes() ;
        delay = field.getDelay() ;
        durations = field.getDurations() ;
        repeat = field.isRepeat() ;
        isPause = true ;
        lastFrame = picRes.length - 1 ;
        currentFrame = 0 ;
    }

    public interface MyFrameAnimationListener{
        void animationStart() ;
        void animationPlaying() ;
        void animationEnd() ;
    }

    private MyFrameAnimationListener myFrameAnimationListener ;

    public void setMyFrameAnimationListener(MyFrameAnimationListener myFrameAnimationListener){
        this.myFrameAnimationListener = myFrameAnimationListener ;
    }

    public void start(){

        isPause = false ;
        MyThread myThread = new MyThread(this) ;
        myThread.start();
    }


    private static class MyThread extends Thread{
        WeakReference<CustomFrameAnimation> weakReference ;
        public MyThread(CustomFrameAnimation zdyFrameAnimation){
            weakReference = new WeakReference<CustomFrameAnimation>(zdyFrameAnimation) ;
        }
        @Override
        public void run() {
            CustomFrameAnimation zdyFrameAnimation = weakReference.get() ;
            while (!zdyFrameAnimation.isPause){
                //开始
                if(zdyFrameAnimation.currentFrame == 0){
                    zdyFrameAnimation.sendMessage(zdyFrameAnimation.obtainMessage(101)) ;
                }

                //进行
                zdyFrameAnimation.sendMessage(zdyFrameAnimation.obtainMessage(102)) ;

                //结束
                if(zdyFrameAnimation.currentFrame == zdyFrameAnimation.lastFrame){
                    zdyFrameAnimation.sendMessage(zdyFrameAnimation.obtainMessage(103)) ;
                    if(zdyFrameAnimation.repeat){
                        zdyFrameAnimation.currentFrame = 0 ;

                        try {
                            Thread.sleep(zdyFrameAnimation.delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;

                    }else {
                        return;
                    }
                }

                zdyFrameAnimation.currentFrame ++ ;

                try {
                    Thread.sleep(zdyFrameAnimation.durations);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 101:      //开始
                myFrameAnimationListener.animationStart();
                break;

            case 102 :      //进行
                imageView.setImageResource(picRes[currentFrame]);
                myFrameAnimationListener.animationPlaying();
                break;

            case 103:       //结束
                myFrameAnimationListener.animationEnd();
                break;
        }
    }


    public void setPuase(){
        isPause = true ;
    }

    public boolean isRunning(){
        return !isPause ;
    }

    public boolean isRepeat(){
        return repeat ;
    }


}
