package agh.ics.oop;


public class SimulationEngine implements IEngine{
    public RectangularMap map;
    public MoveDirection[] directions;
    //public IWorldMap map;
    public SimulationEngine(MoveDirection[] directions, RectangularMap map, Vector2d[] locations){
        this.directions = directions;
        this.map = map;
        for(int i=0; i<locations.length; i++){
            Animal baby = new Animal(map, locations[i]);
            map.place(baby); // if jakis?
        }
    }
    public void run(){
        for(int i=0; i<this.directions.length; i++){
            Animal toCheck = this.map.animals.get(i%this.map.animals.size());
            if(this.directions[i]==MoveDirection.FORWARD){
                if(this.map.canMoveTo(toCheck.getPosition().add(toCheck.getFacing().toUnitVector()))){
                    toCheck.move(this.directions[i]);
                }
                else{
                    continue;
                }
            }
            else if(this.directions[i]==MoveDirection.BACKWARD){
                if(this.map.canMoveTo(toCheck.getPosition().subtract(toCheck.getFacing().toUnitVector()))){
                    toCheck.move(this.directions[i]);
                }
                else{
                    continue;
                }
            }
            else{
                toCheck.move(this.directions[i]);
            }
        }
        System.out.println(this.map.toString());
    }
}
