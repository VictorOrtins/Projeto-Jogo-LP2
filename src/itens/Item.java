package src.itens;
import java.util.Objects;

/**
 *  * Classe Item - um item em um jogo de aventura.
 *
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * A classe item representa um item dentro das salas do jogo.
 * Cada item tem um nome, um peso e uma descrição, que são usados
 * no jogo.
 */

public class Item {

    private String description; //Descrição do item
    private double weight; //Peso do item
    private String name; //Nome do item

    /**
     * Construtor sem parâmetros da classe
     */
    public Item() {
        this.description = "Blue sword";
        this.weight = 2.2;
    }

    /**
     * Construtor da classe
     * @param description descrição do item
     * @param weight peso do item
     */

    public Item(String name, String description, double weight){
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * método assesor do atributo itemDescription
     * @return descrição do item
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Método modificador básico do atributo itemDescription
     * @param description descrição do item desejada
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Método assesor básico do atributo itemWeight
     * @return peso do item
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Método modificador básico do peso do item
     * @param weight peso do item
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * Método acessor básico do atributo nome
     * @return nome do item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método modificador básico do atributo nome
     * @param name nome desejado do item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método facilitador para mostrar os detalhes do item
     * @returnn string do item
     */
    public String showItem(){
        String retorno = "";

        retorno += this.description + " - " + this.weight + "kg \n";

        return retorno;
    }

    public String showItemRoom(){
        String retorno = "";
        //" The " +  descricaoDoItem + " weights " + items.get(i).getWeight() + "kg\n";
        retorno += "The " + description + " weights " + weight + "kg\n";
        
        return retorno;

    }


    


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(description, item.description) && weight == item.weight && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, weight, name);
    }


    /**
     * Método toString da classe Item
     */
    public String toString(){
        String retorno = "";

        retorno += "Nome: " + this.name + "\n";
        retorno += "Descrição: " + this.description + "\n";
        retorno += "Peso: " + this.weight + "\n";

        return retorno;
    }

    



}
