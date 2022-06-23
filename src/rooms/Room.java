package src.rooms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import src.characters.Monster;
import src.itens.Item;



/**
 * Classe Room - uma sala em um jogo de aventura.
 *
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 *
 * Uma sala (Room) representa uma localização no cenário do jogo. Ela está
 * conectada a outras salas por saídas. As saídas são referenciadas por
 * Norte, Sul, Leste e Oeste. Para cada direção, a sala armazena uma referência
 * (variável) a uma sala vizinha, ou null se não houver saída nessa direção.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room 
{
   private String name; //Nome da sala
   private String description; // Descrição (consulte o construtor)
   private Map<String, Room> exits; //Saídas do jogo
   private List<Item> items;
   private Monster monster;

    /**
     * Cria uma sala definida com o nome "description". Inicialmente,
     * ela não tem nenhuma saída. "description" é algo como "uma
     * cozinha" ou "um pátio".
     * @param description A descrição da sala
     */
    public Room(String description, String name) 
    {
        this.description = description;
        this.name = name;
        exits = new HashMap<String, Room>();
        if (description.contains("exit")){
            setExitItem();
        }
        else{
            items = new ArrayList<Item>();
        }

        this.monster = null;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    
    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public Monster getMonster(){
        return this.monster;
    }

    /**
     * Define o item da sala saída
     */
    private void setExitItem(){
        items = null;
    }

    /**
     * Adiciona um item a sala, informando a descrição e o peso do item
     * @param itemDescription descrição do item
     * @param itemWeight peso do item
     */
    public boolean addItem(String name, String itemDescription, double itemWeight){
        Item item;
        item = new Item(name, itemDescription, itemWeight);

        if (items.contains(item)){
            return false;
        }
        else{
            items.add(item);
            return true;
        }
        
    }


    /**
     * Adiciona um item a sala, já tendo o item pronto
     * @param item item da classe Item desejado a ser colocado na sala
     */
    public boolean addItem(Item item){
        if (!items.contains(item)){
            items.add(item);
            return true;
        }
        else{
            return false;
        }
    }

    
    /**
     * 
     * @param direction direção da sala
     * @param room a sala que estará na direção
     * O método adiciona essa direção, junto com a sala, ao hashMap que contém todas as saídas
     */
    public void setExits(String direction, Room room) 
    {
        direction = direction.trim().toLowerCase();

        exits.put(direction, room);
    }

    public Map<String,Room> getExits(){

        return this.exits;
    }

    /**
     * @return Retorna a descrição da sala (a que foi
     * definida no construtor).
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Retorna o objeto da classe Room escolhido como saída
     * 
     * @param direction
     * @return a saída (room) desejada
     */

    public Room getExit(String direction){
        direction = direction.trim().toLowerCase();

        return exits.get(direction);
    }

    /**
     * 
     * @return String a ser usada na hora de mostrar todas as saídas de cada sala
     */
    public String getExitString(){
        String retorno = "Exits: ";

        for (String exit : exits.keySet()){ //Iterando uma String dentro das chaves de exits, que são Strings
            retorno += exit + " ";
        }

        retorno += "\n";

        return retorno;
    }

    /**
     * Método facilitador que mostra a descrição do objeto da classe Room
     * @return String com a descrição
     */
    public String getLongDescription(){
        String retorno = "You are " + description + " , " + this.name + ".\n";
        if (items != null){
            if (items.size() != 0){
                retorno += "Room items: ";
                retorno += "\n";
                retorno += Item.returnAllItems(items);
                retorno += "\n";
            }
        }

        if (hasMonster()){
            retorno += monster.getFullInfo();
            retorno += "Monster itens: \n";
            List<Item> monsterItems = (List<Item>)(List<?>) this.monster.getWeapons();
            retorno += Item.returnAllItems(monsterItems);
        }

        retorno += getExitString();

        return retorno;
    }

    public boolean hasMonster(){
        if (monster != null && monster.isAlive()){
            return true;
        }

        return false;
    }

    /**
     * Método acessor básico dos itens
     * @return a lista de itens
     */
    public List<Item> getItems(){
        return this.items;
    }

    /**
     * Método modificador básico dos itens
     * @param items a lista desejada dos itens
     */
    public void setItems(List<Item> items){
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(name, room.name) && Objects.equals(description, room.description) && Objects.equals(exits, room.exits) && Objects.equals(items, room.items) && Objects.equals(monster, room.monster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, exits, items, monster);
    }



}
