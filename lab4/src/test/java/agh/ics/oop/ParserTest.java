package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest{
    @Test
    public void facingTest(){
        Animal sloth = new Animal();
        assertEquals(sloth.getFacing(), MapDirection.NORTH);
        sloth.move(MoveDirection.RIGHT);
        assertEquals(sloth.getFacing(), MapDirection.EAST);
        sloth.move(MoveDirection.BACKWARD);
        assertEquals(sloth.getFacing(), MapDirection.EAST);
        sloth.move(MoveDirection.FORWARD);
        assertEquals(sloth.getFacing(), MapDirection.EAST);
        sloth.move(MoveDirection.LEFT);
        assertEquals(sloth.getFacing(), MapDirection.NORTH);
    }
    @Test
    public void positionTest(){
        Animal sloth = new Animal();
        assertEquals(sloth.getPosition(), new Vector2d(2,2));
        sloth.move(MoveDirection.FORWARD);
        assertEquals(sloth.getPosition(), new Vector2d(2,3));
        sloth.move(MoveDirection.RIGHT);
        sloth.move(MoveDirection.FORWARD);
        assertEquals(sloth.getPosition(), new Vector2d(3,3));
        sloth.move(MoveDirection.LEFT);
        sloth.move(MoveDirection.BACKWARD);
        assertEquals(sloth.getPosition(), new Vector2d(3,2));
    }
    @Test
    public void outOfBordersTest(){
        Animal sloth = new Animal();
        sloth.move(MoveDirection.FORWARD);
        sloth.move(MoveDirection.FORWARD);
        sloth.move(MoveDirection.FORWARD);
        System.out.println(sloth.getPosition());
        assertEquals(sloth.getPosition(), new Vector2d(2,4));
        sloth.move(MoveDirection.RIGHT);
        sloth.move(MoveDirection.FORWARD);
        sloth.move(MoveDirection.FORWARD);
        sloth.move(MoveDirection.FORWARD);
        assertEquals(sloth.getPosition(), new Vector2d(4,4));
        Animal theirChild = new Animal();
        theirChild.move(MoveDirection.BACKWARD);
        theirChild.move(MoveDirection.BACKWARD);
        theirChild.move(MoveDirection.BACKWARD);
        assertEquals(theirChild.getPosition(), new Vector2d(2,0));
        theirChild.move(MoveDirection.LEFT);
        theirChild.move(MoveDirection.FORWARD);
        theirChild.move(MoveDirection.FORWARD);
        theirChild.move(MoveDirection.FORWARD);
        assertEquals(theirChild.getPosition(), new Vector2d(0,0));
    }
    @Test
    public void parsingTest(){
        OptionsParser input = new OptionsParser();
        Animal monke = new Animal();
        String[] move1 = {"r", "f", "forward", "f", "forward", "f", "forward", "f", "forward", "dgfsdg", "fs"};
        MoveDirection[] t1 = input.parse(move1);
        for(int i=0; i<t1.length; i++){
            monke.move(t1[i]);
        }
        assertEquals(monke.getPosition(), new Vector2d(4,2));
        assertEquals(monke.getFacing(), MapDirection.EAST);
        String[] move2 = {"b", "backward"};
        MoveDirection[] t2 = input.parse(move2);
        for(int i=0; i<t2.length; i++){
            monke.move(t2[i]);
        }
        assertEquals(monke.getPosition(), new Vector2d(2,2));
        String[] move3 = {"f", "forward"};
        MoveDirection[] t3 = input.parse(move3);
        for(int i=0; i<t3.length; i++){
            monke.move(t3[i]);
        }
        assertEquals(monke.getPosition(), new Vector2d(4,2));
        String[] move4 = {"rthys", "fd", "gdf4", "1", "t"};
        MoveDirection[] t4 = input.parse(move4);
        for(int i=0; i<t4.length; i++){
            monke.move(t4[i]);
        }
        assertEquals(monke.getPosition(), new Vector2d(4,2));
        String[] move5 = {"l", "left", "forward"};
        MoveDirection[] t5 = input.parse(move5);
        for(int i=0; i<t5.length; i++){
            monke.move(t5[i]);
        }
        assertEquals(monke.getPosition(), new Vector2d(3,2));
    }
}