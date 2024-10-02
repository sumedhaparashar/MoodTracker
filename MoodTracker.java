import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class M {
    private List<MoodEntry> moodHistory;

    // Inner class to hold mood, date, and reason for happiness
    private class MoodEntry {
        String mood;
        String date;
        String reason;

        MoodEntry(String mood, String date, String reason) {
            this.mood = mood;
            this.date = date;
            this.reason = reason;
        }
    }

    public M() {
        moodHistory = new ArrayList<>();
    }

    public void logMood(String mood) {
        // Get current date
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String reason = "";

        if (mood.equalsIgnoreCase("happy")) {
            // Prompt for reason if the mood is happy
            System.out.println("Enter a reason for your happiness:");
            Scanner scanner = new Scanner(System.in); // Use a new scanner for input
            reason = scanner.nextLine().trim();
        }

        moodHistory.add(new MoodEntry(mood, date, reason));
        System.out.println("Mood logged: " + mood + " on " + date);

        // Suggestion based on mood
        if (mood.equalsIgnoreCase("sad")) {
            System.out.println("Here's a quote for you: \"The only way to do great work is to love what you do.\" - Steve Jobs");
        }
    }

    public void viewMoodHistory() {
        System.out.println("\n--- Mood History ---");
        if (moodHistory.isEmpty()) {
            System.out.println("No moods logged yet.");
        } else {
            for (int i = 0; i < moodHistory.size(); i++) {
                MoodEntry entry = moodHistory.get(i);
                String output = (i + 1) + ": " + entry.mood + " on " + entry.date;
                // Include reason if mood is happy
                if (entry.mood.equalsIgnoreCase("happy") && !entry.reason.isEmpty()) {
                    output += " (Reason: " + entry.reason + ")";
                }
                System.out.println(output);
            }
        }
    }

    public void reviewMonth() {
        System.out.println("\n--- Monthly Mood Review ---");
        if (moodHistory.isEmpty()) {
            System.out.println("No moods logged yet.");
            return;
        }

        // Get today's date
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("Mood entries for the last month:");

        // Check moods in the last month
        for (MoodEntry entry : moodHistory) {
            if (entry.date.compareTo(today) >= 0) { // Basic date comparison
                System.out.println(entry.mood + " on " + entry.date);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        M tracker = new M();
        String command;

        System.out.println("Welcome to the Mood Tracker App!");

        do {
            System.out.println("\nEnter a command (log/view/review/exit):");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "log":
                    System.out.println("Enter your mood (e.g., Happy, Sad):");
                    String mood = scanner.nextLine().trim();
                    tracker.logMood(mood);
                    break;

                case "view":
                    tracker.viewMoodHistory();
                    break;

                case "review":
                    tracker.reviewMonth();
                    break;

                case "exit":
                    System.out.println("Exiting the Mood Tracker App. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid command. Please enter 'log', 'view', 'review', or 'exit'.");
            }
        } while (!command.equals("exit"));

        scanner.close();
    }
}
