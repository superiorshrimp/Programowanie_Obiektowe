package agh.ics.oop;

public class World{
    public static void main(String[] args){
        String[] tab = new String[args.length];
        for(int i=0; i<args.length; i++){
            tab[i] = args[i];
        }
        Animal doggo = new Animal();
        OptionsParser inputHere = new OptionsParser();
        MoveDirection[] path = inputHere.parse(tab);
        for(MoveDirection dir: path){
            doggo.move(dir);
        }
        System.out.println(doggo);
    }
}
