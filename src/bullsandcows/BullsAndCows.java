/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;


/**
 *
 * @author Дарьюшка Windows 10
 */
public class BullsAndCows extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bulls and Cows");
        ChoiceDialog dialog = new ChoiceDialog(primaryStage, "Вас приветствует игра \"Быки-Коровы\". ");
        dialog.getDialog().showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
