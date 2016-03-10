import java.util.Scanner;

/*
 * This Class is responsible for the ranking system in the minesweeper project
 */
public class Ranking {
    private final int MAX_PEOPLE_LIMIT = 5;
    private String[] name;
    private int[] record;
    private int last;

    Ranking() {
        name = new String[MAX_PEOPLE_LIMIT];
        record = new int[MAX_PEOPLE_LIMIT];
        last = 0;
    }

    /*
     * This method handles the logic that involves the ranking system
     * removed unnecessary return; statement
     */
    public void recordName(int result) {
        System.out.print("\n Please enter your name -");
        Scanner in = new Scanner(System.in);
        String newName = in.nextLine();

        if ((last == MAX_PEOPLE_LIMIT) && (record[MAX_PEOPLE_LIMIT - 1] > result)) {
            System.out.println("\nSorry you cannot enter top " + (MAX_PEOPLE_LIMIT) + " players");
        } else if (last == MAX_PEOPLE_LIMIT) {
            name[MAX_PEOPLE_LIMIT - 1] = newName;
            record[MAX_PEOPLE_LIMIT - 1] = result;
        } else {
            name[last] = newName;
            record[last] = result;
            last++;
        }

        sort();
        show();
    }

    /*
     * This method prints out the name and the record to terminal
     * removed unnecessary return; statement
     */
    public void show() {
        if (last == 0) {
            System.out.println("Still no results");
        }
        
        System.out.println("N Name\t\tresult");
        for (int i = 0; i < last; i++) {
            System.out.println((i + 1) + " " + name[i] + "\t" + record[i]);
        }
    }

    /*
     * Implementation of bubble sort. The record have a limit of 5 people
     * so bubble sort will work just fine. if the record where to have unlimited amounts of people
     * in the future one could argue in changing the sorting algorithm.
     */
    private void sort() {
        if (last < 2) {
            return;
        }

        boolean unsorted = true;
        
        while (unsorted) {
            unsorted = false;
            
            //removed unnecessary nesting
            for (int i = 0; i < (last - 1); i++) {
                if (record[i + 1] > record[i]) {
                    int swapR = record[i];
                    record[i] = record[i + 1];
                    record[i + 1] = swapR;
                    String swapN = name[i];
                    name[i] = name[i + 1];
                    name[i + 1] = swapN;
                    unsorted = true;
                }
            }
        }
    }
}
