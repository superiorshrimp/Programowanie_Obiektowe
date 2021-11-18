package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap{
    public int width;
    public int height;
    public ArrayList<Animal> animals;
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<Animal>();
    }
    public boolean canMoveTo(Vector2d position){
        return isOccupied(position) == false && position.precedes(new Vector2d(this.width,this.height)) && position.follows(new Vector2d(0, 0));
    }
    public boolean place(Animal animal){
        if(isOccupied(animal.getPosition()) == false){
            this.animals.add(animal);
            return true;
        }
        return false;
    }
    public boolean isOccupied(Vector2d position){
        for(int i=0; i<this.animals.size(); i++){
            if(this.animals.get(i).getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }
    public Animal objectAt(Vector2d position){
        for(int i=0; i<this.animals.size(); i++){
            if(this.animals.get(i).getPosition().equals(position)){
                return this.animals.get(i);
            }
        }
        return null;
    }
    public String toString(){
        MapVisualizer draw = new MapVisualizer(this);
        return draw.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
    }
}