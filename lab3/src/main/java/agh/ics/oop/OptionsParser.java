package agh.ics.oop;

public class OptionsParser{
    public OptionsParser(){}
    public MoveDirection[] parse(String[] path){
        MoveDirection[] tab = new MoveDirection[path.length];
        int len = path.length;
        for(int i=0; i<path.length; i++){
            switch (path[i]) {
                case "f", "forward" -> tab[i] = MoveDirection.FORWARD;
                case "b", "backward" -> tab[i] = MoveDirection.BACKWARD;
                case "l", "left" -> tab[i] = MoveDirection.LEFT;
                case "r", "right" -> tab[i] = MoveDirection.RIGHT;
                default -> {
                    tab[i] = null;
                    len--;
                }
            }
        }
        MoveDirection[] ret = new MoveDirection[len];
        for(int i=0; i<len; i++){
            ret[i] = tab[i];
        }
        return ret;
    }
}
