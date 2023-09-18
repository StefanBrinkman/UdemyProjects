package com.example.fragmenttestapp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.fragmenttestapp2.data.Course;
import com.example.fragmenttestapp2.data.CourseArrayAdapter;
import com.example.fragmenttestapp2.data.CourseData;

import java.util.List;

public class MainFragment extends ListFragment {
    List<Course> courses = new CourseData().courseList();
    public MainFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CourseArrayAdapter adapter = new CourseArrayAdapter(getActivity(), R.layout.course_listitem, courses);
        setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}

// Add Drawables for courses list