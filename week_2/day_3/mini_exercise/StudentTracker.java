package week_2.day_3.mini_exercise;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentTracker {

    private final HashMap<String, ArrayList<Integer>> studentGrades;

    public StudentTracker() {
        this.studentGrades = new HashMap<>();
    }

    public void addGrade(String name, int grade) {
        studentGrades.putIfAbsent(name, new ArrayList<>());
        studentGrades.get(name).add(grade);
    }

    public double getAverage(String name) {
        ArrayList<Integer> grades = studentGrades.get(name);

        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public String topStudent() {
        String topStudent = "";
        double maxAverage = 0;

        for (String student : studentGrades.keySet()) {
            double average = getAverage(student);
            if (average > maxAverage) {
                maxAverage = average;
                topStudent = student;
            }
        }

        return topStudent;
    }

}
