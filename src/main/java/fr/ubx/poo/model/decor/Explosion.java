package fr.ubx.poo.model.decor;

public class Explosion extends Decor{
    @Override
    public boolean isDestructible() {
        return false;
    }

    public boolean isExplosion(){
        return true;
    }
}
