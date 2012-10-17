
/**
 * An Item in an adventure game.
 * 
 * @author Tanzeel Rana 
 * @version 2012.10.15
 */
public class Item
{
    
    private String itemDescription;
    private double itemWeight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String itemDescription, double itemWeight)
    {
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
    }
    
    public String getItemDescription(){
        return itemDescription ;
    }
    
    public double getItemWeight(){
        return itemWeight;
    }
    
}
