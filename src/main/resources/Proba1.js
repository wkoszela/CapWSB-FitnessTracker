import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Activity {
    private String type;
    private double distance; // in kilometers
    private int duration; // in minutes
    private int calories;
    private Date date;

    public Activity(String type, double distance, int duration, int calories, Date date) {
    this.type = type;
    this.distance = distance;
    this.duration = duration;
    this.calories = calories;
    this.date = date;
}

public String getType() {
    return type;
}

public double getDistance() {
    return distance;
}

public int getDuration() {
    return duration;
}

public int getCalories() {
    return calories;
}

public Date getDate() {
    return date;
}

@Override
public String toString() {
    return "Activity{" +
        "type='" + type + '\'' +
        ", distance=" + distance +
        ", duration=" + duration +
        ", calories=" + calories +
        ", date=" + date +
        '}';
}
}

class FitnessTracker {
    private ArrayList<Activity> activities;

    public FitnessTracker() {
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
    activities.add(activity);
    System.out.println("Activity added!");
}

public void viewAllActivities() {
    System.out.println("All Activities:");
    for (Activity activity : activities) {
        System.out.println(activity);
    }
}

public void viewStatistics() {
    double totalDistance = 0;
    int totalDuration = 0;
    int totalCalories = 0;

    for (Activity activity : activities) {
        totalDistance += activity.getDistance();
        totalDuration += activity.getDuration();
        totalCalories += activity.getCalories();
    }

    System.out.println("Total Distance: " + totalDistance + " km");
    System.out.println("Total Duration: " + totalDuration + " minutes");
    System.out.println("Total Calories Burned: " + totalCalories + " calories");
}
}

public class Main {
    public static void main(String[] args) {
    FitnessTracker tracker = new FitnessTracker();
    Scanner scanner = new Scanner(System.in);

    while (true) {
    System.out.println("Fitness Tracker");
    System.out.println("1. Add Activity");
    System.out.println("2. View All Activities");
    System.out.println("3. View Statistics");
    System.out.println("4. Exit");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (choice == 1) {
    System.out.print("Enter activity type (e.g., Running, Cycling): ");
    String type = scanner.nextLine();

    System.out.print("Enter distance (km): ");
    double distance = scanner.nextDouble();

    System.out.print("Enter duration (minutes): ");
    int duration = scanner.nextInt();

    System.out.print("Enter calories burned: ");
    int calories = scanner.nextInt();

    Activity activity = new Activity(type, distance, duration, calories, new Date());
    tracker.addActivity(activity);

} else if (choice == 2) {
    tracker.viewAllActivities();

} else if (choice == 3) {
    tracker.viewStatistics();

} else if (choice == 4) {
    System.out.println("Exiting...");
    break;

} else {
    System.out.println("Invalid choice. Please try again.");
}
}
}
}
