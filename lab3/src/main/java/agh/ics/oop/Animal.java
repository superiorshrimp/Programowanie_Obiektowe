package agh.ics.oop;

public class Animal{
    private MapDirection facing;
    private Vector2d position;
    public Animal(){
        this.facing = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }
    public String toString(){
        return this.facing.toString() + this.position.toString();
    }
    public void move(MoveDirection direction){
        switch (direction) {
            case LEFT -> this.facing = this.facing.previous();
            case RIGHT -> this.facing = this.facing.next();
            case FORWARD -> {
                if (this.position.add(this.facing.toUnitVector()).precedes(new Vector2d(4, 4)) && this.position.add(this.facing.toUnitVector()).follows(new Vector2d(0, 0))) {
                    this.position = this.position.add(this.facing.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (this.position.subtract(this.facing.toUnitVector()).precedes(new Vector2d(4, 4)) && this.position.subtract(this.facing.toUnitVector()).follows(new Vector2d(0, 0))) {
                    this.position = this.position.subtract(this.facing.toUnitVector());
                }
            }
        }
    }
}
