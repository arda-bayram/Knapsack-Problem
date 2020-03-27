public class UtilityFunctions {


    //This function find maximum element in integer array and return its index
    int findMax(int[] arr){

        int temp = -1;

        for(int i = 0; i<arr.length; i++){

           if(arr[i]>temp) temp = arr[i];
        }

        return indexOf(arr, temp);
    }

    int indexOf(int[] arr, int numb) {

        for(int i= 0; i<arr.length; i++){
            if(arr[i] == numb) return i;
        }
        return -1;
    }

    //This function to print 2d integer arrays
   void print2dArr(int[][] arr){
        int l1 = arr.length;
        int l2 = arr[0].length;

        for(int i= 0; i<l1; i++){
            System.out.println();
            for(int j= 0; j<l2; j++){
                System.out.print(" "+arr[i][j]);
            }
        }
    }
// This function turns 2D array to 1D
    int [] turn2dto1(int[][] arr){
        int k = 0;
        int l1 = arr.length;
        int l2 = arr[0].length;
        int[] ret = new int[l1*l2];
        for(int i= 0; i<l1; i++){

            for(int j= 0; j<l2; j++){
           ret[k] = arr[i][j];
           k++;
            }
        }

        return ret;
    }
//Prints 1D Integer Array
    void printArr(int[]arr){
        int l1 = arr.length;
        for(int i= 0; i< l1; i++){
           System.out.print(" "+arr[i]);
        }
    }
//Takes ınteger array argument and get the Knapsack Capacities and Item Count
    Knapsack[] createKnapsacks(int[] arr){
        int l1= arr.length;
        int itemCount = arr[0];
        int knapSackCount = arr[1];

        Knapsack[] kp = new Knapsack[knapSackCount];

        for(int i= 0; i<knapSackCount; i++) {
            kp[i] = new Knapsack(arr[i+2], itemCount, i+1);
        }

        return  kp;
    }

}
