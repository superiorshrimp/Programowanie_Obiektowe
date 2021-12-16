package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

import java.util.Arrays;

public class App extends Application{
    protected GrassField map;
    protected int moveDelay;
    protected Vector2d[] positions;
    protected MoveDirection[] directions;
    public void init() throws InterruptedException{
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        this.directions = directions;
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        this.positions = positions;
        this.moveDelay = 300;
        this.map = map;
    }
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Animals tracker");
        Button button = new Button("Press the button to start the simulation");
        button.setOnAction(action -> {
            MapBoundary boundaries = new MapBoundary(this.map);
            for(int i=0; i<positions.length; i++){
                Animal baby = new Animal(positions[i]);
                if(!this.map.place(baby)) {
                    throw new IllegalArgumentException("Animal can't be placed since another is in the same location!");
                }
                baby.addObserver(this.map);
                baby.addObserver(boundaries);
            }
            for(int l = 0; l<this.directions.length/positions.length; l++){
                SimulationEngine engine = new SimulationEngine(Arrays.copyOfRange(this.directions,2*l, 2*(l+1)), this.map, boundaries);
                engine.run();
                Vector2d upperRight = new Vector2d(boundaries.xMax, boundaries.yMax);
                Vector2d bottomLeft = new Vector2d(boundaries.xMin, boundaries.yMin);
                GridPane board = new GridPane();
                Scene scene = new Scene(board, 800, 800);
                board.getColumnConstraints().add(new ColumnConstraints(20));
                board.add( new Label("y/x"), 0, 0);

                for(int i = 1; i <= upperRight.x - bottomLeft.x + 1; i++){
                    Label axisLabel = new Label(Integer.toString(i + bottomLeft.x - 1));
                    board.getColumnConstraints().add(new ColumnConstraints(20));
                    board.add(axisLabel, i, 0);
                    GridPane.setHalignment(axisLabel, HPos.CENTER);
                }

                for(int j = 1; j <= upperRight.y - bottomLeft.y + 1; j++){
                    Label axisLabel = new Label(Integer.toString(upperRight.y - j + 1));
                    board.add(axisLabel, 0, j);
                    board.getRowConstraints().add(new RowConstraints(20));
                    GridPane.setHalignment(axisLabel, HPos.CENTER);
                }

                for(int i = 1; i <= upperRight.x - bottomLeft.x + 1; i++) {
                    for(int j = 1; j <= upperRight.y - bottomLeft.y + 1; j++){
                        Vector2d toCheck = new Vector2d(i + bottomLeft.x - 1,upperRight.y - j + 1);
                        if(map.objectAt(toCheck)!=null){
                            Label toAdd = new Label();
                            if(map.objectAt(toCheck) instanceof Grass){
                                GuiElementBox grass = new GuiElementBox("src/main/resources/grass.png");
                                toAdd.setGraphic(grass.imageView);
                            }
                            else{
                                switch(map.objectAt(toCheck).toString()){
                                    case "N": GuiElementBox up = new GuiElementBox("src/main/resources/up.png"); toAdd.setGraphic(up.imageView); break;
                                    case "S": GuiElementBox down = new GuiElementBox("src/main/resources/down.png");toAdd.setGraphic(down.imageView); break;
                                    case "W": GuiElementBox left = new GuiElementBox("src/main/resources/left.png");toAdd.setGraphic(left.imageView); break;
                                    case "E": GuiElementBox right = new GuiElementBox("src/main/resources/right.png");toAdd.setGraphic(right.imageView); break;
                                }
                            }
                            board.add(toAdd, i, j);
                            GridPane.setHalignment(toAdd, HPos.CENTER);
                        } else board.add(new Label (" "), i, j);
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.setGridLinesVisible(true);
                primaryStage.setScene(scene);
                //primaryStage.show();
            }
        });
        HBox hbox = new HBox(button);
        Scene mainMenu = new Scene(hbox, 200, 200);
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }
}
