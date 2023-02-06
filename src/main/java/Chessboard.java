import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class LInfo {
    public final int row;
    public final int column;

    public LInfo(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

public class Chessboard extends Application {
    private final int size = 8;
    private int width = 90;
    private int height = 90;
    private int boardWH = 750;
    private int vezPosRow = 7;
    private int vezPosCol = 0;


    @Override
    public void start(Stage stage) {

        GridPane grid = new GridPane();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Label square = new Label("" );
                square.setUserData(new LInfo(row, col));
                square.setMinSize(width, height);
                square.setMaxSize(width, height);
                square.setStyle("-fx-background-color: " + (((row + col) % 2 == 0) ? "white" : "grey") +
                        "; -fx-border-color: black; -fx-border-width: 2;");
                grid.add(square, col, row);
                if (row == vezPosRow && col == vezPosCol) {
                    Image vez = new Image("vez.png");
                    ImageView iw = new ImageView(vez);
                    iw.setFitWidth(this.width - 10);
                    iw.setFitHeight(this.height - 10);
                    square.setGraphic(iw);

                    iw.setOnDragDetected(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            System.out.println("detected");
                            Dragboard db = iw.startDragAndDrop(TransferMode.ANY);
                            ClipboardContent content = new ClipboardContent();
                            content.putImage(vez);
                            db.setContent(content);
                            mouseEvent.consume();
                        }
                    });

                }
            }
        }



        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid, boardWH, boardWH);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
