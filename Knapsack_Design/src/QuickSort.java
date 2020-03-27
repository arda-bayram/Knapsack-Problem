class QuickSort {
    /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right
       of pivot

       type 1 =  weight to ascending order
       type 2 =  value to descending order
       type 3 = unitValue to descending order

     */
    int partition(Item arr[], int low, int high, int type) {
        Item pivot = arr[high];
        int i = (low - 1); // index of smaller element


        if (type == 1) {
            for (int j = low; j < high; j++) {
                // If current element is smaller than or
                // equal to pivot
                if (arr[j].getWeight() <= pivot.getWeight()) {
                    i++;

                    // swap arr[i] and arr[j]
                    Item temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            // swap arr[i+1] and arr[high] (or pivot)
            Item temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            return i + 1;
        } else if (type == 2) {
            for (int j = low; j < high; j++) {
                // If current element is smaller than or
                // equal to pivot
                if (arr[j].getValue() >= pivot.getValue()) {
                    i++;
                    // swap arr[i] and arr[j]
                    Item temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            // swap arr[i+1] and arr[high] (or pivot)
            Item temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            return i + 1;

        } else if (type == 3) {

            for (int j = low; j < high; j++) {
                // If current element is smaller than or
                // equal to pivot

                if (arr[j].getUnitValue() >= pivot.getUnitValue()) {
                    i++;
                    // swap arr[i] and arr[j]
                    Item temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            // swap arr[i+1] and arr[high] (or pivot)
            Item temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            return i + 1;
        }

        return 15;
    }

    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
        void sort (Item arr[],int low, int high, int type){
            if (low < high) {
            /* pi is partitioning index, arr[pi] is  
              now at right place */
                int pi = partition(arr, low, high, type);

                // Recursively sort elements before
                // partition and after partition
                sort(arr, low, pi - 1, type);
                sort(arr, pi + 1, high, type);
            }
        }
    }
