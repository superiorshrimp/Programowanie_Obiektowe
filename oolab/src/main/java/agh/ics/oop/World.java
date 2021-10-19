package agh.ics.oop;

public class World {
    public static void main(String[] args){
        System.out.println("system wystartował");
        int n = args.length;
        Direction[] T = new Direction[n];
        for(int i = 0; i<n; i++) {
            char c = args[i].charAt(0);
            switch (c) {
                case 'b' -> T[i] = Direction.BACKWARD;
                case 'l' -> T[i] = Direction.LEFT;
                case 'r' -> T[i] = Direction.RIGHT;
                default -> T[i] = Direction.FORWARD; //nieprawidłowe wejście
            }
        }
        run(T);
        System.out.println("system zakończył działanie");
    }
    public static void run(Direction[] T) {
        for (Direction direction : T) {
            switch (direction) {
                case FORWARD -> System.out.println("zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("zwierzak idzie do tyłu");
                case LEFT -> System.out.println("zwierzak skręca w lewo");
                case RIGHT -> System.out.println("zwierzak skręca w prawo");
                default -> System.out.print("");
            }
        }
        System.out.println();
    }
}