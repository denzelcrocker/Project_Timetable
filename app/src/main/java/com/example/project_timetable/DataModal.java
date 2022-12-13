package com.example.project_timetable;

public class DataModal {
    private String Lesson;
    private String Classroom;
    private String Teacher;

    public DataModal(String Lesson, String Classroom,  String Teacher) {
        this.Lesson = Lesson;
        this.Classroom = Classroom;
        this.Teacher = Teacher;
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

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        this.Teacher = teacher;
    }
}