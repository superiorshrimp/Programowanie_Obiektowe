package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection facing;
    public Animal(Vector2d initialPosition){
        super(new Vector2d(initialPosition.x, initialPosition.y));
        this.facing = MapDirection.NORTH;
    }
    public MapDirection getFacing(){return this.facing;}
    public Vector2d getPosition(){return super.position;}
    public boolean isAt(Vector2d loc){return super.position.equals(loc);}
    public String toString(){
        return this.facing.toString();
    }
    public void move(MoveDirection direction){
        switch (direction) {
            case LEFT: {
                this.facing = this.facing.previous();
                break;
            }
            case RIGHT: {
                this.facing = this.facing.next();
                break;
            }
            case FORWARD: {
                Vector2d oldPos = super.position;
                super.position = oldPos.add(this.facing.toUnitVector());
                super.positionChanged(oldPos, super.position);
                break;
            }
            case BACKWARD: {
                Vector2d oldPos = super.position;
                super.position = oldPos.subtract(this.facing.toUnitVector());
                super.positionChanged(oldPos, super.position);
                break;
            }
        }
    }
}