package com.example.zengcanwen.myframeanimation;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by zengcanwen on 2018/1/10.
 */

public class CustomFrameAnimationActivity extends AppCompatActivity {

    private Button button ;
    private Button button1 ;
    private CustomFrameAnimation zdyFrameAnimation ;
    private FrameAnimationContral frameAnimationField ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view_layout);
        ImageView imageView = (ImageView)findViewById(R.id.image_view) ;
        button = (Button)findViewById(R.id.button) ;
        button1 = (Button)findViewById(R.id.button1) ;
        int[] picRes = getPicRes() ;
        frameAnimationField = new FrameAnimationContral() ;
        frameAnimationField.setPicRes(picRes);
        zdyFrameAnimation = new CustomFrameAnimation(frameAnimationField , imageView) ;
        zdyFrameAnimation.setMyFrameAnimationListener(new CustomFrameAnimation.MyFrameAnimationListener() {
            @Override
            public void animationStart() {
                Log.i("aaaaa" , "animationStart---------------------->") ;
            }

            @Override
            public void animationPlaying() {
                Log.i("aaaaa" , "animationPlaying---------------------->") ;
            }

            @Override
            public void animationEnd() {
                Log.i("aaaaa" , "animationEnd---------------------->") ;

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!zdyFrameAnimation.isRunning()){
                    zdyFrameAnimation.start();
                }

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(zdyFrameAnimation.isRunning()){
                    zdyFrameAnimation.setPuase();
                }

            }
        });
    }


    private int[] getPicRes(){
        TypedArray typedArray = getResources().obtainTypedArray(R.array.picture_array) ;
        int len = typedArray.length() ;
        int[] picRes = new int[len] ;
        for(int i = 0 ; i < len ; i++){
            picRes[i] = typedArray.getResourceId(i , -1) ;
        }
        typedArray.recycle();
        return picRes ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zdyFrameAnimation.setPuase();
    }
}
