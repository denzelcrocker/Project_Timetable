package com.example.project_timetable;

public class DataModal_Actual {
    private String Lesson;
    private String Classroom;
    private String Subgroup;
    private String Count;

    public DataModal_Actual(String Lesson, String Classroom,  String Subgroup,  String Count) {
        this.Lesson = Lesson;
        this.Classroom = Classroom;
        this.Subgroup = Subgroup;
        this.Count = Count;
    }
    public String getLesson() {
        return Lesson;
    }

    public void setLesson(String lesson) {
        this.Lesson = lesson;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String classroom) {
        this.Classroom = classroom;
    }

    public String getSubgroup() {
        return Subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.Subgroup = subgroup;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        this.Count = count;
    }
}
