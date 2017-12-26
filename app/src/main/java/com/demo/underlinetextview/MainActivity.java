package com.demo.underlinetextview;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private UnderLineTextview ul_tv;
    private UnderLineTextview2 ul_tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        //文本添加下划线的几种方法

        tv1 = (TextView) findViewById(R.id.tv1);//将要处理的文字写到一个资源文件，如string.xml（使用html用法格式化）
        //当文字中出现URL、E-mail、电话号码等的时候，可以将TextView的android:autoLink属性设置为相应的的值，如果是所有的类型都出来就是android:autoLink="all"，当然也可以在java代码里 做,textView01.setAutoLinkMask(Linkify.ALL);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);//用Html类的fromHtml（）方法格式化要放到TextView里的文字
        tv4 = (TextView) findViewById(R.id.tv4);//设置TextView的Paint属性：tvTest.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        tv5 = (TextView) findViewById(R.id.tv5);//用Spannable或实现它的类，如SpannableString来格式部分字符串

        ul_tv = (UnderLineTextview) findViewById(R.id.ul_tv);//自定义的可以设置下划线颜色宽度的Textview
        ul_tv2 = (UnderLineTextview2) findViewById(R.id.ul_tv2);//自定义的可以设置下划线颜色宽度的Textview
    }

    private void initData() {

        tv3.setText(Html.fromHtml("联系电话：<u>333333</u>"));
        tv3.setTextColor(Color.BLUE);

        tv4.setText("联系电话：444444");
        TextPaint paint = tv4.getPaint();
        paint.setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置下划线
        paint.setAntiAlias(true);//抗锯齿


        CharSequence str = "联系电话：555555";
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new UnderlineSpan(),0,str.length(),0);
        tv5.setText(spannableString);


        ul_tv.setText("联系电话：1008611" + "\n" + "hello world");
        ul_tv.setUnderLineColor(Color.BLUE);
        ul_tv.setStrokeWidth(3);

        ul_tv2.setText("联系电话：1425926" + "\n" + "this is a world");
//        ul_tv2.setUnderLineColor(Color.GREEN);
//        ul_tv2.setUnderlineHeight(8);


    }
}
