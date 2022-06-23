package src.characters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import src.exceptions.AlreadyExistingItemException;
import src.exceptions.MonsterAliveException;
import src.exceptions.SecondWordException;
import src.exceptions.ThirdWordException;
import src.exceptions.UsableWeaponException;
import src.game.Command;
import src.game.Game;
import src.itens.Food;
import src.itens.Item;
import src.itens.Weapon;
import src.rooms.ExitRoom;
import src.rooms.Room;
import src.rooms.TeleportRoom;
import src.rooms.TransporterRoom;

/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Esta classe é uma extensão da classe de personagem
 * É o jogador ativo, que poderá perambular pelas salas, enfrentar inimigos e achar a saída
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Player extends Character {

    private final static int INITIAL_MAXIMUM_WEIGHT = 5;
    
    private List<Item> itens; //Itens do jogador
    private Room previousRoom; //Room passada do personagem
    private double maximumWeight; //Peso máximo que o jogador pode carregar

    /**
     * Construtor apenas com o parâmetro nome
     * @param name Nome desejada ao personagem
     */ 

    public Player(String name){
        super(name);
        this.previousRoom = null;
        maximumWeight = INITIAL_MAXIMUM_WEIGHT;
        itens = new ArrayList<Item>();
    }

    /**
     * Construtor da classe Player
     * @param name nome do jogador
     * @param currentRoom sala atual
     */
    public Player(String name, Room currentRoom) {
        super(name, currentRoom);
        this.previousRoom = null;
        maximumWeight = 3;
        itens = new ArrayList<Item>();
    }

    /**
     * Método acessor básico do atributo itens (um arrayList)
     * @return itens do character
     */
    public List<Item> getItens() {
        return this.itens;
    }

    /**
     * Método modificador básico do atributo itens (um arrayList)
     * @param name itens desejados ao character
     */
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    /**
     * Méodo acessor da sala anterior
     * @return sala anterior
     */
    public Room getPreviousRoom() {
        return this.previousRoom;
    }

    /**
     * Método modificador da sala anterior
     * @param previousRoom sala anterior
     */
    public void setPreviousRoom(Room previousRoom) {
        this.previousRoom = previousRoom;
    }

    /**
     * Método acessor básico do peso máximo
     * @return peso máximo 
     */
    public double getMaximumWeight() {
        return this.maximumWeight;
    }

    /**
     * Método facilitador que retorna uma string com a descrição da saúde do personagem
     * @return
     */
    protected String characterHealth(){
        String retorno = "Player health is: ";
        retorno += this.getHealth() + "\n";

        return retorno;
    }

    /**
     * Método modificador básico do peso máximo
     * @param maximumWeight
     */
    public void setMaximumWeight(double maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public double currrentWeight(){
        double currrentWeight = 0;
        for (Item outroItem : getItens()){
            currrentWeight += outroItem.getWeight();
        }

        return currrentWeight;
    }

    /**
     * Adiciona um item ao ArrayList de itens
     * @param item item desejado
     */
    public boolean addItem(Item item){
        double pesoAtual = this.currrentWeight();

        if (pesoAtual + item.getWeight() <= maximumWeight){
            getItens().add(item);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adiciona um item ao ArrayList de itens
     * @param name nome do item
     * @param description descrição do item
     * @param weight peso do item
     */
    public boolean addItem(String name, String description, double weight){
        Item item = new Item(name, description, weight);

        double pesoAtual = this.currrentWeight();

        if (pesoAtual <= maximumWeight){
            getItens().add(item);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Tenta ir em uma direção. Se houver uma saída, entra
     * na nova sala, caso contrário, imprime uma mensagem de erro.
     * @param command comando dado
     * @throws ThirdWordException
     * @throws SecondWordException
     */

    public boolean goRoom(Command command) throws ThirdWordException, SecondWordException 
    {
        if(!command.hasSecondWord()) {
            // se não houver nenhuma segunda palavra,
            // não sabemos para onde ir
            throw new SecondWordException("Lacking command");
        }

        if (command.hasThirdWord()){
            throw new ThirdWordException("Too much command");
        }


        String direction = command.getSecondWord();

        // Tenta sair da sala atual.
        Room nextRoom = getCurrentRoom().getExit(direction); 

        if (nextRoom == null) { //Se não tem saída para essa direção
            throw new NullPointerException("Invalid direction");
        }
        else {
            this.setPreviousRoom(this.getCurrentRoom());
            this.setCurrentRoom(nextRoom);
            return true;
        }

    }

    /**
     * Retorna a sala anterior em que o jogador se encontrava
     * @param command comando dado
     * @throws SecondWordException
     * @throws IllegalArgumentException
     */

    public boolean back(Command command) throws SecondWordException, IllegalArgumentException{
        if (command.hasSecondWord()){
            //Se houver uma segunda palavra
            //Comando está errado
            throw new SecondWordException("No need for a second word");
        }


        if (this.getPreviousRoom() == null){
            //Se o jogador ainda está no início
            throw new NullPointerException("Previous room is null");
        }
        else if (this.getPreviousRoom() instanceof TeleportRoom){
            //Se a sala anterior do jogador é uma sala de teleporte
            throw new IllegalArgumentException("You can't use back for a teleport room");
        }
        else{
            Room auxiliar;
            auxiliar = this.getCurrentRoom(); //Armazena o valor do room atual
            this.setCurrentRoom(this.getPreviousRoom());
            this.setPreviousRoom(auxiliar);; //this.previousRoom = this.currentRoom, na prática
            return true;
        }
    }

    /** 
     * "Quit" foi inserido. Verifica se o usuário digitou alguma outra
     * palavra na frase apenas para decidir se realmente encerraremos o jogo.
     * @return Retorna true se "command" é um comando capaz de encerrar o jogo, false caso contrário.
     * @throws SecondWordException
     */
    public boolean quit(Command command) throws SecondWordException 
    {
        if(command.hasSecondWord()) {
            throw new SecondWordException("There's no need for a second word");
        }
        else {
            return true;  // Sinaliza que queremos encerrar o jogo
        }
    }


    /**
     *  Pega a informação da sala em que o jogador está
     * @return descrição da sala
     */
    public String getLocationInfo(){
        return this.getCurrentRoom().getLongDescription();
    }

    /**
     * Comando para o jogador comer
     * @return String padrão
     * @throws SecondWordException
     * @throws ThirdWordException
     * @throws IllegalArgumentException
     */
    public boolean eat(Command command) throws SecondWordException, ThirdWordException, IllegalArgumentException{
        if (!command.hasSecondWord()){
            // Se o comando não tiver uma segunda palavra
            throw new SecondWordException("What are you trying to eat?!");
        }

        if (command.hasThirdWord()){
            //Se o comando tiver uma terceira palavra
            throw new ThirdWordException("No need for a third word");
        }

        if (this.getItens() != null){ //Se o personagem tiver itens
            int foodNumber = -1;

            try{
                foodNumber = Integer.parseInt(command.getSecondWord());
            }
            catch(NumberFormatException e){
                throw e;
            }

            if (foodNumber >= 0 && foodNumber < itens.size()){
                Item item = itens.get(foodNumber);
                if (item instanceof Food){
                    Food food = (Food) item;
                    this.setHealth(food.getPlusHealth() + this.getHealth());
                    this.setMaximumWeight(food.getPlusMaxWeight() + this.getMaximumWeight());
                    itens.remove(food);
                    return true;
                }
                else{
                    throw new IllegalArgumentException("The item selected needs to be of the type Food");
                }
            }
            else{
                throw new ArrayIndexOutOfBoundsException("Item number needs to be within the array");
            }
        }else{
            throw new NullPointerException("You don't have itens");
        }



    }

    /**
     * Esse método dá uma String com as informações dos itens que o jogador possui
     * @return String sobre os itens
     */
    private String playerItems(){
        String retorno = "Your items: \n";
        if (itens != null && itens.size() != 0){
            retorno += Item.returnAllItems(itens);
        }

        /* 
        for (int i = 0; i < this.getItens().size(); i++){
            retorno += "[" + i + "] The " + this.getItens().get(i).showItem();
        }
        */
        retorno += "Current weight: " + currrentWeight() + "kg\n";
        retorno += "Maximum weight: " + maximumWeight + "kg\n";
        retorno += "\n";

        return retorno;
    }

    public boolean drop(Command command) throws SecondWordException, ThirdWordException, AlreadyExistingItemException{
        if (!command.hasSecondWord()){
            //Se o comando não tiver segunda palavra

            throw new SecondWordException("Drop which item?");
        }

        if (command.hasThirdWord()){
            //Se o comando tiver terceira palavra

            throw new ThirdWordException("I don't know what to do");
        }

        if (this.getItens() != null){ //Se os itens existirem
            int itemNumber;

            try{
                itemNumber = Integer.parseInt(command.getSecondWord());
            }
            catch(NumberFormatException e){
                throw e;
            }

            if (itemNumber < this.getItens().size() && itemNumber >= 0){ //Se o item estiver na lista
                Item itemAuxiliar = this.getItens().get(itemNumber);

                boolean verificador;
    
                verificador = this.getCurrentRoom().addItem(itemAuxiliar);
                if (verificador){ //Se o item foi adicionado
                    this.getItens().remove(itemAuxiliar);
                    return true;
                }
                else{
                    throw new AlreadyExistingItemException("Player already has this item");
                }
    
            }
            else{
                throw new ArrayIndexOutOfBoundsException("Choose an existing item");
            }
        }
        else{
            throw new NullPointerException("You have no item list");
        }
    }

    public boolean take(Command command) throws SecondWordException, ThirdWordException, AlreadyExistingItemException{
        if (!command.hasSecondWord()){
            //Se o comando não tiver segunda palavra

            throw new SecondWordException("Take which item?");
        }

        if (command.hasThirdWord()){
            //Se o comando tiver terceira palavra

            throw new ThirdWordException("Invalid use of the third word");
        }

        int itemNumber;

        try{
            itemNumber = Integer.parseInt(command.getSecondWord());
        }
        catch(NumberFormatException e){
            throw e;
        }

        if (itemNumber < this.getCurrentRoom().getItems().size() && itemNumber >= 0){
            Item itemNovo = this.getCurrentRoom().getItems().get(itemNumber);
            boolean verificador;
            verificador = this.addItem(itemNovo);
            
            if (verificador){
                this.getCurrentRoom().getItems().remove(itemNovo);
                return true;            }
            else{
                throw new AlreadyExistingItemException("This item already existed");
            }

        }
        else{
            throw new ArrayIndexOutOfBoundsException("Choose a item that's in the array");
        }
    }

    /**
     * É um método facilitador, ele retorna os itens e as informações do room num só método
     * @return String completa com informações
     */
    public String getFullInfo(){

        if (this.getCurrentRoom() instanceof ExitRoom){
            return "";
        }

        if (this.getItens() != null && this.getItens().size() > 0){
            return this.characterHealth() + this.playerItems() +  this.getLocationInfo();
        }
        else{
            return this.characterHealth() + this.getLocationInfo();
        }
    }

    public boolean teleport(Command command) throws SecondWordException, ThirdWordException{
        if (this.getCurrentRoom() instanceof TeleportRoom){
            if (!command.hasSecondWord()){
                //Se a segunda palavra não existir
                throw new SecondWordException("A teleport destination needs to be adressed");
            }

            if (command.hasThirdWord()){
                //Se a terceira palavra existir
                throw new ThirdWordException("No need for a third word");
            }

            TeleportRoom thisRoom = (TeleportRoom)this.getCurrentRoom();
            int roomNumber;

            try{
                roomNumber = Integer.parseInt(command.getSecondWord());
            }
            catch(NumberFormatException e){
                throw e;
            }

            Room nextRoom;

            if (roomNumber < thisRoom.getPossibleLocations().size() && roomNumber >= 0){
                nextRoom = thisRoom.getPossibleLocations().get(roomNumber);
                this.setCurrentRoom(nextRoom);
                this.previousRoom = thisRoom;
                return true;
            }
            else{
                throw new ArrayIndexOutOfBoundsException("Choose a room that is in the array");
            }
        }
        else{
            throw new IllegalArgumentException("Teleport only works in a teleport room");
        }
    }

    public boolean transport(Command command) throws SecondWordException, ThirdWordException{
        if (!command.hasSecondWord()){
            throw new SecondWordException("Second word required");
        }
        else if (command.hasThirdWord()){
            throw new ThirdWordException("Third word not required");
        }
        else if(! (this.getCurrentRoom() instanceof TransporterRoom)){
            throw new IllegalArgumentException("Transport only in transporter rooms");
        }

        int roomNumber;

        try{
            roomNumber = Integer.parseInt(command.getSecondWord());
        }
        catch(NumberFormatException e){
            throw e;
        }

        TransporterRoom thisRoom = (TransporterRoom) this.getCurrentRoom();
        Room rooms[] = thisRoom.getDestinationRooms();

        if (roomNumber > 0 || roomNumber < Game.FLOORS){
            Room nextRoom = rooms[roomNumber];
            this.setPreviousRoom(thisRoom);
            this.setCurrentRoom(nextRoom);
            return true;
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Choose an item that's in the room");
        }

    }

    public boolean attack(Command command) throws ThirdWordException, SecondWordException, UsableWeaponException, MonsterAliveException {
        if (!command.hasSecondWord()){
            throw new SecondWordException("Choose an weapon to attack");
        }

        if (command.hasThirdWord()){
            throw new ThirdWordException("Third word not needed");
        }

        if (!this.getCurrentRoom().hasMonster()){
            throw new MonsterAliveException("Can only attack in a room with a monster");
        }

        int itemNumber;

        try{
            itemNumber = Integer.parseInt(command.getSecondWord());
        }
        catch(NumberFormatException e){
            throw e;
        }

        if (hasItens()){
            if (itemNumber < itens.size() && itemNumber >= 0){
                if (itens.get(itemNumber) instanceof Weapon){
                    Weapon weapon = (Weapon) itens.get(itemNumber);
                    int weaponDamage;
                    int weaponDurability;
                    Monster roomMonster = getCurrentRoom().getMonster();

                    if (weapon.isUsable()){
                        weaponDamage = weapon.getDamage();
                        weaponDurability = weapon.getDurability();

                        roomMonster.setHealth(roomMonster.getHealth() - weaponDamage);
                        weapon.setDurability(weaponDurability - 1);
                        return true;
                        
                    }
                    else{
                        throw new UsableWeaponException("The weapon choosed needs to be usable");
                    }

                }
                else{
                    throw new IllegalArgumentException("A weapon must be choosed");
                }
            }
            else{
                throw new ArrayIndexOutOfBoundsException("Weapon not in array");
            }
        }
        else{
            throw new NullPointerException("You need to have itens to attack");
        }
         


    }

    public boolean hasItens(){
        if (getItens() != null && getItens().size() > 0){
            return true;
        }

        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(itens, player.itens) && Objects.equals(previousRoom, player.previousRoom) && maximumWeight == player.maximumWeight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itens, previousRoom, maximumWeight);
    }


}
