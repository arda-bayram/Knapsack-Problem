import java.awt.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello User, I'm optimal Knapsack Filler 1");
        System.out.println("I'll direct you to solve your problems. ");

        Thread.sleep(2000);
        System.out.println("Please enter the filename, but before enter it i need you to remind some rules");
        Thread.sleep(2000);
        System.out.println("\n\nPlease enter the filename, but before enter it i need you to remind some rules");
        Thread.sleep(1000);
        System.out.println("\n\nPlease enter the filename, but before enter it i need you to remind some rules");
        Thread.sleep(1000);
        System.out.println("\n Sorry sometimes i get mindConfusion");
        Thread.sleep(2000);
        System.out.println("First rule, there is no Knapsack Filler");
        Thread.sleep(2000);
        System.out.println("Second rule, there is no Knapsack Filler");
        Thread.sleep(2000);
        System.out.println("Third rule there is no Knapsack Filler");
        Thread.sleep(2000);
        System.out.println("Also file format should contains only 2 columns, don't worry i handle all informations");
        System.out.println("If is it multiple knapsack problem enter 1, if singular enter 0");
        Thread.sleep(2000);
        int singOrMult = sc.nextInt();
        Thread.sleep(2000);
        System.out.println("Now enter the filename, and let the show begin");

        String filename = sc.next();

if(singOrMult == 0 ){
    Knapsack knp = knapsackDesign(filename);
    System.out.println("Optimal Value is "+ knp.getTotalValue());
}

if(singOrMult == 1){
    Knapsack[] knp = multipleKnapsackDesign(filename);
    System.out.println("Optimal Value = " + getTotalValue(knp, knp[0].get_itemArr()));

    Item[] itemArr = knp[0].get_itemArr();
    printFormatted(itemArr);

}

    }

    public static Knapsack[] multipleKnapsackDesign(String filename) throws IOException {

        QuickSort quick = new QuickSort();
        UtilityFunctions uti = new UtilityFunctions();


        //Cases

        int case0,case1,case2,case3;
        case0 = case1 = case2 = case3 = 0;



        //Creating and initialazing Knapsacks and Item array
        int[][]arr = getDataFromFile(filename);
        int[] ar = uti.turn2dto1(arr);
        Knapsack[] knp =  uti.createKnapsacks(ar);
        Item[] itemArr = composeItemArrayFromFile(filename);
        Item[] copy = itemArr;


        //You can reach the itemArr inside first knapsack
        knp[0].set_itemArr(itemArr);


        //Case 0

        fillKnapsacks(knp,itemArr);
        case0 = getTotalValue(knp,itemArr);
        clearKnapsacks(knp,itemArr);


        //Case 1

        quick.sort(itemArr,0,itemArr.length-1, 1);
        fillKnapsacks(knp,itemArr);
        case1 = getTotalValue(knp,itemArr);
        clearKnapsacks(knp,itemArr);

        //Case 2

        quick.sort(itemArr,0,itemArr.length-1, 2);
        fillKnapsacks(knp,itemArr);
        case2 = getTotalValue(knp,itemArr);
        clearKnapsacks(knp,itemArr);

        //Case 3

        quick.sort(itemArr,0,itemArr.length-1, 3);
        fillKnapsacks(knp,itemArr);
        case3 = getTotalValue(knp,itemArr);
        clearKnapsacks(knp,itemArr);


        int[] caseArr = {case0,case1,case2,case3};
        int index = uti.findMax(caseArr);
        System.out.println(case0 + " " + case1 + " " + case2 + " " + case3 + " ");


        if (index == 0) {
            knp[0].set_itemArr(copy);
            fillKnapsacks(knp,copy);
            case0 = getTotalValue(knp,copy);

            return  knp;
        }
        if (index == 1) {
            quick.sort(itemArr,0,itemArr.length-1, 1);
            fillKnapsacks(knp,itemArr);
            case1 = getTotalValue(knp,itemArr);
            return  knp;
        }
        if (index == 2) {
            quick.sort(itemArr,0,itemArr.length-1, 2);
            fillKnapsacks(knp,itemArr);
            case2 = getTotalValue(knp,itemArr);

            return  knp;
        }
        if (index == 3) {
            quick.sort(itemArr,0,itemArr.length-1, 3);
            fillKnapsacks(knp,itemArr);
            case3 = getTotalValue(knp,itemArr);
            return  knp;
        }




        return knp;
    }

    public static Knapsack knapsackDesign(String filename) throws IOException {
        //Classes and functions
        UtilityFunctions uti = new UtilityFunctions();
        QuickSort quick = new QuickSort();

        //Cases
        int case0,case1,case2,case3;
        case0 = case1 = case2 = case3 = 0;

        //Creating ıtem Arr, and Knapsack
        int[][]arr = getDataFromFile(filename);
        int itemCount = arr[0][0];
        int Capacity = arr[0][1];
        Item [] itemArr = new Item[itemCount];
        Item [] copy = new Item[itemCount];
        for(int i = 1; i<itemCount+1; i++){
            itemArr[i-1] = new Item(arr[i][0], arr[i][1], i-1, 0);
        }
        Knapsack knp = new Knapsack(Capacity,itemCount,1);
        copy = itemArr;



        //Case 0
        knp.set_itemArr(copy);
        knp.fillKnapsack(itemArr);
        case0 = knp.getTotalValue();
        knp.clearKnapsack(itemArr);

        System.out.println("Case 0 = "+ case0);

        //Case 1

        quick.sort(itemArr, 0,itemCount-1,1);
        knp.fillKnapsack(itemArr);
        case1 = knp.getTotalValue();
        knp.clearKnapsack(itemArr);

        System.out.println("Case 1 = "+ case1);

        //Case2
        quick.sort(itemArr, 0,itemCount-1,2);
        knp.fillKnapsack(itemArr);
        case2 = knp.getTotalValue();
        knp.clearKnapsack(itemArr);
        System.out.println("Case 2 = "+ case2);


        //Case3
        quick.sort(itemArr, 0,itemCount-1,3);
        knp.fillKnapsack(itemArr);
        case3 = knp.getTotalValue();
        knp.clearKnapsack(itemArr);
        System.out.println("Case 3 = "+ case3);

        int [] caseArr = {case0, case1, case2, case3};
        int index = uti.findMax(caseArr);


        if (index == 0) {
            knp.fillKnapsack(itemArr);
            return knp;
        }
        if (index == 1) {
            quick.sort(itemArr, 0, itemCount - 1, 1);
            knp.fillKnapsack(itemArr);
            return knp;
        }
        if (index == 2) {
            quick.sort(itemArr, 0, itemCount - 1, 2);
            knp.fillKnapsack(itemArr);
            return knp;
        }
        if (index == 3) {
            quick.sort(itemArr, 0, itemCount - 1, 3);
            knp.fillKnapsack(itemArr);
            return knp;
        }

        return knp;
    }

