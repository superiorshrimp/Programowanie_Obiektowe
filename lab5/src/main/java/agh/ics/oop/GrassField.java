package agh.ics.oop;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    public int grassCount;
    public ArrayList<Grass> grasses;
    public GrassField(int n){
        super((int) (sqrt(10*n)), (int) (sqrt(10*n)));
        this.grasses = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            Vector2d check = new Vector2d(this.getRandomNumber(0, this.width), this.getRandomNumber(0, this.height));
            int flag = 0;
            while(flag!=grasses.size()){
                flag = 0;
                check = new Vector2d(this.getRandomNumber(0, this.width), this.getRandomNumber(0, this.height));
                for(int j=0; j<grasses.size(); j++){
                    if(!check.equals(grasses.get(j).getPosition())){
                        flag++;
                    }
                }
            }
            grasses.add(new Grass(check));
        }
    }
    public Object objectAt(Vector2d position){ //Animal ma prio
        for(int i=0; i<this.animals.size(); i++){
            if(this.animals.get(i).getPosition().equals(position)){
                return this.animals.get(i);
            }
        }
        for(int i=0; i<this.grassCount; i++){
            if(this.grasses.get(i).getPosition().equals(position)){
                return this.grasses.get(i);
            }
        }
        return null;
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
