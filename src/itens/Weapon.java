package src.itens;
import java.util.Objects;
/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Esta classe é uma classe que estende de item e serve como arma,
 * tanto para os jogadores como para os monstros.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Weapon extends Item{
    private int damage;
    private int durability;
    private boolean usable;

    /**
     * Método construtor da classe Weapon
     */
    public Weapon(){
        this.damage = 20;
    }

    /**
     * Método construtor da classe Weapon
     * @param name nome da arma
     * @param description descrição da arma
     * @param weight peso da arma
     * @param damage dano da arma
     */
    public Weapon(String name, String description, double weight, int damage, int durability){
        super(name, description, weight);

        this.damage = damage;
        setDurability(durability);
        this.usable = true;
    }

    public int getDurability(){
        return this.durability;
    }

    public void setDurability(int durability){
        this.durability = durability;
    }

    public void setUsable(boolean usable){
        this.usable = usable;
    }

    public boolean isUsable(){
        if (durability <= 0){
            setUsable(false);
        }

        return usable;
    }

    /**
     * Método assesor do atributo damage
     * @return dano da arma
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Método modificador do atributo damage
     * @param damage dano da arma desejada
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Método facilitador que mostra as descrições do item weapon
     */
    public String showItem(){
        String retorno = "";


        retorno += this.getDescription() + " - " + this.getWeight() + "kg - ";
        retorno += this.damage + " damage ";
        retorno += this.durability + " durability\n";

        return retorno;
    }

    public String showItemRoom(){
        String retorno = "";


        retorno += this.getDescription() + " - " + this.getWeight() + "kg - ";
        retorno += this.damage + " damage \n";

        return retorno;
    }


    public void useWeapon(){
        this.durability--;
    }

    /**
     * Método toString da classe Weapon
     */
    @Override
    public String toString() {
        return super.toString() + "{" +
            " damage='" + getDamage() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Weapon)) {
            return false;
        }
        Weapon weapon = (Weapon) o;
        return damage == weapon.damage && durability == weapon.durability && usable == weapon.usable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(damage, durability, usable);
    }

}
