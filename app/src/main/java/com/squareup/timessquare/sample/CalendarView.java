package com.squareup.timessquare.sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarView extends RelativeLayout implements OnPageChangeListener {
	private int position;
	private CheckedTextView title;
    private int cellSize;
    private float rowFive;
    private float rowSix;
    private float rowFour;
    private LinearLayout mLinearCalendar;
    private RelativeLayout mRelativeHead;
    private CalendarPickerView calendarPickView;


    public CalendarView(Context context) {
        super(context);
    }

    AnimUtils animUtils = new AnimUtils();
    int count = 0;

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.calendar_picker,this);
        // 計算每天格子的大小 為調整buttom做準備
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        cellSize = displayMetrics.widthPixels / 7;

        // 初始化CalendarPickView的時間跨度
        final Calendar targetDate = Calendar.getInstance();
        int day = targetDate.get(Calendar.DATE);
        int maxday = targetDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        targetDate.add(Calendar.DATE,maxday-day);

        calendarPickView = (CalendarPickerView) findViewById(R.id.calendar_view);
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.YEAR,-1);
        Date startDate = startTime.getTime();

        // 开始初始化Calendar
        calendarPickView.init(new Date(), startDate, targetDate.getTime(),
                ((FragmentActivity)context).getSupportFragmentManager());

        calendarPickView.setVerticalScrollBarEnabled(false);
        calendarPickView.setEnabled(false);
        calendarPickView.setOnPageChangeListener(this);

        // 初始化Calendar位置
        calendarPickView.setLayoutParams(new LinearLayout.LayoutParams(
                calendarPickView.getLayoutParams().width, (cellSize * 6)));
        position = calendarPickView.selectedIndex;
        Log.e("position:11",position+"");

        mRelativeHead = (RelativeLayout) findViewById(R.id.head);
        mLinearCalendar = (LinearLayout) findViewById(R.id.linear_view);
        mLinearCalendar.setTranslationY(-3000);
        // title部分
        title = (CheckedTextView) findViewById(R.id.title);
        title.setText(new SimpleDateFormat("MMMM yyyy").format(targetDate
                .getTime()));

        title.setChecked(false);
        title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count%2==0){
                    animUtils.showAnimate(mLinearCalendar);
                }else{
                    animUtils.dismissAnimate(mLinearCalendar);
                }
                count++;
            }
        });

        // 星期部分
        LinearLayout headerRow = (LinearLayout) findViewById(R.id.weekRow);
        for (int c = Calendar.SUNDAY; c <= Calendar.SATURDAY; c++) {
            targetDate.set(Calendar.DAY_OF_WEEK, c);
            final TextView textView = (TextView) headerRow.getChildAt(c - 1);
            textView.setText(new SimpleDateFormat("EEE").format(targetDate
                    .getTime()));
        }

        findViewById(R.id.next).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });
        findViewById(R.id.prev).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

            }
        });
    }


    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


	// 改变bottom位置
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@SuppressLint("NewApi")
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
	}

	@SuppressLint("NewApi")
	@Override
	public void onPageSelected(int arg0) {
		title.setText(calendarPickView.months.get(arg0).getLabel());
		// 计算在不同行數的情况下 bottom的位置
		if (rowFive == 0 || rowSix == 0 || rowFour == 0) {
			rowFive = calendarPickView.getY() + (cellSize * 5);
			rowSix = calendarPickView.getY() + (cellSize * 6);
			rowFour = calendarPickView.getY() + (cellSize * 4);
		}
	}


}
