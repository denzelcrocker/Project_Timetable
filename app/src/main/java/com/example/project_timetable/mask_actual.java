package com.example.project_timetable;

import android.os.Parcel;
import android.os.Parcelable;

public class mask_actual implements Parcelable{
    private int Id_lesson;
    private String Lesson;
    private String Classroom;
    private String Subgroup;
    private String Count;

    public mask_actual(int Id_lesson, String Lesson, String Classroom, String Subgroup, String Count) {
        this.Id_lesson = Id_lesson;
        this.Lesson = Lesson;
        this.Classroom = Classroom;
        this.Subgroup = Subgroup;
        this. Count = Count;


    }
    protected mask_actual(Parcel in) {
        Id_lesson = in.readInt();
        Lesson = in.readString();
        Classroom = in.readString();
        Subgroup = in.readString();
        Count = in.readString();
    }
    public static final Creator<mask_actual> CREATOR = new Creator<mask_actual>() {
        @Override
        public mask_actual createFromParcel(Parcel in) {
            return new mask_actual(in);
        }

        @Override
        public mask_actual[] newArray(int size) {
            return new mask_actual[size];
        }
    };
    public int getId_lesson() {
        return Id_lesson;
    }

    public void setId_lesson(int id_lesson) {
        Id_lesson = id_lesson;
    }

    public String getLesson() {
        return Lesson;
    }

    public void setLesson(String lesson) {
        Lesson = lesson;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String classroom) {
        Classroom = classroom;
    }

    public String getSubgroup() {
        return Subgroup;
    }

    public void setSubgroup(String subgroup) {
        Subgroup = subgroup;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id_lesson);
        parcel.writeString(Lesson);
        parcel.writeString(Classroom);
        parcel.writeString(Subgroup);
        parcel.writeString(Count);
    }
}
