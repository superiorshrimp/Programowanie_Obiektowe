package agh.ics.oop;

public class World{
    public static void main(String[] args){
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        //RectangularMap map = new RectangularMap(5,5);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }
}
