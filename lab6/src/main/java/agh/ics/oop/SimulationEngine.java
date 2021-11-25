package agh.ics.oop;

public class SimulationEngine implements IEngine{
    public AbstractWorldMap map;
    public MoveDirection[] directions;
    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] locations){
        this.directions = directions;
        this.map = map;
        for(int i=0; i<locations.length; i++){
            Animal baby = new Animal(locations[i]);
            map.place(baby); //if jakis?
            baby.addObserver(map);
        }
    }
    public void run(){
        for(int i=0; i<this.directions.length/this.map.animalList.size(); i++){
            int j = 0;
            for(Animal toCheck : this.map.animalList){
                if(this.directions[this.map.animalList.size()*i+j]==MoveDirection.FORWARD){
                    if(this.map.canMoveTo(toCheck.getPosition().add(toCheck.getFacing().toUnitVector()))){
                        toCheck.move(this.directions[this.map.animalList.size()*i+j]);
                    }
                    else{
                        continue;
                    }
                }
                else if(this.directions[this.map.animalList.size()*i+j]==MoveDirection.BACKWARD){
                    if(this.map.canMoveTo(toCheck.getPosition().subtract(toCheck.getFacing().toUnitVector()))){
                        toCheck.move(this.directions[this.map.animalList.size()*i+j]);
                    }
                    else{
                        continue;
                    }
                }
                else{
                    toCheck.move(this.directions[this.map.animalList.size()*i+j]);
                }
                j++;
            }
        }
        System.out.println(this.map.toString());
    }
}