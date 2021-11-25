package agh.ics.oop;

import java.util.ArrayList;

public class Animal{
    private MapDirection facing;
    private Vector2d position;
    private ArrayList<IPositionChangeObserver> observers;
    public Animal(Vector2d initialPosition){
        this.facing = MapDirection.NORTH;
        this.position = new Vector2d(initialPosition.x, initialPosition.y);
        this.observers = new ArrayList<>();
    }
    public MapDirection getFacing(){return this.facing;}
    public Vector2d getPosition(){return this.position;}
    public boolean isAt(Vector2d loc){return this.position.equals(loc);}
    public String toString(){
        return this.facing.toString();
    }
    public void move(MoveDirection direction){
        switch (direction) {
            case LEFT: {
                this.facing = this.facing.previous();
                break;
            }
            case RIGHT: {
                this.facing = this.facing.next();
                break;
            }
            case FORWARD: {
                Vector2d oldPos = this.position;
                this.position = oldPos.add(this.facing.toUnitVector());
                this.positionChanged(oldPos);
                break;
            }
            case BACKWARD: {
                Vector2d oldPos = this.position;
                this.position = oldPos.subtract(this.facing.toUnitVector());
                this.positionChanged(oldPos);
                break;
            }
        }
    }
    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, this.position);
        }
    }
}