package Items;

/**
 * Created by user on 16/05/2016.
 */
public class Counter {
    private int counter;

    public Counter {
        int counter=this.counter;
    }

    // add number to current count.
    public void increase(int number){
        this.counter+= number;
    }
    // subtract number from current count.
    public void decrease(int number){
        this.counter-= number;
    }
    // get current count.
    public int getValue(){
        return this.counter;
    }
}
