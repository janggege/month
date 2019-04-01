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
*/public class Myview extends LinearLayout implements View.OnClickListener {

    private EditText ed;
    int sum = 1;

    public Myview(Context context) {
        super(context);
    }

    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }


    public Myview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initview(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.myview2, null, false);
        addView(view);
        view.findViewById(R.id.jia).setOnClickListener(this);
        view.findViewById(R.id.jian).setOnClickListener(this);
        ed = view.findViewById(R.id.ed1);
        ed.setText(sum+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jian:
                sum++;
                ed.setText(sum+"");
                onclick.inclick();
                break;
            case R.id.jia:
                if (sum > 1) {
                    sum--;
                    ed.setText(sum+"");
                    onclick.inclick();
                }
                break;

        }
    }

    public interface onclick {
        void inclick();
    }

    onclick onclick;

    public void setOnclick(Myview.onclick onclick) {
        this.onclick = onclick;
    }
}
