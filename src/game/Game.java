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
        centauro.addItem("Vasco Shirt", "ugly as fuck vasco shirt", 0.7);
        centauro.addItem(new Weapon("Football", "Old football", 0.3, 5, 15));
        centauro.addItem(new Food("Protein Bar", "Protein bar, You eat and keep hungry", 0.2, 30, 0));
        centauro.addItem(new Weapon("Boxing gloves", "A new pair of boxing gloves", 1.4, 25, 6));


        hering.setExits("west", tonsShop);
        hering.setExits("south", centauro);
        hering.setExits("north", mcDonalds);
        hering.addItem("Belt","rusty belt", 5);
        hering.addItem(new Food("Meat", "a very bad meat", 2, 60, 2));

        tonsShop.setExits("east", hering);
        tonsShop.addItem( "Rock","tinniest rock ever", 0.5);
        tonsShop.addItem(new Weapon("Theo Isaac", "little man Theo Isaac", 5, 12, 2));

        spoleto.setExits("west", mcDonalds);
        spoleto.setExits("east", elevadorPrimeiroAndar);
        spoleto.addItem(new Weapon("Knife", "Sharpy Knife", 0.3, 17, 4));
        spoleto.addItem(new Food("Spaghetti", "Spaghetti with that white sauce we all like", 0.6, 40, 2));

        elevadorPrimeiroAndar.setExits("west", spoleto);

        mcDonalds.setExits("west", cafe);
        mcDonalds.setExits("east", spoleto);
        mcDonalds.setExits("south", hering);
        mcDonalds.setExits("north", cinema);
        mcDonalds.addItem(new Food("Sushi", "Tasty Sushi", 0.2, 2, 40));
        mcDonalds.addItem("Table","one heck of a table", 4);
        mcDonalds.addItem(new Weapon("Fork", "useful fork,", 0.8, 30, 10));
        mcDonalds.addItem(new Food("Magic cookie", "Magic Cookie", 0.7, 200, 3.7));
        mcDonalds.addItem(new Food("Big Mac", "Best and worst mcdonalds burger, big mac", 0.4, -1.2, 4));
        mcDonalds.addItem(new Weapon("French Fries", "deadly, deadly french fries,", 1.2, 4, 2));

        
        cafe.setExits("north", cinema);
        cafe.setExits("south", lojaDoFlamengo);
        cafe.setExits("east", mcDonalds);
        cafe.addItem("Napkins","A huge amount of napkins", 8);
        cafe.addItem(new Food("Pao de Queijo", "A cheese bread, but it's different", 0.4, 60, 0.2));

        cinema.setExits("south", mcDonalds);
        cinema.addItem(new Food("Popcorn", "Salty popcorn", 0.2, 30, 6));
        cinema.addItem("Morbius", "Morbius - It's Morbin Time", 5);


        lojaDoFlamengo.setExits("north", cafe);
        lojaDoFlamengo.setExits("south", piticas);
        lojaDoFlamengo.setExits("west", escadaRolantePrimeiroAndar);
        lojaDoFlamengo.addItem("Gabigol","Gabigol, the best brazilian player ever", 78);
        lojaDoFlamengo.addItem(new Weapon("Rodinei", "Rodinei: he can make you laught", 20, 2, 30));


        piticas.setExits("north", lojaDoFlamengo);
        piticas.addItem("Piticas shirt","goofy ass nerd shirt", 0.6);
        


        escadaRolantePrimeiroAndar.setExits("up", escadaRolanteSegundoAndar);
        escadaRolantePrimeiroAndar.setExits("east", lojaDoFlamengo);

        //Estacionamento
        //Saída
        
        gameStation.setExits("west", pracaDeAlimentacao);
        gameStation.setExits("south", banheiro);
        gameStation.addItem("Game tickets", "A ticket that you can trade for an bow and arrow that doesn't last for 5 minutes", 0.5);
        gameStation.addItem(new Weapon("Nerf", "a classy nerf", 1, 10, 5));
        gameStation.addItem(new Weapon("Bumber car", "a huge bumper car", 4, 1, 100));

        banheiro.setExits("north", gameStation);
        banheiro.setExits("west", pracaDeAlimentacao);
        banheiro.addItem("Turd", "A biiig piece of turd", 4);
        banheiro.addItem(new Weapon("Toilet Paper", "you can clean and kill people with this toilet paper", 1, 14, 5));
        banheiro.addItem(new Food("Apple", "a very clean apple", 3, 100, 2));

        leitura.setExits("northwest", pracaDeAlimentacao);
        leitura.setExits("west", escadaRolanteSegundoAndar);
        leitura.setExits("south", oticaWamberto);
        leitura.addItem("A sutil arte de tacar o foda-se", "An underrated book, the sutil art of tacar o fds", 2);
        leitura.addItem(new Food("Gum", "strawberry gum", 0.4, 50, 4));
        
        oticaWamberto.setExits("north", leitura);
        oticaWamberto.setExits("west", lojasAmericanas);
        oticaWamberto.addItem("Glasses", "harry potter like glasses", 1);
        oticaWamberto.addItem(new Food("Pear", "a pear in an unknown state", 1, 60, 3));
        
        escadaRolanteSegundoAndar.setExits("down", escadaRolantePrimeiroAndar);
        escadaRolanteSegundoAndar.setExits("west", pirlimpimpim);
        escadaRolanteSegundoAndar.setExits("south", lojasAmericanas);
        escadaRolanteSegundoAndar.setExits("east", leitura);
        
        lojasAmericanas.setExits("north", escadaRolanteSegundoAndar);
        lojasAmericanas.setExits("east", oticaWamberto);
        lojasAmericanas.addItem(new Weapon("a bit of everything", "in a very diverse store, you find a very diverse item", 2, 50, 30));
        lojasAmericanas.addItem(new Food("Chocolate bar", "an oddly good chocolate bar",0.5, 60, 10));

        pracaDeAlimentacao.setExits("northeast", gameStation);
        pracaDeAlimentacao.setExits("east", banheiro);
        pracaDeAlimentacao.setExits("southeast", leitura);
        pracaDeAlimentacao.setExits("north", boliche);
        pracaDeAlimentacao.setExits("south", pirlimpimpim);
        pracaDeAlimentacao.setExits("southeast", escadaRolanteSegundoAndar);
        pracaDeAlimentacao.addItem(new Food("Pizza", "a bad and old pizza piece", 0.9, 80, 0));
        pracaDeAlimentacao.addItem(new Weapon("Machete", "huge machette ", 4, 22, 2));

        elevadorSegundoAndar.setExits("east", pracaDeAlimentacao);
        elevadorSegundoAndar.setExits("north", pracaDeAlimentacao);
        elevadorSegundoAndar.setExits("south", onildo);
        
        onildo.setExits("north", elevadorSegundoAndar);
        onildo.setExits("east", pirlimpimpim);


        lojaDoBelo.setExits("west", salaDeSeguranca);
        lojaDoBelo.setExits("north", pirlimpimpim);
        lojaDoBelo.addItem("Belo shirt", "ugly ass belo jersey", 2);

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

        Monster nebueR; 
        nebueR = new Monster("nebueR", "the Russian guard", salaDeSeguranca, 125);
        nebueR.addWeapon(new Weapon("Trancamento de Curso", "If you get it, you get it.", 5, 15, 3));
        salaDeSeguranca.setMonster(nebueR);

        Monster wamberto;
        wamberto = new Monster("Wamberto", "the rageful owner of the eyeglasses store", oticaWamberto, 110);
        wamberto.addWeapon(new Weapon("canudo", "wamberto can blow the straw, and make a huge impact",1, 20, 3));
        oticaWamberto.setMonster(wamberto);

        Monster gorgonzola_gengibre_guacamole;
        gorgonzola_gengibre_guacamole = new Monster("Gorgonzola, Gengibre e Guacamole", "the dangerous food trinity", pracaDeAlimentacao, 150);
        gorgonzola_gengibre_guacamole.addWeapon(new Weapon("fat", "those brutal monsters can make you overweight", 4, 10, 3));
        pracaDeAlimentacao.setMonster(gorgonzola_gengibre_guacamole);
        
        Monster fred;
        fred = new Monster("Professor fred", "an 'ordinary' calculus teacher", hering, 80);
        fred.addWeapon(new Weapon("geogebra", "causes brain stun in his students", 5, 15, 2));
        fred.addWeapon(new Weapon("canetinha", "'caiiu a canetinha'", 1, 5, 1));
        hering.setMonster(fred);

        Monster unifilis;
        unifilis = new Monster("unifilis", "the greek philosopher... or the STD", banheiro, 100);
        unifilis.addWeapon(new Weapon("paideia", "the greek education can be harmful", 2, 10, 2));
        unifilis.addWeapon(new Weapon("HIV", "a really bad disease", 1, 35, 1));
        banheiro.setMonster(unifilis);

        Monster graben;
        graben = new Monster("Graben", "a shapeless lovecraftian creature", mcDonalds, 60);
        graben.addWeapon(new Weapon("Horst", "a dangerous seismic anomaly", 3, 10, 3));
        mcDonalds.setMonster(graben);

        Monster zeugmae;
        zeugmae = new Monster("Zeugmae", "A mystical nerd Wizard", piticas, 30);
        zeugmae.addWeapon(new Weapon("the staff of truth", "he literally throws the truth in your face, isn't that magical?", 3, 5, 3));
        piticas.setMonster(zeugmae);
       
        Monster dablo;
        dablo = new Monster("Mr Dablo", "the miserable one", estacionamento, 175);
        dablo.addWeapon(new Weapon("dablozinhos", "Mr dablo can throw dablozinhos in your direction, it doest seem dangerous, but it really is!", 2, 40, 5));
        estacionamento.setMonster(dablo);

        //mcDonalds.setExits("northeast", saida);
        //saida.setExits("southwest", mcDonalds);



        //this.player.setCurrentRoom(pracaDeAlimentacao);  // iniciar game na praça de alimentação
        this.player.addItem(new Weapon("Bad knife", "A not so sharp wife", 0.2, 2, 400));
        this.player.setCurrentRoom(centauro);
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
            System.out.println("\n");
            if (!this.player.isAlive()){
                System.out.println("You're dead. Try again");
                break;
            }

            Command command;
        
            command = parser.getCommand();


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

            if (this.player.getCurrentRoom().hasMonster()){
                System.out.println("||| You can't leave a room with a monster! |||");
                return false;
            }

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
            System.out.println("---------------------");
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
                                        "Your command words are: \n" +
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
