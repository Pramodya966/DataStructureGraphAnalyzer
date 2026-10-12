import java.util.ArrayList;
import java.util.List;

/** Stores results of operations so they can be shown with "Display All Results". */
public class ResultsLog {
    private static final List<String> ENTRIES = new ArrayList<>();

    public static void add(String entry) { ENTRIES.add(entry); }

    public static void displayAll() {
        System.out.println("=============================================");
        System.out.println("ALL RECORDED RESULTS");
        System.out.println("=============================================");
        if (ENTRIES.isEmpty()) {
            System.out.println("No results recorded yet. Use the other menus first.");
        } else {
            for (int i = 0; i < ENTRIES.size(); i++) {
                System.out.printf("%3d. %s%n", i + 1, ENTRIES.get(i));
            }
        }
        System.out.println("=============================================");
    }
}
