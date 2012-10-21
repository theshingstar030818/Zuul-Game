import java.util.*;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 */

public class Game 
{
    private Parser parser;
    private Player player1;
    private HashMap<String,Room> rooms;
    private Stack<GameState> gameState;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        gameState = new Stack<GameState>();
        rooms = new HashMap<String,Room>();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room gallery,waitingroom, workshop, lobby, entrance, dinningroom,studio,theater, dressingroom,technician;
        
        Item plants;
        // create the rooms
        rooms.put("gallary",gallery = new Room("gallary"));
        rooms.put("workshop",workshop = new Room("workshop"));
        rooms.put("lobby",lobby = new Room("lobby"));
        rooms.put("entrance",entrance = new Room("entrance"));
        rooms.put("dinning room",dinningroom = new Room("dinning room"));
        rooms.put("studio",studio = new Room("studio"));
        rooms.put("theater",theater = new Room("theater"));
        rooms.put("dressing room",dressingroom = new Room("dressing room"));
        rooms.put("technician room",technician = new Room("technician room"));
        rooms.put("waiting room",waitingroom = new Room("waiting room"));

        //create the items
        plants = new Item("A Plant",5.0);

        // initialise room exits

        gallery.setExits("south",workshop);
        workshop.setExits("north",gallery);
        workshop.setExits("east",theater);
        workshop.setExits("south",dressingroom);
        dressingroom.setExits("north",workshop);
        dressingroom.setExits("east", technician);
        technician.setExits("west",dressingroom);
        technician.setExits("north",studio);
        studio.setExits("south",technician);
        studio.setExits("west",theater);
        studio.setExits("north",dinningroom);
        dinningroom.setExits("south", studio);
        dinningroom.setExits("west", lobby);
        lobby.setExits("east",dinningroom);
        lobby.setExits("south",theater);
        lobby.setExits("west",waitingroom);
        lobby.setExits("north",entrance);
        waitingroom.setExits("west",lobby);
        theater.setExits("north",lobby);
        theater.setExits("east",studio);
        theater.setExits("west",workshop);
        entrance.setExits("south",lobby);
        entrance.addItem("plant1",plants);
        
        
        player1 = new Player("tanzeel"," me ",1000);
        player1.setCurrentRoom(rooms.get("entrance"));  // start game outside

        
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     * @throws CloneNotSupportedException 
     */
    public void play() throws CloneNotSupportedException 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo(player1);
    }

    private void printLocationInfo(Player player){
        System.out.println(player.getCurrentPlayerRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     * @throws CloneNotSupportedException 
     */
    private boolean processCommand(Command command) throws CloneNotSupportedException 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")){
            look();
        }
        else if (commandWord.equals("eat")){
            eat();
        }
        else if (commandWord.equals("undo")){
            undo();
        }
        else if (commandWord.equals("pick")){
            pick(command);
        }
        else if (commandWord.equals("drop")){
            drop(command);
        }

        return wantToQuit;
    }

    private void undo(){
        if(!gameState.empty()){
            rooms = gameState.peek().getMap();
            gameState.pop();
             System.out.println(player1.getFullPlayerDescription());
        }else{
            System.out.println("no more undo!");
        }
        printLocationInfo(player1);
    }
    
    private void drop(Command command){
    /**  if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = 
        player1.drop(_item_)
    */
    }
   

    private void eat(){
        System.out.println("you have eaten now and you are not hungry anymore ");
    }

    private void look(){
        System.out.println(player1.getCurrentPlayerRoom().getLongDescription());
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    private void pick(Command command) throws CloneNotSupportedException{
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Pick what?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = player1.getCurrentPlayerRoom().getItem(itemName);

        // Try to pick up the item.
        
        if(player1.getCurrentPlayerRoom().containsItem(itemName)&&player1.pick(itemName,item)){
            Player playerX = player1.clone();
            HashMap<String,Room> map =(HashMap<String,Room>) rooms.clone();
            gameState.push(new GameState(player1,map));
            System.out.println(item.getItemDescription() + " has been picked by " + player1.getFullPlayerDescription());
            player1.getCurrentPlayerRoom().reomoveItem(itemName);
        }else{
            System.out.println("item could not be picked ");
        }

    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = player1.getCurrentPlayerRoom().getExits(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            
            
            // Try to leave current room.
            HashMap<String,Room> map = (HashMap<String,Room>)rooms.clone();
            gameState.push(new GameState(player1,map));
            player1.setPreviousRoom(player1.getCurrentPlayerRoom());
            player1.setCurrentRoom(nextRoom);
            printLocationInfo(player1);
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
