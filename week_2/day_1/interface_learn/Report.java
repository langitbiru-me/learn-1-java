package week_2.day_1.interface_learn;

// class can implement multiple interfaces
public class Report implements Printable, Saveable {

    private final String content;

    public Report(String content) {
        this.content = content;
    }

    @Override
    public void print() {
        System.out.println("printing content :" + content);
    }

    @Override
    public void save(String filename) {
        System.out.println("saving file " + filename);
    }

    public static void main(String[] args) {
        Report report = new Report("Hello, Java Interfaces!");
        report.print();
        report.save("test_report.txt");
    }
}
