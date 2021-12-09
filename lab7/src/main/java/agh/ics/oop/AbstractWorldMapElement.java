package agh.ics.oop;

import java.util.ArrayList;

public class AbstractWorldMapElement implements IMapElement, IPositionChangeObserver{
    protected Vector2d position;
    protected ArrayList<IPositionChangeObserver> observers;
    public AbstractWorldMapElement(Vector2d location){
        this.position = location;
        this.observers = new ArrayList<>();
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, this.position);
        }
    }
}
