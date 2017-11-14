package myfirstgame.todolist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by James on 14/11/2017.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "americanTypwriterRegular.ttf");
        this.setTypeface(face);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "americanTypwriterRegular.ttf");
        this.setTypeface(face);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "americanTypwriterRegular.ttf");
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }
}