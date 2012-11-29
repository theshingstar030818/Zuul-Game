package model.object;

import java.io.Serializable;

/**
 * An Item in an adventure game.
 * 
 * @author Tanzeel Rana 
 * @version 2012.10.15
 */
public class Item implements Serializable
{
    
	private static final long serialVersionUID = -3301986314361182137L;
	private String itemName;
    private double itemWeight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String itemName, double itemWeight)
    {
        this.itemName = itemName;
        this.itemWeight = itemWeight;
    }
    
    public String getItemName(){
        return itemName ;
    }
    
    public double getItemWeight(){
        return itemWeight;
    }
    
}