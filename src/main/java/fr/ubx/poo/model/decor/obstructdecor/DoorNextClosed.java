package fr.ubx.poo.model.decor.obstructdecor;

public class DoorNextClosed extends ObstructDecor {
    @Override
    public String toString() {
        return "DoorNextClosed";
    }

    public boolean isClosedDoor(){return true;}
}
