// Copyright 2012 Square, Inc.
package com.squareup.timessquare;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.timessquare.sample.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MonthView extends LinearLayout {
    CalendarGridView grid;
    private Listener listener;
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");

    public static MonthView create(ViewGroup parent, LayoutInflater inflater,
                                   DateFormat weekdayNameFormat, Listener listener, Calendar today) {
        final MonthView view = (MonthView) inflater.inflate(R.layout.month, parent, false);
        view.setDividerColor(R.color.transparent);
        view.setDayTextColor(R.color.custom_calendar_text_selector);
//        view.setTitleTextColor(R.color.custom_calendar_text_selector);
        view.setDisplayHeader(false);
        view.setDayBackground(R.drawable.custom_calendar_bg_selector);

        final int originalDayOfWeek = today.get(Calendar.DAY_OF_WEEK);

        int firstDayOfWeek = today.getFirstDayOfWeek();
        final CalendarRowView headerRow = (CalendarRowView) view.grid.getChildAt(0);
        for (int offset = 0; offset < 7; offset++) {
            today.set(Calendar.DAY_OF_WEEK, firstDayOfWeek + offset);
            final TextView textView = (TextView) headerRow.getChildAt(offset);
            textView.setText(weekdayNameFormat.format(today.getTime()));
        }
        today.set(Calendar.DAY_OF_WEEK, originalDayOfWeek);
        view.listener = listener;
        return view;
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        grid = (CalendarGridView) findViewById(R.id.calendar_grid);
    }

    public void init(MonthDescriptor month, List<List<MonthCellDescriptor>> cells) {
        Logr.d("Initializing MonthView (%d) for %s", System.identityHashCode(this), month);
        long start = System.currentTimeMillis();
        final int numRows = cells.size();
        grid.setNumRows(numRows);
        for (int i = 0; i < 6; i++) {
            CalendarRowView weekRow = (CalendarRowView) grid.getChildAt(i + 1);
            weekRow.setListener(listener);
            if (i < numRows) {
                weekRow.setVisibility(VISIBLE);
                List<MonthCellDescriptor> week = cells.get(i);
                for (int c = 0; c < week.size(); c++) {
                    MonthCellDescriptor cell = week.get(c);
                    CalendarCellView cellView = (CalendarCellView) weekRow.getChildAt(c);

                    String cellDate = Integer.toString(cell.getValue());
                    if (!cellView.getText().equals(cellDate)) {
                        //如果是当月的第一天显示月份
                        if (cellDate.equals("1")) {
                            cellView.setText(monthFormat.format(cell.getDate()));
                        } else {
                            cellView.setText(cellDate);
                        }
                    }
                    cellView.setEnabled(cell.isCurrentMonth());
                    cellView.setSelectable(cell.isSelectable());
                    cellView.setSelected(cell.isSelected());
                    cellView.setCurrentMonth(cell.isCurrentMonth());
                    cellView.setToday(cell.isToday());
                    cellView.setRangeState(cell.getRangeState());
                    cellView.setHighlighted(cell.isHighlighted());
                    cellView.setTag(cell);
                }
            } else {
                weekRow.setVisibility(GONE);
            }
        }

        Logr.d("MonthView.init took %d ms", System.currentTimeMillis() - start);
    }

    public void setDividerColor(int color) {
//        grid.setDividerColor(color);
        grid.setDividerColor(getResources().getColor(R.color.transparent));
    }

    public void setDayBackground(int resId) {
        grid.setDayBackground(resId);
    }

    public void setDayTextColor(int resId) {
        grid.setDayTextColor(R.color.custom_calendar_text_selector);
    }

    public void setDisplayHeader(boolean displayHeader) {
        grid.setDisplayHeader(displayHeader);
    }

    public void setHeaderTextColor(int color) {
        grid.setHeaderTextColor(color);
    }

    public interface Listener {
        void handleClick(MonthCellDescriptor cell);
    }
}
