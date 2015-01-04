package com.squareup.timessquare;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.squareup.timessquare.sample.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Package : com.squareup.timessquare
 * Author : jacob
 */
public class MonthContentView extends LinearLayout {

    private int dividerColor;
    private int dayBackgroundResId;
    private int dayTextColorResId;
    private boolean displayHeader;
    private int headerTextColor;

    private MonthView monthView;
    private LayoutInflater layoutInflater;

    public MonthContentView(Context context) {
        super(context);
    }

    public MonthContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        layoutInflater = LayoutInflater.from(context);
        Resources res = context.getResources();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CalendarPickerView);
        dividerColor = a.getColor(R.styleable.CalendarPickerView_dividerColor,
                res.getColor(R.color.calendar_divider));
        dayBackgroundResId = a.getResourceId(R.styleable.CalendarPickerView_dayBackground,
                R.drawable.calendar_bg_selector);
        dayTextColorResId = a.getResourceId(R.styleable.CalendarPickerView_dayTextColor,
                R.color.calendar_text_selector);
        displayHeader = a.getBoolean(R.styleable.CalendarPickerView_displayHeader, false);
        headerTextColor = a.getColor(R.styleable.CalendarPickerView_headerTextColor,
                res.getColor(R.color.calendar_text_active));
        a.recycle();

    }


    public MonthContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addContentView(MonthView.Listener listener,
                               DateFormat weekDateFormat,
                               Calendar today) {
        monthView = MonthView.create(this, layoutInflater,
                weekDateFormat, listener, today,
                dividerColor,
                dayBackgroundResId,
                dayTextColorResId,
                displayHeader,
                headerTextColor);
        addView(monthView);
    }

    public void init(MonthDescriptor descriptor, List<List<MonthCellDescriptor>> lists) {
        monthView.init(descriptor, lists);
    }
}
