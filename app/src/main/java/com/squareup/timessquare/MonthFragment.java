package com.squareup.timessquare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.squareup.timessquare.sample.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class MonthFragment extends Fragment {
    List<List<List<MonthCellDescriptor>>> cells;
    List<MonthDescriptor> months;
    DateFormat weekdayNameFormat;
    MonthView.Listener listener;
    Calendar today;
    int position;

    public static MonthFragment create(List<MonthDescriptor> months,
                                       List<List<List<MonthCellDescriptor>>> cells
            , MonthView.Listener listener, DateFormat weekDateFormat
            , Calendar today, int position
    ) {
        MonthFragment monthFragment = new MonthFragment();
        monthFragment.cells = cells;
        monthFragment.months = months;
        monthFragment.weekdayNameFormat = weekDateFormat;
        monthFragment.today = today;
        monthFragment.listener = listener;
        monthFragment.position = position;
        return monthFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_calendar_fragment, null);
        MonthContentView monthContentView = (MonthContentView) rootView.findViewById(R.id.month_content_view);
        monthContentView.addContentView(listener, weekdayNameFormat, today);
        monthContentView.init(months.get(position), cells.get(position));
        return rootView;
    }
}
