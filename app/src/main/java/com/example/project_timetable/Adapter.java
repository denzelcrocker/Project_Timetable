package com.example.project_timetable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context mContext;
    List<Mask> maskList;
    public Adapter(Context mContext, List<Mask> listProduct) {
        this.mContext = mContext;
        this.maskList = listProduct;
    }
    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int i) {
        return maskList.get(i);
    }

    @Override
    public long getItemId(int i) {return maskList.get(i).getId_lesson(); }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = View.inflate(mContext,R.layout.activity_mask,null);
        TextView Id_lesson = v.findViewById(R.id.numberOfLesson);
        TextView Lesson = v.findViewById(R.id.lessonText);
        TextView Classroom = v.findViewById(R.id.classroomText);
        TextView Teacher = v.findViewById(R.id.teacherText);

        Mask mask = maskList.get(i);
        Id_lesson.setText(Integer.toString(mask.getId_lesson()).toString());
        Lesson.setText(mask.getLesson());
        Classroom.setText(mask.getClassroom());
        Teacher.setText(mask.getTeacher());
        return v;
    }
}
