package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    public int grassCount;
    public Map<Vector2d, Grass> grasses;
    public LinkedList<Grass> grassList;
    public GrassField(int n){
        super((int) (sqrt(10*n)), (int) (sqrt(10*n)));
        this.grasses = new HashMap<>(n);
        this.grassList = new LinkedList<>();
        for(int i=0; i<n; i++){
            Vector2d check = new Vector2d(this.getRandomNumber(0, this.width), this.getRandomNumber(0, this.height));
            while(grasses.get(check) != null){
                check = new Vector2d(this.getRandomNumber(0, this.width), this.getRandomNumber(0, this.height));
            }
            Grass checked = new Grass(check);
            grasses.put(check, checked);
            grassList.add(checked);
        }
    }
    public Grass generateGrass(){
        Vector2d check = new Vector2d(this.getRandomNumber(0, this.width), this.getRandomNumber(0, this.height));
        while(grasses.get(check) != null){
            check = new Vector2d(this.getRandomNumber(0, this.width), this.getRandomNumber(0, this.height));
        }
        Grass checked = new Grass(check);
        return checked;
    }
    public boolean replaceGrass(Grass toRemove, Grass toAdd){
        if(toRemove!=null){
            this.grasses.remove(toRemove.getPosition());
            this.grassList.remove(toRemove);
        }
        if(this.grasses.containsKey(toAdd.getPosition())){
            return false;
        }
        else{
            this.grasses.put(toAdd.getPosition(), toAdd);
            this.grassList.add(toAdd);
        }
        return true;
    }
    public Object objectAt(Vector2d position){ //Animal ma prio
        if(super.objectAt(position)!=null){
            return super.objectAt(position);
        }
        else if(grasses.get(position)!=null){
            return grasses.get(position);
        }
        return null;
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}