//This method turn the file in to 2D array, file format should include maximum 2 columns
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

    //Takes filename, and compose Item Array
    public static Item[] composeItemArrayFromFile(String fileName) throws IOException {
        int[][] myData = getDataFromFile(fileName);
        int itemCount = myData[0][0];
        int l1 = myData.length;
        //Starting the array after knapsacks
        int startIndex = l1-itemCount;
        Item arr[] = new Item[itemCount];
        for (int i = 0; i < itemCount; i++) {
            arr[i] = new Item(myData[i+startIndex][0], myData[i+startIndex][1], i+1, 0);
        }


        return arr;
    }
//This method takes knapsack Array, and fill the knapsacks -inside it-
    //Its checks if the item free, if the space is enough in knapsack, its also signs item after add
  public static void fillKnapsacks(Knapsack[] arr, Item[] itemArr){
        int l1= arr.length;
        for(int i=0; i<l1; i++){
            arr[i].fillKnapsack(itemArr);
        }
    }

    //This method resets knapsacks, also the item array
    //It resets knapsacks fullness, also resest items insideNo
    public static void clearKnapsacks(Knapsack[] arr, Item[] itemArr){
        int l1= arr.length;
        for(int i=0; i<l1; i++){
            arr[i].clearKnapsack(itemArr);
        }
    }


    //This function takes Knapsack Array and calculate its total value
    public static int getTotalValue(Knapsack[] arr, Item[] itemArr){
        int l1= arr.length;
        int totalValue = 0;
        for(int i=0; i<l1; i++){
           totalValue+= arr[i].getTotalValue();
        }
        return totalValue;
    }

    public static void printInsideNo(Item[] arr){
        int l = arr.length;

        for(int i= 0; i<l; i++){
            System.out.println(arr[i].getInsideNo());
        }
    }

// This function takes the insideNo (1-6) and turns corresponding num to 1 from 0
    public static void printFormatted(Item[] arr){
        int l= arr.length;

        for(int i= 0; i<l; i++){
            System.out.print(arr[i].getItemNumber()+" ");
            numberFormatter(arr[i].getItemNumber());
            int p = arr[i].getInsideNo();
            switch (p) {
                case 0: System.out.println("0 0 0 0 0 0");
                break;
                case 1: System.out.println("1 0 0 0 0 0");
                break;
                case 2: System.out.println("0 1 0 0 0 0");
                    break;
                case 3: System.out.println("0 0 1 0 0 0");
                    break;
                case 4: System.out.println("0 0 0 1 0 0");
                    break;
                case 5: System.out.println("0 0 0 0 1 0");
                    break;
                case 6: System.out.println("0 0 0 0 0 1");
                    break;
                    default: System.out.println("* * * * * * * * *");
            }
        }
    }

    // This function adds some spaces to format columns in table
    public static void numberFormatter(int x){

        if(x<10){
            System.out.print("    ");
        }
        else if(x<100){
            System.out.print("   ");
        }
        else if(x<1000){
            System.out.print("  ");
        }
        else if(x<10000){
            System.out.print(" ");
        }
        else if(x<100000){
            System.out.print("");
        }
    }

}
