package com.example.zengcanwen.myframeanimation;

/**
 * Created by zengcanwen on 2018/1/10.
 */

public class FrameAnimationContral {
    private int[] picRes ;
    private int durations ;
    private int delay ;
    private boolean repeat ;

    public FrameAnimationContral() {
        durations = 100 ;
        delay = 1000 ;
        repeat = true ;
    }

    public int[] getPicRes() {
        return picRes;
    }

    public void setPicRes(int[] picRes) {
        this.picRes = picRes;
    }

    public int getDurations() {
        return durations;
    }

    public void setDurations(int durations) {
        this.durations = durations;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
