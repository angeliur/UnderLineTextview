package com.demo.underlinetextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
//如果Textview换行的话，每行都会有下划线的
public class UnderLineTextview extends TextView {

    private float density;
    private TypedArray typedArray;
    private int mColor;
    private float mStrokeWidth;
    private Rect mRect;
    private Paint mPaint;
    private int firstCharInLine;
    private int lastCharInLine;
    private float xStart;
    private float xStop;
    private float xDiff;

    public UnderLineTextview(Context context) {
        this(context,null);
    }

    public UnderLineTextview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UnderLineTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取屏幕密度
        density = context.getResources().getDisplayMetrics().density;
        //获取自定义属性
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.UnderlinedTextView, defStyleAttr, 0);
        mColor = typedArray.getColor(R.styleable.UnderlinedTextView_underlineColor, 0xFFFF0000);
        mStrokeWidth = typedArray.getDimension(R.styleable.UnderlinedTextView_underlineWidth, density * 2);
        typedArray.recycle();

        mRect = new Rect();
        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.STROKE);
    }

//    TextView下列属性：
//
//    TextView 的 layout里面包含各种获取字符位置、行数、列数等的方法
//    layout.getLineForOffset 获取该字符所在行数.
//    layout.getLineBounds获取该行的外包矩形（Rect) 这样 这个字符的顶部Y坐标就是rect的top 底部Y坐标就是rect的bottom
//    layout.getPrimaryHorizontal获取该字符左边的X左边
//    layout.getSecondaryHorizontal获取该字符字符的右边X坐标

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(mStrokeWidth);
        //得到TextView显示有多少行
        int count = getLineCount();
        //得到TextView的布局
        Layout layout = getLayout();
        for (int i = 0; i < count; i++) {
            //getLineBounds得到这一行的外包矩形,
            // 这个字符的顶部Y坐标就是rect的top 底部Y坐标就是rect的bottom
            int baseline = getLineBounds(i, mRect);
            firstCharInLine = layout.getLineStart(i);
            lastCharInLine = layout.getLineEnd(i);

            xStart = layout.getPrimaryHorizontal(firstCharInLine);
            xDiff = layout.getPrimaryHorizontal(firstCharInLine + 1) - xStart;
            xStop = layout.getPrimaryHorizontal(lastCharInLine - 1) + xDiff;
//            xStop = layout.getSecondaryHorizontal(lastCharInLine);

            canvas.drawLine(xStart,baseline + mStrokeWidth,xStop,baseline + mStrokeWidth,mPaint);

        }
    }

    public int getUnderLineColor() {
        return mColor;
    }

    public void setUnderLineColor(int mColor) {
        this.mColor = mColor;
        invalidate();
    }

    public float getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        invalidate();
    }
}
