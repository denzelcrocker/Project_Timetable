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

public class Adapder_actual extends BaseAdapter {
    private Context mContext;
    List<mask_actual> maskList;
    public Adapder_actual(Context mContext, List<mask_actual> listProduct) {
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
        View v = View.inflate(mContext,R.layout.activity_mask_actual,null);
        TextView Lesson = v.findViewById(R.id.lessonText);
        TextView Classroom = v.findViewById(R.id.classroomText);
        TextView Subgroup = v.findViewById(R.id.subgroupText);
        TextView Count = v.findViewById(R.id.countText);

        mask_actual mask = maskList.get(i);
        Lesson.setText(mask.getLesson());
        Classroom.setText(mask.getClassroom());
        Subgroup.setText(mask.getSubgroup());
        Count.setText(mask.getCount());
        return v;
    }
}
