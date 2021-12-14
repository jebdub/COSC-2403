public class Item {
    private String name;
    private double weight;

    public Item( String name, double weight){
        this.name = name;
        this.weight = weight;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getWeight(){
        return weight;
    }

    public void setWeight(double w){
        weight = w;
    }

    @Override
    public String toString(){
        return String.format("Name: %s, Weight: %.1f lb",name,weight);
    }

}
