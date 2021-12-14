import java.util.ArrayList;
import java.util.HashMap;
public class Room {
    public String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items = new ArrayList<Item>();
    /*
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    public Room upExit;
    public Room downExit;
*/

    public Room(String description) {
        exits = new HashMap<String, Room>();
        this.description = description;
    }

    public HashMap<String, Room> getExits() {
        return exits;
    }

    public void setExits(HashMap<String, Room> exits) {
        this.exits = exits;
    }
/*
    public void setExits(Room north, Room east, Room south, Room west, Room up, Room down)
    {
        if(north != null) {
            northExit = north;
        }
        if(east != null) {
            eastExit = east;
        }
        if(south != null) {
            southExit = south;
        }
        if(west != null) {
            westExit = west;
        }
        if (up != null) {
            upExit = up;
        }
        if (down != null){
            downExit = down;
        } */

    public void addExit(String dir, Room room) {
        exits.put(dir, room);
    }

    public void removeExit(String dir) {
        exits.remove(dir);
    }

    public Room getExit(String dir) {
        return exits.get(dir);
    }

    public String getDescription() {
        return description;
    }

    public String getItems() {
        String str = "";
        if (items.size() > 0)
            for (int i = 0; 1 < items.size(); i++)
                str += items.get(i).toString() + "\n";
        else
            str = "no item in this room";
        return str;
    }

    public boolean hasItem(String str) {
        for (Item s : items) {
            if (str.equalsIgnoreCase(s.getName()))
                return true;
        }
        return false;
    }

    public void removeItem(String str) {
        int index;
        for (index = 0; index < items.size(); index++)
            if (items.get(index).getName().equalsIgnoreCase(str))
                break;
        items.remove(index);
    }

    public void addItem(Item thing) {
        items.add(thing);
    }
}
