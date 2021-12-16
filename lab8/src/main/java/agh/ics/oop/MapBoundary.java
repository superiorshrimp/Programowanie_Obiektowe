package agh.ics.oop;

import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    public GrassField map;
    protected SortedSet<Vector2d> xObjects;
    protected SortedSet<Vector2d> yObjects;
    public int xMin;
    public int yMin;
    public int xMax;
    public int yMax;
    public MapBoundary(GrassField map){
        this.map = map;
        this.xObjects = new TreeSet<>(new XComparatorElements());
        this.yObjects = new TreeSet<>(new YComparatorElements());
        for(Animal animal : map.animalList){
            xObjects.add(animal.getPosition());
            yObjects.add(animal.getPosition());
        }
        for(Grass grass : map.grassList){
            xObjects.add(grass.getPosition());
            yObjects.add(grass.getPosition());
        }
        this.xMin = xObjects.first().x;
        this.yMin = yObjects.first().y;
        this.xMax = xObjects.last().x;
        this.yMax = yObjects.last().y;
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.xObjects.remove(oldPosition);
        this.yObjects.remove(oldPosition);
        this.xObjects.add(newPosition);
        this.yObjects.add(newPosition);
        if(newPosition.x<this.xMin){
            this.xMin = newPosition.x;
        }
        else if(newPosition.x>this.xMax){
            this.xMax = newPosition.x;
        }
        else if(newPosition.y<this.yMin){
            this.yMin = newPosition.y;
        }
        else if(newPosition.y>this.yMax){
            this.yMax = newPosition.y;
        }
    }
}
