package src.rooms;
/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Essa classe é um extensão da classe Room
 * Serve apenas para determinar o ponto de parada do jogo
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class ExitRoom extends Room{

    public ExitRoom(String description) {
        super(description, null);
    }

    public String getLongDescription(){
        return "";
    }
    
}
