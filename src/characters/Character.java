package src.characters;
import java.util.Objects;

import src.exceptions.SecondWordException;
import src.exceptions.ThirdWordException;
import src.game.Command;
import src.rooms.Room;

/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Essa classe é uma classe abstrata personagem.
 * Ela mantém todas as caracterísitcas principais para um personagem do jogo
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public abstract class Character {
    private Room currentRoom;
    private String name;
    private double health;
    private boolean alive;

    /**
     * Construtor da classe Character
     * @param name nome do personagem
     */
    public Character(String name){
        this.name = name;
        System.out.println();
        currentRoom = null;
        this.health = 100;
        this.alive = true;
    }

    /**
     * Construtor da classe Character
     * @param name nome do personagem
     * @param currentRoom room atual do personagem
     */
    public Character(String name, Room currentRoom){
        this.name = name;
        this.currentRoom = currentRoom;
        this.health = 100;
        this.alive = true;
    }

    public abstract boolean goRoom(Command command) throws ThirdWordException, SecondWordException;

    protected abstract String characterHealth();

    /**
     * Método acessor básico do atributo name
     * @return nome do character
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método modificador básico do atributo nome
     * @param name nome desejado ao character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método acessor básico do atributo currentRoom
     * @return sala atual do character
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Método modificador básico do atributo currentRoom
     * @param name sala atual desejada ao character
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }


    /**
     *  Pega a informação da sala em que o jogador está
     * @return descrição da sala
     */
    public String getLocationInfo(){
        return this.getCurrentRoom().getLongDescription();
    }


    /**
     * É um método facilitador, ele retorna os itens e as informações do room num só método
     * @return String completa com informações
     */
    public String getFullInfo(){
        return this.characterHealth() + this.getLocationInfo();
    }

    public double getHealth(){
        return this.health;
    }

    public void setHealth(double health){
        if (health < 0){
            this.health = 0;
            return;
        }
        this.health = health;      
    }

    public boolean isAlive(){
        if (this.health <= 0 && alive){
            setDead();
            System.out.println(this.getName() + " is dead");
        }

        return this.alive;
    }

    public void setDead(){
        this.alive = false;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Character)) {
            return false;
        }
        Character character = (Character) o;
        return Objects.equals(currentRoom, character.currentRoom) && Objects.equals(name, character.name) && health == character.health && alive == character.alive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentRoom, name, health, alive);
    }





}
