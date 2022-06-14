package src.rooms;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Esta classe é uma extensão da classe Room, e é feita para teleportar
 * os jogadores para uma sala de seu desejo
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class TeleportRoom extends Room{
    List<Room> possibleLocations;

    public TeleportRoom(String description, String name){
        super(description, name);
        possibleLocations = new ArrayList<Room>();
    }

    public void addTeleportLocation(Room room){
        if (room != null){
            possibleLocations.add(room);
        }
    }

    
    public String getLongDescription(){
        String retorno = super.getLongDescription();
        retorno += "Teleport locations: \n";
        for (int i = 0; i < possibleLocations.size(); i++){
            retorno += "[" + i + "] " + possibleLocations.get(i).getName() + "\n";
        }

        return retorno;
    }
    

    public List<Room> getPossibleLocations() {
        return this.possibleLocations;
    }

    public void setPossibleLocations(List<Room> possibleLocations) {
        this.possibleLocations = possibleLocations;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TeleportRoom)) {
            return false;
        }
        TeleportRoom teleportRoom = (TeleportRoom) o;
        return Objects.equals(possibleLocations, teleportRoom.possibleLocations);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(possibleLocations);
    }

    
}
