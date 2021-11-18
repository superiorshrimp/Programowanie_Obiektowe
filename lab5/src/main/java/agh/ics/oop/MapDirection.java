package agh.ics.oop;

public enum MapDirection{
    NORTH,
    EAST,
    SOUTH,
    WEST;
    public String toString(){
        switch(this) {
            case NORTH: return "N";
            case EAST: return "E";
            case SOUTH: return "S";
            default: return "W"; //west
        }
    }
    public MapDirection next(){
        switch(this){
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            default: return NORTH; //west
        }
    }
    public MapDirection previous(){
        switch(this){
            case NORTH: return WEST;
            case EAST: return NORTH;
            case SOUTH: return EAST;
            default: return SOUTH; //west
        }
    }
    public Vector2d toUnitVector(){
        switch(this){
            case NORTH: return new Vector2d(0,1);
            case EAST: return new Vector2d(1,0);
            case SOUTH: return new Vector2d(0,-1);
            default: return new Vector2d(-1,0); //west
        }
    }
}