import javafx.scene.input.ScrollEvent;

public class Statistic {
    private int quantity;
    private String stat;

    public void setQuantity(int a) //update the quantity to a statistic
    {
        quantity = a;
    }

    public int getQuantity() //return the current quantity of a statistic
    {
        return quantity;
    }

    public void printStat() //prints the name of the statistic to the screen
    {
        System.out.println(stat);
    }

    public Health() // health stat
    {
        stat = Health;
        quantity = 100;
    }

    public Score() // total score
    {
        stat = Score;
        quantity = 100;
    }


}
