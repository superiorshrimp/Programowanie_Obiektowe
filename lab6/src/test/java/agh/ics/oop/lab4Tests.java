package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class lab4Tests{
    @Test
    public void moveTest(){
        //pierwsza sytuacja
        String[] dirs = {"f", "b", "r", "l"};
        MoveDirection[] directions = new OptionsParser().parse(dirs);
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(map.animals.get(0).getPosition(), new Vector2d(2,3));
        assertEquals(map.animals.get(0).getFacing(), MapDirection.EAST);
        assertEquals(map.animals.get(1).getPosition(), new Vector2d(3,3));
        assertEquals(map.animals.get(1).getFacing(), MapDirection.WEST);
        //druga sytuacja
        String[] dirs1 = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions1 = new OptionsParser().parse(dirs1);
        RectangularMap map1 = new RectangularMap(10, 5);
        Vector2d[] positions1 = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine1 = new SimulationEngine(directions1, map1, positions1);
        engine1.run();
        assertEquals(map1.animals.get(0).getPosition(), new Vector2d(2,0));
        assertEquals(map1.animals.get(0).getFacing(), MapDirection.SOUTH);
        assertEquals(map1.animals.get(1).getPosition(), new Vector2d(3,5));
        assertEquals(map1.animals.get(1).getFacing(), MapDirection.NORTH);
    }
}