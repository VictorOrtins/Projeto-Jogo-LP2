package src.characters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import src.game.Command;
import src.itens.Weapon;
import src.rooms.ExitRoom;
import src.rooms.Room;

/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Esta classe é uma extensão da classe de personagem
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Monster extends Character{
    private List<Weapon> weapons;
    private String description;

    /**
     * Método construtor da classe Monster
     */
    public Monster(){
        super(null, null);
        this.weapons = null;
    }

    /**
     * 
     * @param name nome do monstro
     * @param monsterDescription descrição do monstro
     * @param currentRoom room atual do monstro
     */
    public Monster(String name, String monsterDescription, Room currentRoom, double health){
        super(name, currentRoom);
        this.description = monsterDescription;
        weapons = new ArrayList<Weapon>();
        setHealth(health);
    }

    public void addWeapon(Weapon weapon){
        if (weapon !=null){
            if (weapon.getDamage() > 0){
                if (weapon.getDurability() > 0){
                    if (weapon.getWeight() > 0){
                        if(!weapons.contains(weapon)){
                            weapons.add(weapon);
                        }
                    }
                }
            }
        }
    }

        /**
     * Tenta ir em uma direção. Se houver uma saída, entra
     * na nova sala, caso contrário, imprime uma mensagem de erro.
     * @param command comando dado
     */
    @Override
    public boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // se não houver nenhuma segunda palavra,
            // não sabemos para onde ir
            return false;
        }

        String direction = command.getSecondWord();

        // Tenta sair da sala atual.
        Room nextRoom = getCurrentRoom().getExit(direction); 

        if (nextRoom == null) {
            return false;
        }
        else {
            this.setCurrentRoom(nextRoom);
            return false;
        }
    }

    
    public int attack(){
        Random randomizer = new Random();

        if (weapons != null & weapons.size() > 0){
            int weaponNumber = randomizer.nextInt(weapons.size());

            Weapon weapon = weapons.get(weaponNumber);
            if (weapon != null && weapon.getDurability() > 0){
                return weapon.getDamage();
            }
        }

        return -1;
    }
    
    public void setDead(){
        super.setDead();
        this.getCurrentRoom().getItems().addAll(weapons);    
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Método facilitador que retorna uma string com a descrição da saúde do personagem
     * @return
     */
    protected String characterHealth(){
        String retorno = "Monster health is: ";
        retorno += this.getHealth() + "\n";

        return retorno;
    }

        /**
     * É um método facilitador, ele retorna os itens e as informações do room num só método
     * @return String completa com informações
     */
    public String getFullInfo(){

        String retorno = "";

        /* 
        if (this.getCurrentRoom() instanceof ExitRoom){
            return retorno;
        }
        */

        if (this.isAlive()){
            retorno += "Look! There's a monster named " + this.getName() + "\n" + characterHealth();
            if(true){

            }
        }

        return retorno;
    }

    public List<Weapon> getWeapons(){
        return weapons;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Monster)) {
            return false;
        }
        Monster monster = (Monster) o;
        return Objects.equals(weapons, monster.weapons) && Objects.equals(description, monster.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weapons, description);
    }



}
