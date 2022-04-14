package com.example.smart3_25.util.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridVIew  extends GridView {
    public MyGridVIew(Context context) {
        super(context);
    }

    public MyGridVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyGridVIew(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, i);
    }
}
