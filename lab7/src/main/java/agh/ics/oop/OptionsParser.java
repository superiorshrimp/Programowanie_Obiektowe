package agh.ics.oop;

public class OptionsParser{
    public OptionsParser(){}
    public MoveDirection[] parse(String[] path){
        MoveDirection[] tab = new MoveDirection[path.length];
        int len = path.length;
        for(int i=0; i<path.length; i++){
            switch (path[i]) {
                case "f":
                case "forward": {
                    tab[i] = MoveDirection.FORWARD;
                    break;
                }
                case "b":
                case"backward": {
                    tab[i] = MoveDirection.BACKWARD;
                    break;
                }
                case "l":
                case "left": {
                    tab[i] = MoveDirection.LEFT;
                    break;
                }
                case "r":
                case "right": {
                    tab[i] = MoveDirection.RIGHT;
                    break;
                }
                default: {
                    throw new IllegalArgumentException("At least one argument is invalid!");
                }
            }
        }
        MoveDirection[] ret = new MoveDirection[len];
        for(int i=0; i<len; i++){ //linkedlista byla by lepsza
            ret[i] = tab[i];
        }
        return ret;
    }
}