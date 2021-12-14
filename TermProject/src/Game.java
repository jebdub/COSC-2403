public class Game {
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room outside, theater, pub, lab, office, bathroom, library, bookstore;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("first floor in a lecture theater");
        pub = new Room("first floor in the campus pub");
        lab = new Room("first floor in a computing lab");
        office = new Room("second floor in the computing admin office");
        bathroom = new Room("second floor in the bathroom");
        library = new Room("second floor library");
        bookstore = new Room("second floor bookstore");


        // initialise room exits
        outside.addExit("east", theater);
        outside.addExit("south", pub);
        outside.addExit("up", bathroom);
        //outside.setExits(null, theater, lab, pub);
        outside.addItem(new Item("cookie", 0.5));
        outside.addItem(new Item("laptop", 4.5));
        theater.addExit("south", lab);
        theater.addExit("west", outside);
        theater.addExit("up", bathroom);
        //theater.setExits(null, null, null, outside);
        theater.addItem(new Item("iPhone 13 Pro", 0.4));
        pub.addExit("north", outside);
        //pub.setExits(null, outside, null, null);
        pub.addItem(new Item("gold", 2));
        lab.addExit("north", theater);
        lab.addExit("up", bookstore);
        //lab.setExits(outside, office, null, null);
        lab.addItem(new Item("coke", 0.3));
        lab.addItem(new Item("key", 0.1));
        office.addExit("south", library);
        office.addExit("down", outside);
        // office.setExits(null, null, null, lab);
        office.addItem(new Item("hammer", 0.5));
        office.addItem(new Item("arrow", 0.1));
        bathroom.addExit("south", bookstore);
        bathroom.addExit("down", theater);
        //bathroom.setExits();
        bathroom.addItem(new Item("bow", 6));
        bathroom.addItem(new Item("flashlight", 1));
        library.addExit("north", office);
        library.addExit("east", bookstore);
        //library.setExits();
        library.addItem(new Item("shield", 6));
        bookstore.addExit("north", bathroom);
        bookstore.addExit("west", library);
        //bookstore.setExits
        bookstore.addItem(new Item("wumpus", 10));

        currentRoom = outside;  // start game outside
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the Wumpus World");
        System.out.println("Wumpus World is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        for (String dir : currentRoom.getExits().keySet())
            System.out.print(dir + " ");
        /*
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
        System.out.println();

         */
        System.out.println();
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("grab")) {
            grab(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("grab look go quit help");
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom;

        nextRoom = currentRoom.getExit(direction);
/*
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }


        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");

          for (String dir: currentRoom.getExits().keySet())
              System.out.print(dir + " ");
*/
        if (nextRoom == null) {
            System.out.println("there is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println("you are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            for (String dir : currentRoom.getExits().keySet())
                System.out.print(dir + " ");
            System.out.println();
        }
    }

    private void grab(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("grab what?");
            return;
        }
        String itemToGrab = command.getSecondWord();

        //try to leave current room.
        if (currentRoom.hasItem(itemToGrab)) {
            currentRoom.removeItem(itemToGrab);
        } else {
            System.out.println("item not found");
        }
    }

    public void look() {
        System.out.println("items:\n" + currentRoom.getItems());
    }
        /*

            if(currentRoom.northExit != null) {
                System.out.print("north ");
            }
            if(currentRoom.eastExit != null) {
                System.out.print("east ");
            }
            if(currentRoom.southExit != null) {
                System.out.print("south ");
            }
            if(currentRoom.westExit != null) {
                System.out.print("west ");
            }
            System.out.println();
        }

         */

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}

