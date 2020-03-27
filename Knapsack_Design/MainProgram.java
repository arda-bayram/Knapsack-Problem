import java.util.*;
import java.io.*;
import java.io.File;
import java.util.ArrayList;

enum Type {
    SINGLE,
    MULTIPLE
}

public class MainProgram {

    public static void main(String[] args) throws IOException {
        Knapsack knp = knapsackDesign("test1a.dat");
        Knapsack[] arr = multipleKnapsackDesign("test2d.dat");

        int totalValue = getTotalValueofKnapsacks(arr);
        System.out.println(totalValue);
    }

    public static void writeToFile(Knapsack knapsack, String fileName) throws IOException {
        Integer totalValue = knapsack.getTotalValue();
        Item[] items = composeItemArrayFromFile(fileName);
        int length = items.length;


        int[] valueArray = new int[length];

        for (Item knapsackItem : knapsack.getKnapsack())
            for (int i = 0; i < length; i++)
                if (knapsackItem.getItemNumber() == items[i].getItemNumber())
                    valueArray[i] = 1;

        BufferedWriter writer = new BufferedWriter(new FileWriter("output-" + fileName));

        writer.write(totalValue.toString() + "\n");
        for (int i = 0; i < length; i++)
            writer.append(valueArray[i] + "\n");

        writer.close();

    }

