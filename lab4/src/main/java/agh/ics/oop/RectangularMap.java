package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    public int width;
    public int height;
    //public Animal[][] map;
    public List<Animal> animals;
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<Animal>();
        //this.map = new Animal[height][width];
    }
    public boolean canMoveTo(Vector2d position){
        return isOccupied(position) == false && position.precedes(new Vector2d(this.width,this.height)) && position.follows(new Vector2d(0, 0));
    }
    public boolean place(Animal animal){
        if(isOccupied(animal.getPosition()) == false){
            this.animals.add(animal);
            //map[animal.getPosition().y][animal.getPosition().x] = animal;
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
        //return map[position.y][position.x] == null;
    }
    public Object objectAt(Vector2d position){
        for(int i=0; i<this.animals.size(); i++){
            if(this.animals.get(i).getPosition().equals(position)){
                return this.animals.get(i);
            }
        }
        return null;
        //return map[position.y][position.x];
    }
    public String toString(){
        MapVisualizer draw = new MapVisualizer(this);
        return draw.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
    }
}
