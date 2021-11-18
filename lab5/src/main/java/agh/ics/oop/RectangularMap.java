package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap{
    public RectangularMap(int width, int height){
        super(width, height);
    }
    public Animal objectAt(Vector2d position){
        for(int i=0; i<this.animals.size(); i++){
            if(this.animals.get(i).getPosition().equals(position)){
                return this.animals.get(i);
            }
        }
        return null;
    }
}