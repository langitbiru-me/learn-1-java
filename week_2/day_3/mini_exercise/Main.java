package week_2.day_3.mini_exercise;

public class Main {

    public static void main(String[] args) {
        StudentTracker tracker = new StudentTracker();

        tracker.addGrade("Alice", 90);
        tracker.addGrade("Alice", 85);
        tracker.addGrade("Bob", 95);

        System.out.println("Alice's average: " + tracker.getAverage("Alice"));
        System.out.println("Bob's average: " + tracker.getAverage("Bob"));
        System.out.println("Top student: " + tracker.topStudent());
    }
}
