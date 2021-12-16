package agh.ics.oop;

public class SimulationEngine implements Runnable{ // IEngine,
    public GrassField map;
    public MoveDirection[] directions;
    public MapBoundary boundaries;
    public SimulationEngine(MoveDirection[] directions, GrassField map, MapBoundary boundaries){
        this.directions = directions;
        this.map = map;
        this.boundaries = boundaries;
    }
    public void run(){
        for(int i=0; i<this.directions.length/this.map.animalList.size(); i++){
            int j = 0;
            for(Animal toCheck : this.map.animalList){
                if(this.directions[this.map.animalList.size()*i+j]==MoveDirection.FORWARD){
                    if(this.map.canMoveTo(toCheck.getPosition().add(toCheck.getFacing().toUnitVector()))){
                        if(this.map.grasses.get(toCheck.getPosition().add(toCheck.getFacing().toUnitVector()))!=null){
                            this.map.replaceGrass(this.map.grasses.get((toCheck.getPosition().add(toCheck.getFacing().toUnitVector()))), this.map.generateGrass());
                        }
                        toCheck.move(this.directions[this.map.animalList.size()*i+j]);
                    }
                    else{
                        continue;
                    }
                }
                else if(this.directions[this.map.animalList.size()*i+j]==MoveDirection.BACKWARD){
                    if(this.map.canMoveTo(toCheck.getPosition().subtract(toCheck.getFacing().toUnitVector()))){
                        if(this.map.grasses.get(toCheck.getPosition().subtract(toCheck.getFacing().toUnitVector()))!=null){
                            this.map.replaceGrass(this.map.grasses.get((toCheck.getPosition().add(toCheck.getFacing().toUnitVector()))), this.map.generateGrass());
                        }
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
    }
}