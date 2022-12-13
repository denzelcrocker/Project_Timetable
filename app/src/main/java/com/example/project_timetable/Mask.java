package com.example.project_timetable;

import android.os.Parcel;
import android.os.Parcelable;

public class Mask implements Parcelable{
    private int Id_lesson;
    private String Lesson;
    private String Classroom;
    private String Teacher;

    public Mask(int Id_lesson, String Lesson, String Classroom, String Teacher) {
        this.Id_lesson = Id_lesson;
        this.Lesson = Lesson;
        this.Classroom = Classroom;
        this.Teacher = Teacher;


    }
    protected Mask(Parcel in) {
        Id_lesson = in.readInt();
        Lesson = in.readString();
        Classroom = in.readString();
        Teacher = in.readString();
    }
    public static final Creator<Mask> CREATOR = new Creator<Mask>() {
        @Override
        public Mask createFromParcel(Parcel in) {
            return new Mask(in);
        }

        @Override
        public Mask[] newArray(int size) {
            return new Mask[size];
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

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
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
        parcel.writeString(Teacher);
    }
}
