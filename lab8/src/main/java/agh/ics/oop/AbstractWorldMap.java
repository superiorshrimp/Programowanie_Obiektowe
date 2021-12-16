package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final int width;
    protected final int height;
    protected Map<Vector2d, Animal> animals;
    public LinkedList<Animal> animalList;
    public AbstractWorldMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new HashMap<>();
        this.animalList = new LinkedList<>();
    }
    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position) && position.precedes(new Vector2d(this.width,this.height)) && position.follows(new Vector2d(0, 0));
    }
    public boolean isOccupied(Vector2d position){
        return animals.get(position)!=null;
    }
    public boolean place(Animal animal){
        if(!isOccupied(animal.getPosition())){
            this.animals.put(animal.getPosition(),animal);
            this.animalList.add(animal);
            return true;
        }
        return false;
    }
    public Object objectAt(Vector2d position){
        return this.animals.get(position);
    }
    public String toString(){
        return new MapVisualizer(this).draw(new Vector2d(0,0), new Vector2d(this.width, this.height));
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal change = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, change);
    }
}