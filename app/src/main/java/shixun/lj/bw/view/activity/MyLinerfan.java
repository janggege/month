package shixun.lj.bw.view.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import shixun.lj.bw.R;


/*
  name:刘江
  data:2019
*/public class MyLinerfan extends LinearLayout implements View.OnClickListener {

    public EditText editText;

    public interface onclick1 {
        void showdata(String s);

        void fan();
    }

    onclick1 onclick;


    public void setOnclick(onclick1 onclick) {
        this.onclick = onclick;
    }

    public MyLinerfan(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context, attrs);
    }


    public MyLinerfan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initview(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.mylinerfan, null, false);
        addView(view);
        editText = view.findViewById(R.id.ftext1);
        view.findViewById(R.id.ftext2).setOnClickListener(this);
        view.findViewById(R.id.fimage1).setOnClickListener(this);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ftext2:
                String s = editText.getText().toString();
                if (!s.isEmpty()) {
                    onclick.showdata(s);
                }
                break;
            case R.id.fimage1:
                onclick.fan();

                break;
        }

    }
}
