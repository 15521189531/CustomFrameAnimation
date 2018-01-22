package com.example.zengcanwen.myframeanimation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by zengcanwen on 2018/1/10.
 */

public class AnimationDrawableActivity extends AppCompatActivity {
    private Button button1 ;
    private Button button2 ;
    private AnimationDrawable animationDrawable ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view_layout);
        ImageView imageView = (ImageView)findViewById(R.id.image_view) ;
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.myanimationlist));
        animationDrawable = (AnimationDrawable) imageView.getDrawable() ;
        button1 = (Button)findViewById(R.id.button) ;
        button2 = (Button)findViewById(R.id.button1) ;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!animationDrawable.isRunning()){
                    animationDrawable.start();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(animationDrawable.isRunning()){
                    animationDrawable.stop();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycleDrawable();
    }

    private void recycleDrawable(){
        for(int i = 0 ; i < animationDrawable.getNumberOfFrames() ; i ++){
            Drawable drawable =  animationDrawable.getFrame(i) ;
            if( drawable != null && drawable instanceof BitmapDrawable  ){
                ((BitmapDrawable)drawable).getBitmap().recycle();
                drawable.setCallback(null);
            }
        }
        animationDrawable.setCallback(null);
        System.gc();
    }
}
