package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    public int grassCount;
    public Map<Vector2d, Grass> grasses;
    public GrassField(int n){
        super((int) (sqrt(10*n)), (int) (sqrt(10*n)));
        this.grasses = new HashMap<>(n);
        for(int i=0; i<n; i++){
            Vector2d check = new Vector2d(this.getRandomNumber(0, this.width), this.getRandomNumber(0, this.height));
            while(grasses.get(check) != null){
                check = new Vector2d(this.getRandomNumber(0, this.width), this.getRandomNumber(0, this.height));
            }
            grasses.put(check, new Grass(check));
        }
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