package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

public class App extends Application{
    protected GridPane board = new GridPane();
    protected Scene scene = new Scene(board, 400, 400);
    protected GrassField map;
    protected Vector2d bottomLeft;
    protected Vector2d upperRight;
    protected int moveDelay;
    public void init() throws InterruptedException {
        String[] args = getParameters().getRaw().toArray(new String[0]); // var
        MoveDirection[] directions = new OptionsParser().parse(args);
        GrassField map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        Thread engineThread = new Thread(engine);
        engineThread.start();
        this.moveDelay = 3000;
        this.map = map;
        this.upperRight = new Vector2d(engine.boundaries.xMax, engine.boundaries.yMax);
        this.bottomLeft = new Vector2d(engine.boundaries.xMin, engine.boundaries.yMin);
    }
    public void start(Stage primaryStage) throws Exception{
        board.getColumnConstraints().add(new ColumnConstraints(20));
        board.add( new Label ("y/x"), 0, 0);

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
                        switch(map.objectAt(toCheck).toString()){ //TODO: ano żeby działało
                            case "N": GuiElementBox up = new GuiElementBox("src/main/resources/up.png"); toAdd.setGraphic(up.imageView); break;
                            case "S": GuiElementBox down = new GuiElementBox("src/main/resources/down.png");toAdd.setGraphic(down.imageView); break;
                            case "W": GuiElementBox left = new GuiElementBox("src/main/resources/left.png");toAdd.setGraphic(left.imageView); break;
                            case "E": GuiElementBox right = new GuiElementBox("src/main/resources/right.png");toAdd.setGraphic(right.imageView); break;
                        }
                    }
                    board.add(toAdd, i, j);
                    GridPane.setHalignment(toAdd, HPos.CENTER);
                } else board.add(new Label (" "),i, j);
            }
        }
        board.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
