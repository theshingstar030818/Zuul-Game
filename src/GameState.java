import java.util.*;
/**
 * Write a description of class GameState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameState
{
    private Player player;
    private HashMap<String,Room> map;

    /**
     * 
     */
    public GameState(Player player, HashMap<String,Room> map)
    {
        this.player = player;
        this.map = map;
    }

    public Player getPlayer(){
        return player;
    }
   
    public HashMap<String,Room> getMap(){
        return map;
    }
}
