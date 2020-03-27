import java.util.*;


public class Knapsack {
    private int _capacity;
    private int _itemCount;
    private int _fullness = 0;
    private  int _knapsackID;
    private Item[] _itemArr;


    public ArrayList<Item> _knapsack;

    public Knapsack(int capacity, int itemCount, int ID) {
        this._knapsackID = ID;
        this._capacity = capacity;
        this._itemCount = itemCount;
        init();
    }
    public void set_itemArr(Item[] arr){
        _itemArr = arr;
    }

    private void init() {
        _knapsack = new ArrayList<Item>(_itemCount);
    }

    public void addItem(Item item) {
        if (checkCapacity(item)) {
            this._fullness += item.getWeight();
            _knapsack.add(item);
            item.setInsideNo(_knapsackID); }
    }

    public void removeItem(Item item) {
        _knapsack.remove(item);
        item.setInsideNo(0);
    }

    public int getCapacity() {
        return _capacity;
    }

    public int getTotalValue(){
        int totalValue = 0;
        for(int i = 0; i< _knapsack.size(); i++){
            totalValue+= _knapsack.get(i).getValue();
        }
        return totalValue;
    }
    public int getID() {return _knapsackID;}
    public Item[] get_itemArr(){
        return this._itemArr;
    }
    public void setCapacity(int capacity) {
        _capacity = capacity;
    }
    public void setFullness(int fullness) {
        _fullness = fullness;
    }

    //Check if there is enough space for new item in the knapsack
    public Boolean checkCapacity(Item item) {
        if((item.getWeight() < (this._capacity - this._fullness)))return true;

        return false;
    }


    public void fillKnapsack(Item arr[]){
        int i = 0;

       while((_capacity-_fullness) > 0 && i< arr.length) {
           if(arr[i].getInsideNo() == 0)
           {addItem(arr[i]);
           }
           i++;
       }

    }

    public void clearKnapsack(Item arr[]){
        int length = arr.length;
        for(int i = 0; i<length; i++){
            arr[i].setInsideNo(0);
            this.removeItem(arr[i]);
        }
        this.setFullness(0);
    }
    public List<Item> getKnapsack() {
        return _knapsack;
    }
}