package src.game;

import src.characters.Monster;
import src.characters.Player;
import src.exceptions.AlreadyExistingItemException;
import src.exceptions.MonsterAliveException;
import src.exceptions.SecondWordException;
import src.exceptions.ThirdWordException;
import src.exceptions.UsableWeaponException;
import src.itens.Food;
import src.itens.Weapon;
import src.rooms.ExitRoom;
import src.rooms.Room;
import src.rooms.TeleportRoom;
import src.rooms.TransporterRoom;

/**
 *  Esta classe é parte da aplicação "Jampa Mil Grau".
 *  O "Jampa Mil Grau" é um jogo de aventura em texto simples.  Os Usuários
 *  podem perambular pelo cenário. Isso é tudo! Esse jogo deve ser 
 *  realmente aprimorado para torná-lo mais interessante!
 * 
 *  Para jogar, primeiro crie uma instância desta classe, em seguida, chame
 *  o método "play".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria todas
 *  as salas, cria o Parser ("tradutor") e inicia o jogo propriamente dito.
 *  Ela também interpreta e executa os comandos que o Parser retorna.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Game 
{
    public static final int FLOORS = 3;


    private Parser parser; // "tradutor"/"interpretador"/"analisador"
    private Player player; //Jogador 
        
    /**
     * Cria o jogo, o player e inicializa seu mapa (de jogo) interno.
     * @param name Nome desejado ao jogador
     */
    public Game(String name) 
    {
        parser = new Parser();
        createPlayer(name);
        createRooms();
    }

    /**
     * Cria todas as salas e liga suas saídas umas as outras.
     */
    private void createRooms()
    {
        Room centauro, hering, tonsShop, spoleto, mcDonalds, cafe, cinema, lojaDoFlamengo, piticas,
        lojasAmericanas, boliche, leitura, banheiro, salaDeSeguranca, pirlimpimpim, gameStation,
        onildo, oticaWamberto, estacionamento, pracaDeAlimentacao;

        Room escadaRolantePrimeiroAndar, escadaRolanteSegundoAndar;
        TransporterRoom elevadorPrimeiroAndar, elevadorSegundoAndar, elevadorEstacionamento;
        TeleportRoom lojaDoBelo;
        ExitRoom saida;
        
    

        //Primeiro andar
        centauro = new Room("in the sports store", "Centauro");
        hering = new Room("in the clothing store", "Hering");
        tonsShop = new Room("in Tons shop", "Ton's Shop");
        spoleto = new Room("in the pasta restaurant", "Spoleto");
        mcDonalds = new Room("at the fast food", "McDonalds");
        cafe = new Room("in the new cafe area", "São Braz");
        cinema = new Room("in the theater", "Cinepolis");
        lojaDoFlamengo = new Room("in the brand new store", "Flamengo Store");
        piticas = new Room("at that goofy store for nerds", "Piticas");

        //saida = new Room("at the exit! You can finally leave this crappy place");

        //Segundo 
        lojasAmericanas = new Room("in the american store facility", "Lojas Americanas");
        boliche = new Room("at the old and boring bowling room", "AZ Boliche");
        leitura = new Room("at the library where u never buy books", "Leitura");
        banheiro = new Room("in the unused and dirty facility", "Bathroom");
        salaDeSeguranca = new Room("at the room your kid is never at", "Security Room");
        pirlimpimpim = new Room("in the nostalgic and bad play center", "Pirlimpimpim");
        gameStation = new Room("in the good old playing place", "Game Station");
        onildo = new Room("in a quite expensive hairdresser", "Onildo");
        oticaWamberto = new Room("at the Wamberto's work place", "Otica Wamberto");
        estacionamento = new Room("in the frighteningly empty parking lot", "Estacionamento");
        pracaDeAlimentacao = new Room("in the food court", "Praça de Alimentação");
        
        escadaRolantePrimeiroAndar = new Room("at the first floor", "in the escalator");
        escadaRolanteSegundoAndar = new Room("at the second floor", "in the escalator");


        elevadorPrimeiroAndar = new TransporterRoom("at the elevator of the first floor", "Elevador Primeiro Andar"); //A definir
        elevadorSegundoAndar = new TransporterRoom("at the elevator of the second floor", "Elevador Segundo Andar");
        elevadorEstacionamento = new TransporterRoom("at the elevator at the parking lot", "Elevador Estacionamento");

        Room rooms1[] = new Room[2];
        rooms1[0] = elevadorPrimeiroAndar;
        rooms1[1] = elevadorSegundoAndar;

        Room rooms2[] = new Room[2];
        rooms2[0] = elevadorPrimeiroAndar;
        rooms2[1] = elevadorEstacionamento;

        Room rooms3[] = new Room[2];
        rooms3[0] = elevadorSegundoAndar;
        rooms3[1] = elevadorEstacionamento;

        elevadorPrimeiroAndar.setDestinationRooms(rooms3);
        elevadorSegundoAndar.setDestinationRooms(rooms2);
        elevadorEstacionamento.setDestinationRooms(rooms1);

        lojaDoBelo = new TeleportRoom("in that miserable club store", "Loja do Belo");

        saida = new ExitRoom("at the exit! You finished the game!");

        centauro.setExits("north", hering);
        centauro.addItem("Football", "old Football", 0.9);


        hering.setExits("west", tonsShop);
        hering.setExits("south", centauro);
        hering.setExits("north", mcDonalds);
        hering.addItem("Belt","rusty belt", 5);

        tonsShop.setExits("east", hering);
        tonsShop.addItem( "Rock","tinniest rock ever", 0.5);

        spoleto.setExits("west", mcDonalds);
        spoleto.setExits("east", elevadorPrimeiroAndar);
        spoleto.addItem("Knife","really small knife", 0.01);

        elevadorPrimeiroAndar.setExits("west", spoleto);

        mcDonalds.setExits("west", cafe);
        mcDonalds.setExits("east", spoleto);
        mcDonalds.setExits("south", hering);
        mcDonalds.setExits("north", cinema);
        mcDonalds.addItem("Sushi","tasty sushi", 0.2);
        mcDonalds.addItem("Table","one heck of a table", 4);
        mcDonalds.addItem( "Fork","beautiful fork", 0.01);
        mcDonalds.addItem(new Food("Magic cookie", "Magic Cookie", 0.7, 20, 3.7));

        
        cafe.setExits("north", cinema);
        cafe.setExits("south", lojaDoFlamengo);
        cafe.setExits("east", mcDonalds);
        cafe.addItem("Cup of coffee","huge cup of coffee", 2);

        cinema.setExits("south", mcDonalds);
        cinema.addItem("Popcorn","salty pop ass corn" ,3);

        lojaDoFlamengo.setExits("north", cafe);
        lojaDoFlamengo.setExits("south", piticas);
        lojaDoFlamengo.setExits("west", escadaRolantePrimeiroAndar);
        lojaDoFlamengo.addItem("Gabigol","best brazilian player ever", 78);

        piticas.setExits("north", lojaDoFlamengo);
        piticas.addItem("Piticas shirt","goofy ass nerd shirt", 0.6);

        escadaRolantePrimeiroAndar.setExits("up", escadaRolanteSegundoAndar);
        escadaRolantePrimeiroAndar.setExits("east", lojaDoFlamengo);

        //Estacionamento
        //Saída
        
        gameStation.setExits("west", pracaDeAlimentacao);
        gameStation.setExits("south", banheiro);

        banheiro.setExits("north", gameStation);
        banheiro.setExits("west", pracaDeAlimentacao);

        leitura.setExits("northwest", pracaDeAlimentacao);
        leitura.setExits("west", escadaRolanteSegundoAndar);
        leitura.setExits("south", oticaWamberto);
        
        oticaWamberto.setExits("north", leitura);
        oticaWamberto.setExits("west", lojasAmericanas);
        
        escadaRolanteSegundoAndar.setExits("down", escadaRolantePrimeiroAndar);
        escadaRolanteSegundoAndar.setExits("west", pirlimpimpim);
        escadaRolanteSegundoAndar.setExits("south", lojasAmericanas);
        escadaRolanteSegundoAndar.setExits("east", leitura);
        
        lojasAmericanas.setExits("north", escadaRolanteSegundoAndar);
        lojasAmericanas.setExits("east", oticaWamberto);

        pracaDeAlimentacao.setExits("northeast", gameStation);
        pracaDeAlimentacao.setExits("east", banheiro);
        pracaDeAlimentacao.setExits("southeast", leitura);
        pracaDeAlimentacao.setExits("north", boliche);
        pracaDeAlimentacao.setExits("south", pirlimpimpim);
        pracaDeAlimentacao.setExits("southeast", escadaRolanteSegundoAndar);

        elevadorSegundoAndar.setExits("east", pracaDeAlimentacao);
        elevadorSegundoAndar.setExits("north", pracaDeAlimentacao);
        elevadorSegundoAndar.setExits("south", onildo);
        
        onildo.setExits("north", elevadorSegundoAndar);
        onildo.setExits("east", pirlimpimpim);


        lojaDoBelo.setExits("west", salaDeSeguranca);
        lojaDoBelo.setExits("north", pirlimpimpim);

        lojaDoBelo.addTeleportLocation(onildo);
        lojaDoBelo.addTeleportLocation(estacionamento);
        lojaDoBelo.addTeleportLocation(pracaDeAlimentacao);
        lojaDoBelo.addTeleportLocation(lojaDoFlamengo);
        lojaDoBelo.addTeleportLocation(mcDonalds);
        lojaDoBelo.addTeleportLocation(centauro);

        pirlimpimpim.setExits("south", lojaDoBelo);
        pirlimpimpim.setExits("west", onildo);
        pirlimpimpim.setExits("east", escadaRolanteSegundoAndar);
        pirlimpimpim.setExits("north", pracaDeAlimentacao);


        salaDeSeguranca.setExits("east", lojaDoBelo);

        boliche.setExits("south",pracaDeAlimentacao);

        elevadorEstacionamento.setExits("west", estacionamento);

        estacionamento.setExits("east", elevadorEstacionamento);
        estacionamento.setExits("exit", saida);

        Monster dablo;
        dablo = new Monster("Mr Dablo", "the miserable one", mcDonalds, this);
        dablo.addWeapon(new Weapon("dablinho", "I really dont know", 2, 20, 3));
        mcDonalds.setMonster(dablo);

        //mcDonalds.setExits("northeast", saida);
        //saida.setExits("southwest", mcDonalds);



        //this.player.setCurrentRoom(pracaDeAlimentacao);  // iniciar game na praça de alimentação
        this.player.addItem(new Weapon("Penis", "My penis", 2, 50, 4));
        this.player.setCurrentRoom(mcDonalds);

    }

    private void createPlayer(String name){
        player = new Player(name);
    }

    /**
     * Rotina principal do jogo. Executa um loop até o fim do jogo.
     */
    public void play() 
    {            
        System.out.println(printWelcome());

        // Entra no loop do comando principal. Aqui lemos repetidamente
        // os comandos e os executamos até que o jogo termine.
                
        boolean finished = false;
        while (! finished) {
            Command command;
        
            command = parser.getCommand();

            if (!this.player.isAlive()){
                System.out.println("You're dead. Try again");
                break;
            }

            finished = processCommand(command);
            

            if (this.player.getCurrentRoom() instanceof ExitRoom){
                System.out.println("You made it! You're at the exit!");
                finished = true;
            }


        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private String printWelcome()
    {
        String retorno = "\n";
        retorno += "Welcome to the Jampa Mil Grau game!\n";
        retorno += "Jampa Mil Grau is a new, incredible and  adventure game.\n";
        retorno += "Type '" + CommandWord.HELP + "' if you need help\n\n";
        retorno += this.player.getFullInfo();

        return retorno;
    }

    /**
     * Dado um comando, processa-o (isto é: executa esse comando).
     * @param command O comando a ser processado.
     * @return Retorna true caso o comando finalize o jogo, false caso contrário.
     * @throws Exception
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false; // Se o usuário quer encerrar o jogo
        boolean sucess = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }


        CommandWord commandWord = command.getCommandWord();
        if (commandWord == CommandWord.HELP){
            this.help(command);
        }
        else if (commandWord == CommandWord.GO){

            try{
                sucess = this.player.goRoom(command);
            }
            catch(SecondWordException e){
                System.out.println("||| Go where? |||");
            }
            catch(ThirdWordException e){
                System.out.println("||| What are you trying to say? |||");
            }
            catch(NullPointerException e){
                System.out.println("||| There's no door for this direction! |||");
            }

        }
        else if (commandWord == CommandWord.BACK){

            try{
                sucess = this.player.back(command);
            }
            catch(SecondWordException e){
                System.out.println("||| There's no need of a second word to the back command |||");
            }
            catch(IllegalArgumentException e){
                System.out.println("||| You really thought you could go back like that to a teleport room???? |||");
            }
            catch(NullPointerException e){
                System.out.println("||| You can't use the back command! |||");
            }

        }
        else if (commandWord == CommandWord.DROP){
            try{
                sucess = this.player.drop(command);
            }
            catch(SecondWordException e){
                System.out.println("||| Drop what? |||");
            }
            catch(ThirdWordException e){
                System.out.println("||| What are you trying to say? |||");
            }
            catch(NumberFormatException e){
                System.out.println("||| Choose a valid item |||");
            }
            catch(NullPointerException e){
                System.out.println("||| You don't have an item list |||");
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("||| You need to choose a food in your item list |||");
            }
            catch(AlreadyExistingItemException e){
                System.out.println("||| You already have this item |||");
            }
        }
        else if (commandWord == CommandWord.TAKE){
            try{
                sucess = this.player.take(command);
            }
            catch(SecondWordException e){
                System.out.println("||| Drop what? |||");
            }
            catch(ThirdWordException e){
                System.out.println("||| What are you trying to say? |||");
            }
            catch(NumberFormatException e){
                System.out.println("||| Choose a valid item |||");
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("||| You need to choose a food in your item list |||");
            }
            catch(AlreadyExistingItemException e){
                System.out.println("||| Maximum Weight Hit|||");
            }
        }
        else if (commandWord == CommandWord.LOOK){
            System.out.println(this.player.getLocationInfo());
        }
        else if (commandWord == CommandWord.EAT){

            try{
                sucess = this.player.eat(command);
            }
            catch(SecondWordException e){
                System.out.println("||| You need to say what are you trying to eat! |||");
            }
            catch(ThirdWordException e){
                System.out.println("||| I don't know what you mean with that |||");
            }
            catch(NumberFormatException e){
                System.out.println("||| Choose a valid item |||");
            }
            catch(NullPointerException e){
                System.out.println("||| You don't have an item list |||");
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("||| You need to choose a food in your item list |||");
            }
            catch(IllegalArgumentException e){
                System.out.println("||| That's not a food! |||");
            }

        }
        else if (commandWord == CommandWord.TELEPORT){
            try{
                sucess = this.player.teleport(command);
            }
            catch(SecondWordException e){
                System.out.println("||| You need to say to which room you wanna go! |||");
            }
            catch(ThirdWordException e){
                System.out.println("||| I don't know what you mean with that |||");
            }
            catch(NumberFormatException e){
                System.out.println("||| Choose a valid room|||");
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("||| You need to choose a food in the room list |||");
            }
            catch(IllegalArgumentException e){
                System.out.println("||| You can't teleport outside of a teleport room! |||");
            }
        }
        else if (commandWord == CommandWord.TRANSPORT){
            try{
                sucess = this.player.transport(command);
            }
            catch(SecondWordException e){
                System.out.println("||| You need to say to which room you wanna go! |||");
            }
            catch(ThirdWordException e){
                System.out.println("||| I don't know what you mean with that |||");
            }
            catch(NumberFormatException e){
                System.out.println("||| Choose a valid floor|||");
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("||| You need to choose a floor in the room list |||");
            }
            catch(IllegalArgumentException e){
                System.out.println("||| You can't teleport outside of a transport room! |||");
            }
        }
        else if (commandWord == CommandWord.ATTACK){
            try{
                sucess = this.player.attack(command);
            }
            catch(SecondWordException e){
                System.out.println("||| You need to say to which room you wanna go! |||");
            }
            catch(ThirdWordException e){
                System.out.println("||| I don't know what you mean with that |||");
            }
            catch(NumberFormatException e){
                System.out.println("||| Choose a valid weapon||");
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("||| You need to choose a weapon in the room list |||");
            }
            catch(IllegalArgumentException e){
                System.out.println("||| You need to choose a weapon! |||");
            }
            catch(MonsterAliveException e){
                System.out.println("||| You can't attack in a monsterless room! |||");
            }
            catch(UsableWeaponException e){
                System.out.println("||| You need to choose a weapon that can be used |||");
            }
        }
        else if (commandWord == CommandWord.QUIT){
            try{
                wantToQuit = player.quit(command);
            }
            catch(SecondWordException e){
                System.out.println("||| Quit what? |||");
            }
        }
        else if (commandWord == CommandWord.UNKNOWN){
            System.out.println("I dont know what you mean with that");
            return false;
        }

        if (command.getCommandWord() != CommandWord.QUIT && command.getCommandWord() != CommandWord.LOOK && sucess){
            if (player.getCurrentRoom().hasMonster()){
                Monster monsterInRoom = player.getCurrentRoom().getMonster();
                int damage = monsterInRoom.attack();


                player.setHealth(player.getHealth() - damage);
                System.out.println(monsterInRoom.getName() + " attacked you and caused " + damage + " of damage!");
            }
        }

        if (sucess){
            System.out.println(this.player.getFullInfo());
        }

        return wantToQuit;
    }

    // Implementação dos comandos do usuário:

    public void help(Command command){
        if (!command.hasSecondWord()){
            System.out.println(printHelp());
            return;
        }

        if (command.hasThirdWord()){
            System.out.println("No need for a third word!");
            return;
        }
        
        String secondWord = command.getSecondWord();
        if (secondWord.equals(CommandWord.HELP.toString())){
            System.out.println(printHelp());
            System.out.println("If you want to know about any of the specific comands\nJust hit 'help' + the command you want");
        }
        else if (secondWord.equals(CommandWord.GO.toString())){
            System.out.println("teste");
            //printHelpGo();
            System.out.println(printHelpGo());
        }
        else if (secondWord.equals(CommandWord.BACK.toString())){
            System.out.println(printHelpBack());
        }
        else if (secondWord.equals(CommandWord.DROP.toString())){
            System.out.println(printHelpDrop());
        }
        else if (secondWord.equals(CommandWord.TAKE.toString())){
            System.out.println(printHelpTake());
        }
        else if (secondWord.equals(CommandWord.LOOK.toString())){
            System.out.println(printHelpLook());
        }
        else if (secondWord.equals(CommandWord.EAT.toString())){
            System.out.println(printHelpEat());
        }
        else if (secondWord.equals(CommandWord.TELEPORT.toString())){
            System.out.println(printHelpTeleport());
        }
        else if (secondWord.equals(CommandWord.TRANSPORT.toString())){
            System.out.println(printHelpTransport());
        }
        else if (secondWord.equals(CommandWord.ATTACK.toString())){
            System.out.println(printHelpAttack());
        }
        else if (secondWord.equals(CommandWord.QUIT.toString())){
            System.out.println(printHelpQuit());
        }
        else if (secondWord.equals(CommandWord.UNKNOWN.toString())){
            System.out.println("This comand doesn't exist");
        }

        
    }
    /**
     * Imprime alguma informação de ajuda.
     * Aqui imprimimos algumas mensagens estúpidas e enigmáticas,
     * bem como uma lista de comandos.
     */
    private String printHelp()
    {
       /* System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the Manaíra Shopping mall.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommandWords());
        */
        String Help = new String("You are lost. You are alone. You wander around at the Manaíra Shopping mall.\n" +
                                        " Your command words are: " +
                                         parser.showCommandWords());
        return Help;
    }

    private String printHelpBack(){
       /* System.out.println("Back command takes you to the previous room");
        System.out.println("Just notice that, right now, using the back command takes you to the previous room");
        System.out.println("And not to the previous room in the historic. That historic doesn't exist, yet");
        */
        String HelpBack = new String("Back command takes you to the previous room" +
                "Just notice that, right now, using the back command takes you to the previous room" +
                "And not to the previous room in the historic. That historic doesn't exist, yet\n");
        return HelpBack;
    }

    private String printHelpDrop(){
       /* System.out.println("That's how you drop an item. Just choose the item from your itens list, with the according number");
        System.out.println("And drop it");
        */
        String HelpDrop = new String("That's how you drop an item. Just choose the item from your itens list, with the according number" +
                "And drop it\n");
        return HelpDrop;
    }

    private String printHelpTake(){
        /*System.out.println("That's how you take an item from the room. Just choose the item from the room, with the according number");
        System.out.println("And take it");
         */
        String HelpTake = new String("That's how you take an item from the room. Just choose the item from the room, with the according number" +
                "And take it\n");
        return HelpTake;
    }

    private String printHelpEat(){
        /*System.out.println("This is the way uou eat one of your items. It has to be an instance of a class that");
        System.out.println("called food, that inherits from item");
        */
        String HelpEat = new String("This is the way uou eat one of your items. It has to be an instance of a class" +
                "called food, that inherits from item\n");
        return HelpEat;
    }

    private String printHelpTeleport(){
        //System.out.println("Enter teleport, in a teleport room, and it will teleport you to the selected room. Transport is for elevators and teleport is for teleport rooms");
        String HelpTeleport = new String("Enter teleport, in a teleport room, and it will teleport you to the selected room. Transport is for elevators and teleport is for teleport rooms\n");
        return HelpTeleport;
    }


    private String printHelpQuit(){
        //System.out.println("Quit the game. Loser!");
        String HelpQuit = new String("Quit the game. Loser!\n");
        return HelpQuit;
    }

    private String printHelpGo(){
       // System.out.println("Go to another room. Use 'Go' and one of the directions named in the room exits");
        String HelpGo = new String ("Go to another room. Use 'Go' and one of the directions named in the room exits\n");
        return HelpGo;
    }

    private String printHelpLook(){
        //System.out.println("That prints the information of the room you're at");
        String HelpLook = new String("That prints the information of the room you're at\n");
        return HelpLook;
    }

    private String printHelpAttack(){
        //System.out.println("This is how you attack a monster. You can only use this command if there's a monster in your room");
        String HelpAttack = new String("This is how you attack a monster. You can only use this command if there's a monster in your room\n");
        return HelpAttack;
    }

    private String printHelpTransport(){
        //System.out.println("That's how you use an elevator, basically. Transport is for elevators and teleport is for teleport rooms");
        String HelpTransport = new String("That's how you use an elevator, basically. Transport is for elevators and teleport is for teleport rooms\n");
        return HelpTransport;
    }

    public int getFloors(){
        return FLOORS;
    }

    public Player getPlayer(){
        return this.player;
    }

    


}
