package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Vector2dTest{
    @Test
    public void equalsTest(){
        assertTrue((new Vector2d(56,43)).equals(new Vector2d(56,43)));
    }
    @Test
    public void toStringTest(){
        assertEquals((new Vector2d(56,43)).toString(), "(56,43)");
    }
    @Test
    public void precedesTest(){
        assertTrue((new Vector2d(56,43)).precedes(new Vector2d(100,100)));
    }
    @Test
    public void followsTest(){
        assertTrue((new Vector2d(56,43)).follows(new Vector2d(10,10)));;
    }
    @Test
    public void upperRightTest(){
        assertEquals((new Vector2d(56,43)).upperRight(new Vector2d(100,10)), new Vector2d(100,43));
    }
    @Test
    public void lowerLeftTest(){
        assertEquals((new Vector2d(56,43)).lowerLeft(new Vector2d(100,10)), new Vector2d(56,10));
    }
    @Test
    public void addTest(){
        assertEquals((new Vector2d(56,43)).add(new Vector2d(100,10)), new Vector2d(156,53));
    }
    @Test
    public void subtractTest(){
        assertEquals((new Vector2d(56,43)).subtract(new Vector2d(100,10)), new Vector2d(-44,33));
    }
    @Test
    public void oppositeTest(){
        assertEquals((new Vector2d(56,43)).opposite(), new Vector2d(-56,-43));
    }
}
