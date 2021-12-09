package agh.ics.oop;

import java.util.Comparator;

public class XComparatorElements implements Comparator<Vector2d>{
    public XComparatorElements(){
        ;
    }
    public int compare(Vector2d a, Vector2d b){
        if(a.x > b.x){
            return 1;
        }
        else if(a.x < b.x){
            return -1;
        }
        else if(a.y > b.y){
            return 1;
        }
        else if(a.y < b.y){
            return -1;
        }
        else{
            return 0;
        }
    }
}
