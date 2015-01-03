package com.squareup.timessquare.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("NewApi")
public class CalendarPage extends FragmentActivity implements
		OnPageChangeListener{
	private int position;
	private CalendarPickerView calendarPickView;
	private TextView title;
	private int cellSize;
	private LinearLayout headerRow;
	private float rowFive;
	private float rowSix;
	private float rowFour;

	@SuppressLint({ "SimpleDateFormat", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_picker);

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
				getSupportFragmentManager());

		calendarPickView.setVerticalScrollBarEnabled(false);
		calendarPickView.setEnabled(false);
		calendarPickView.setOnPageChangeListener(this);

		// 初始化Calendar位置
		calendarPickView.setLayoutParams(new LinearLayout.LayoutParams(
				calendarPickView.getLayoutParams().width, (cellSize * 6)));
		position = calendarPickView.selectedIndex;

		// title部分
		title = (TextView) findViewById(R.id.title);
		title.setText(new SimpleDateFormat("MMMM yyyy").format(targetDate
				.getTime()));

		// 星期部分
		headerRow = (LinearLayout) findViewById(R.id.weekRow);
		for (int c = Calendar.SUNDAY; c <= Calendar.SATURDAY; c++) {
			targetDate.set(Calendar.DAY_OF_WEEK, c);
			final TextView textView = (TextView) headerRow.getChildAt(c - 1);
			textView.setText(new SimpleDateFormat("EEE").format(targetDate
					.getTime()));
		}

		findViewById(R.id.next).setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				if (position < calendarPickView.months.size() - 1) {
					calendarPickView.setCurrentItem(position++);
				}
			}
		});
		findViewById(R.id.prev).setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				if (position > 0) {
					calendarPickView.setCurrentItem((position--));
				}
			}
		});

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
