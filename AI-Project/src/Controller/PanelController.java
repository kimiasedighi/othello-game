package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PanelController {
    public static String p1_name , p2_name;
    public TextField player2;
    public TextField player1;
    public RadioButton pp , pa , aa;

    public void startGame(ActionEvent actionEvent) throws IOException {
        p1_name = player1.getText();
        p2_name = player2.getText();
        if (pp.isSelected()) {
            new PageLoader().load("../View/Game.fxml");
        }
        else if (pa.isSelected()) {
            new PageLoader().load("../View/GameWithAI.fxml");
        }
    }
}
