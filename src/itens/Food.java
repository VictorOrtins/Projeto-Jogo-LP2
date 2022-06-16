package src.itens;
public class Food extends Item{
    private double plusMaxWeight;
    private double plusHealth;

    public Food(String name, String description, double weight){
        super(name, description, weight);
        this.plusHealth = 0;
        this.plusMaxWeight = 0;
    }

    public Food(String name, String description, double weight, double plusHealth, double plusMaxWeight){
        super(name, description, weight);
        this.plusHealth = plusHealth;
        this.plusMaxWeight = plusMaxWeight;
    }

    public double getPlusMaxWeight() {
        return this.plusMaxWeight;
    }

    public void setPlusMaxWeight(int plusMaxWeight) {
        this.plusMaxWeight = plusMaxWeight;
    }

    public double getPlusHealth() {
        return this.plusHealth;
    }

    public void setPlusHealth(double plusHealth) {
        this.plusHealth = plusHealth;
    }

    public String showItem(){
        String retorno = "";


        retorno += this.getDescription() + " - " + this.getWeight() + "kg - ";
        retorno += this.plusMaxWeight + " plusMaxWeight ";
        retorno += this.plusHealth + " plusHealth\n";

        return retorno;
    }

    public String showItemRoom(){
        String retorno = "";


        retorno += this.getDescription() + " - " + this.getWeight() + "kg - ";
        retorno += this.plusMaxWeight + " plusMaxWeight ";
        retorno += this.plusHealth + " plusHealth\n";

        return retorno;
    }


}
