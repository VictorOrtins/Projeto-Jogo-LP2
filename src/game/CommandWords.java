package src.game;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Esta classe mantém um conjunto de todas as palavras de comandos
 * conhecidas e válidas desse jogo.
 * É usada para reconhecer os comandos quando eles são inseridos
 * pelo usuário.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class CommandWords
{


    private static Map<String, CommandWord> validCommands;

    /**
     * Construtor - inicializa as palavras de comando.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        

        for (CommandWord command : CommandWord.values()){
            if (command != CommandWord.UNKNOWN){
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Verifica se uma dada String é uma palavra de comando válida neste jogo.
     * @return true se a String fornecida é uma palavra de comando válida,
     * false caso contrário.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    public String getValidCommands(){
        String retorno = "";
        for (String palavra : validCommands.keySet()){
            retorno += palavra + " | ";
        }
        
        return retorno;
    }

    public CommandWord getCommandWord(String key){
        CommandWord command = validCommands.get(key);

        if (command != null){
            return command;
        }
        else{
            return CommandWord.UNKNOWN;
        }
    }

    public Map<String, CommandWord> getMapValidCommands(){
        return validCommands;
    }   
}
