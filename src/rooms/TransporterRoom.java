package src.rooms;
import java.util.Objects;

import src.game.Game;

/**
 * Esta classe é parte da aplicação "Jampa Mil Grau".
 * O "Jampa Mil Grau" é um jogo de aventura em texto simples.
 * 
 * Esta classe é uma sala que vai transportar os jogadores para
 * outras 2 possíveis salas
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class TransporterRoom extends Room{

    private Room destinationRooms[];

    public TransporterRoom(String description, String name) {
        super(description, name);
        destinationRooms = new Room[Game.FLOORS - 1];
    }

    public void setDestinationRooms(Room[] rooms){
        for (Room room: rooms){
            if (room == null){
                return;
            }
        }

        this.destinationRooms = rooms;

    }

    public String getLongDescription(){
        String retorno = super.getLongDescription();
        retorno += "Transport locations: \n";
        for (int i = 0; i < Game.FLOORS - 1; i++){
            retorno += "[" + i + "] " + destinationRooms[i].getName() + "\n";
        }

        return retorno;
    }

    public Room[] getDestinationRooms() {
        return this.destinationRooms;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TransporterRoom)) {
            return false;
        }
        TransporterRoom transporterRoom = (TransporterRoom) o;
        return Objects.equals(destinationRooms, transporterRoom.destinationRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(destinationRooms);
    }



    





}
