package src.game;
/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Essa classe é um enum que contém todos os comandos do jogo.
 * Para adicionar um comando, apenas precisa-se mudar esse enum
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public enum CommandWord {
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), BACK("back"), DROP("drop"), TAKE("take"), TELEPORT("teleport"), TRANSPORT("transport"), ATTACK("attack"),UNKNOWN("?");

    private String commandWord;

    /**
     * Construtor do enum
     * @param commandWord palavra de comando
     */
    CommandWord(String commandWord){
        this.commandWord = commandWord;
    }

    /**
     * Retorna a string do comando
     *      
     */
    public String toString(){
        return commandWord;
    }

}
