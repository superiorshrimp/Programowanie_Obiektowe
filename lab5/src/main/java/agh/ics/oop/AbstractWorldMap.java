package agh.ics.oop;

import java.util.ArrayList;

abstract class AbstractWorldMap implements IWorldMap{
    protected final int width;
    protected final int height;
    protected ArrayList<Animal> animals;
    public AbstractWorldMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<>();
    }
    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position) && position.precedes(new Vector2d(this.width,this.height)) && position.follows(new Vector2d(0, 0));
    }
    public boolean isOccupied(Vector2d position){
        for(int i=0; i<this.animals.size(); i++){
            if(this.animals.get(i).getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }
    public boolean place(Animal animal){
        if(!isOccupied(animal.getPosition())){
            this.animals.add(animal);
            return true;
        }
        return false;
    }
    public String toString(){
        return new MapVisualizer(this).draw(new Vector2d(0,0), new Vector2d(this.width, this.height));
    }
}
