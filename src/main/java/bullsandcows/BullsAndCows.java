package bullsandcows;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author �������� Windows 10
 */
public class BullsAndCows extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bulls and Cows");
        ChoiceDialog dialog = new ChoiceDialog(primaryStage, "��� ������������ ���� \"����-������\". ");
        dialog.getDialog().showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
