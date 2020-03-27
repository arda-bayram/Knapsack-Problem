import java.util.*;

public class Item {
    private int _no;
    private int _value;
    private int _weight;
    private int _insideNo;

    public Item(int value, int weight, int no, int inside) {
        this._value = value;
        this._weight = weight;
        this._no = no;
        this._insideNo = inside;
    }
    public int getInsideNo() {return this._insideNo;}
    public int getValue() {
        return this._value;
    }
    public int getWeight() {
        return this._weight;
    }
    public int getItemNumber() {
        return this._no;
    }
    public void setItemNumber(int no) {
        this._no = no;
    }
    public void setInsideNo(int no){this._insideNo = no;}
    public double getUnitValue(){return (double)_value/_weight; }
    public void resetItemInsıde() {this._insideNo = 0;}
    public void printItemInside() {System.out.println(_insideNo);}
}