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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> previousRooms = new Stack();
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room gallery,waitingroom, workshop, lobby, entrance, dinningroom,studio,theater, dressingroom,technician;
        Item plants;
        // create the rooms
        gallery = new Room("gallary");
        workshop = new Room("workshop");
        lobby = new Room("lobby");
        entrance = new Room("entrance");
        dinningroom = new Room("dinning room");
        studio = new Room("studio");
        theater = new Room("theater");
        dressingroom = new Room("dressing room");
        technician = new Room("technician room");
        waitingroom = new Room("waiting room");

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
        entrance.addItem(plants);

        currentRoom = entrance;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
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
        printLocationInfo();
    }

    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
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
        else if (commandWord.equals("back")){
            back();
        }
        else if (commandWord.equals("undo")){
            undo();
        }

        return wantToQuit;
    }

    private void undo(){
        previousRoom = currentRoom;
        if(!previousRooms.empty()){
            currentRoom = previousRooms.pop();
        }else{
            System.out.println("no more undo!");
        }
        printLocationInfo();
    }

    private void back(){
        Room tempRoom = previousRoom;
        previousRoom = currentRoom ;
        currentRoom = tempRoom;
        printLocationInfo();
    }

    private void eat(){
        System.out.println("you have eaten now and you are not hungry anymore ");
    }

    private void look(){
        System.out.println(currentRoom.getLongDescription());
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

        // Try to leave current room.
        previousRoom = currentRoom;
        previousRooms.push(previousRoom);
        Room nextRoom = currentRoom.getExits(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
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