    public static Knapsack knapsackDesign(String filename) throws IOException {
        Item[] arr = composeItemArrayFromFile(filename);
        int[] info = new int[2];
        try {
            info = getKnapsackInfo(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Knapsack knapsack = new Knapsack(info[1], info[0]);
        System.out.println();
        knapsack.setFullness(0);
        int length = arr.length;
        Item[] copy = arr;
        int case0, case1, case2, case3;
        case0 = case1 = case2 = case3 = 0;

        QuickSort quick = new QuickSort();
        UtilityFunctions uti = new UtilityFunctions();

        //Case0
        knapsack.fillKnapsack(arr, 1);
        case0 = knapsack.getTotalValue();
        System.out.println("Case 0 = " + case0);
        knapsack.clearKnapsack(arr);
        //Case1
        quick.sort(arr, 0, length - 1, 1);
        knapsack.fillKnapsack(arr, 1);
        case1 = knapsack.getTotalValue();
        System.out.println("Case 1 = " + case1);
        knapsack.clearKnapsack(arr);
        //Case2
        quick.sort(arr, 0, length - 1, 2);
        knapsack.fillKnapsack(arr, 1);
        case2 = knapsack.getTotalValue();
        System.out.println("Case 2 = " + case2);
        knapsack.clearKnapsack(arr);

        //Case3
        quick.sort(arr, 0, length - 1, 3);
        knapsack.fillKnapsack(arr, 1);
        case3 = knapsack.getTotalValue();
        System.out.println("Case 3 = " + case3);
        knapsack.clearKnapsack(arr);

        int[] caseArr = {case0, case1, case2, case3};
        int t = uti.findMax(caseArr);
        System.out.println(t);
//
        if (t == 0) {
            knapsack.fillKnapsack(copy, 1);
            return knapsack;
        }
        if (t == 1) {
            quick.sort(arr, 0, length - 1, 1);
            knapsack.fillKnapsack(arr, 1);
            return knapsack;
        }
        if (t == 2) {
            quick.sort(arr, 0, length - 1, 2);
            knapsack.fillKnapsack(arr, 1);
            return knapsack;
        }
        if (t == 3) {
            quick.sort(arr, 0, length - 1, 3);
            knapsack.fillKnapsack(arr, 1);
            return knapsack;
        }
        return knapsack;
    }

    /* Compose Item array */
    public static Item[] composeItemArrayFromFile(String fileName) throws IOException {
        int[][] myData = getDataFromFile(fileName);
        Item arr[] = new Item[myData.length];
        for (int i = 0; i < myData.length; i++) {
            arr[i] = new Item(myData[i][0], myData[i][1], i, 0);
        }


        return arr;
    }

    /* Get all data from file as a 2D array */
    public static int[][] getDataFromFile(String fileName) {

        ArrayList<String[]> ip = new ArrayList<>();

        int[][] data = null;
        int cols = 0, rows = 0;

        try {
            File fileA = new File(fileName);
            BufferedReader bra = new BufferedReader(new FileReader(fileA));

            String line = "";
            String[] str;

            while ((line = bra.readLine()) != null) {
                str = line.trim().split(" ");
                ip.add(str);
                cols = str.length;
                rows++;
            }

            data = new int[rows][cols];

            for (int i = 0; i < ip.size(); i++) {
                String[] temp = ip.get(i);
                for (int j = 0; j < temp.length; j++) {
                    data[i][j] = Integer.parseInt(temp[j]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: File not found.");
        }

        return data;
    }


    /* Get max number of items to fill knapsack and total weight (W) */
    public static int[] getKnapsackInfo(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
        String[] line = sc.nextLine().trim().split(" ");

        int[] info = new int[2];
        info[0] = Integer.parseInt(line[0]);
        info[1] = Integer.parseInt(line[1]);

        return info;
    }


    public static Knapsack[] multipleKnapsackDesign(String filename) throws IOException {
        //Cases


        int case0, case1, case2, case3;
        case0 = case1 = case2 = case3 = 0;
        //Value to hold best case
        int totalValue = 0;
        //Sorting obj.
        QuickSort quick = new QuickSort();
        UtilityFunctions uti = new UtilityFunctions();
        //

        //Creating arrays and variables from main Array

        Item[] arr = composeItemArrayFromFile(filename);

        int knapsackCount = arr[0].getWeight();
        int itemCount = arr[0].getValue();
        Item[] itemArr = new Item[itemCount];
        Item[] copy = itemArr;
        Item[] xxx = new Item[itemCount];
        Knapsack[] knapsackArr = new Knapsack[knapsackCount];

        //Copying the items from main Array
        for (int i = 0; i < itemCount; i++) {
            itemArr[i] = arr[i + knapsackCount];
        }


        //Filling the knapsack array by using main Array formatted way.
        //Starting i from 1, coz 1 element of array is for item count and knapsack number
        for (int i = 1; i < knapsackCount; i++) {

            knapsackArr[i - 1] = new Knapsack(arr[i].getValue(), itemCount);
            if (i + 1 <= knapsackCount) {
                knapsackArr[i] = new Knapsack(arr[i].getWeight(), itemCount);
            }
//Setting Id's


        }
        //Case0
        for (int i = 0; i < itemCount; i++) {
            itemArr[i].resetItemInsıde();
        }
        for (int i = 0; i < knapsackCount; i++) {
            knapsackArr[i].fillKnapsack(itemArr, 0);
            totalValue += knapsackArr[i].getTotalValue();
        }
        prıntItems(itemArr);
        for (int i = 0; i < knapsackCount; i++) {
            knapsackArr[i].clearKnapsack(itemArr);
        }
        case0 = totalValue;

        //Case1
        for (int i = 0; i < itemCount; i++) {
            itemArr[i].resetItemInsıde();
        }
        totalValue = 0;
        quick.sort(itemArr, 0, itemCount - 1, 1);
        for (int i = 0; i < knapsackCount; i++) {

            knapsackArr[i].clearKnapsack(itemArr);
            knapsackArr[i].fillKnapsack(itemArr, 0);
            totalValue += knapsackArr[i].getTotalValue();
        }
        for (int i = 0; i < knapsackCount; i++) {
            knapsackArr[i].clearKnapsack(itemArr);
        }
        case1 = totalValue;

        //Case2
        for (int i = 0; i < itemCount; i++) {
            itemArr[i].resetItemInsıde();
        }
        totalValue = 0;
        quick.sort(itemArr, 0, itemCount - 1, 2);
        for (int i = 0; i < knapsackCount; i++) {
            knapsackArr[i].fillKnapsack(itemArr, 0);
            totalValue += knapsackArr[i].getTotalValue();
        }
        for (int i = 0; i < knapsackCount; i++) {
            knapsackArr[i].clearKnapsack(itemArr);
        }
        case2 = totalValue;


        //Case3
        for (int i = 0; i < itemCount; i++) {
            itemArr[i].resetItemInsıde();
        }
        totalValue = 0;
        quick.sort(itemArr, 0, itemCount - 1, 3);
        for (int i = 0; i < knapsackCount; i++) {
            knapsackArr[i].fillKnapsack(itemArr, 0);
            totalValue += knapsackArr[i].getTotalValue();
        }
        for (int i = 0; i < knapsackCount; i++) {
            knapsackArr[i].clearKnapsack(itemArr);
        }
        case3 = totalValue;
        int[] caseArr = {case0, case1, case2, case3};
        int t = uti.findMax(caseArr);


        System.out.println("Case 0 = " + case0 + " Case 1 = " + case1 + "Case 2 = " + case2 + " Case 3 = " + case3 + " T = " + t);
        for (int i = 0; i < itemCount; i++) {
            itemArr[i].resetItemInsıde();
        }

        if (t == 0) {
            totalValue = 0;
            for (int i = 0; i < knapsackCount; i++) {
                knapsackArr[i].fillKnapsack(itemArr, 0);
                totalValue += knapsackArr[i].getTotalValue();
            }
            return knapsackArr;
        }
        if (t == 1) {
            totalValue = 0;
            quick.sort(xxx, 0, itemCount - 1, 1);
            for (int i = 0; i < knapsackCount; i++) {
                knapsackArr[i].fillKnapsack(itemArr, 0);
                totalValue += knapsackArr[i].getTotalValue();
            }
            return knapsackArr;
        }
        if (t == 2) {
            totalValue = 0;
            quick.sort(xxx, 0, itemCount - 1, 2);
            for (int i = 0; i < knapsackCount; i++) {
                knapsackArr[i].fillKnapsack(itemArr, 0);
                totalValue += knapsackArr[i].getTotalValue();
            }
            return knapsackArr;
        }
        if (t == 3) {
            totalValue = 0;
            quick.sort(xxx, 0, itemCount - 1, 3);
            for (int i = 0; i < knapsackCount; i++) {
                knapsackArr[i].fillKnapsack(itemArr, 0);
                totalValue += knapsackArr[i].getTotalValue();
            }
            return knapsackArr;
        }
        return knapsackArr;
    }

    public static int getTotalValueofKnapsacks(Knapsack[] arr) {
        int length = arr.length;
        int totalValue = 0;
        for (int i = 0; i < length; i++) {
            totalValue += arr[i].getTotalValue();
        }

        return totalValue;
    }

    public static void prıntItems(Item[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            arr[i].printItemInside();
        }
    }
}
