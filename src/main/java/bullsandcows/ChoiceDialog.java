/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 *
 * @author Дарьюшка Windows 10
 */
public class ChoiceDialog {
    private Dialog<BullsCows> dialog;
    private ScrollPane sp;
            
    public ChoiceDialog(Stage primaryStage, String s) {//диалог выбора типа игры
        dialog = new Dialog<>();
        dialog.setTitle("Bulls and Cows");
        dialog.setHeaderText(s + "Выберите, пожалуйста, тип игры:");
        createButtons(primaryStage);
    }
    private void createButtons(Stage primaryStage) {//создание кнопок для диалога
        ButtonType buttonTypeOk1 = new ButtonType("человек-человек", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk1); 
        ButtonType buttonTypeOk2 = new ButtonType("человек-компьютер", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk2);
        ButtonType buttonTypeCancel = new ButtonType("выход", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);
        dialog.setResultConverter((ButtonType b) -> {
            if (b == buttonTypeOk1) {
                System.out.println("1");//выбор чел-чел
                gameFieid(primaryStage, 1);
            }
            if (b == buttonTypeOk2) {
                System.out.println("2");//выбор чел-комп
                gameFieid(primaryStage, 2);
            }
            return null;
        });
    }
    
    private void gameFieid(Stage primaryStage, int n){
        MenuBar menu = new MenuBar();//создание меню
        menu.setPadding(new Insets(0, 0, 0, 18));
        menu.getMenus().add(createFileMenu(primaryStage));
        MenuBar menu2 = new MenuBar();
        menu2.getMenus().add(createHelpMenu());
        VBox menuV = new VBox(10);
        menuV.getChildren().addAll(menu, menu2);
        menuV.setPadding(new Insets(10));
        
        switch(n){
           case 1://при выборе чел-чел
                BullsCows bc;
                bc = new BullsCows("Игрок 1", "Игрок 2");
                HBox root1 = new HBox(10);
                root1.setAlignment(Pos.TOP_CENTER);
                View viewBC = new View(bc);                
                root1.getChildren().addAll(menuV, viewBC);                           
                Scene scene1 = new Scene(root1, 650, 620);
                sp = new ScrollPane();
                sp.setContent(viewBC);
                root1.getChildren().add(sp); 
                primaryStage.setResizable(false);
                primaryStage.setScene(scene1);
                primaryStage.show();
            break;
            case 2://при выборе чел-комп
                BullsCowsComp bcComp;
                bcComp = new BullsCowsComp("Игрок"); 
                HBox root2 = new HBox(10);
                root2.setAlignment(Pos.TOP_CENTER);
                ViewComp viewBCComp = new ViewComp(bcComp);
                root2.getChildren().addAll(menuV, viewBCComp);                           
                Scene scene2 = new Scene(root2, 650, 620);
                sp = new ScrollPane();
                sp.setContent(viewBCComp);
                root2.getChildren().add(sp); 
                primaryStage.setResizable(false);
                primaryStage.setScene(scene2);
                primaryStage.show();
            break;
            default: return;
        }
    }
    
    private Menu createFileMenu(Stage primaryStage) {//раздел меню "игра"
        Menu menuFile = new Menu("Игра"); 
        MenuItem gameNew = new MenuItem("Новая игра");
        gameNew.setOnAction((ActionEvent t) -> {
            ChoiceDialog dialog = new ChoiceDialog(primaryStage," ");
              //  "                                                       ");
            dialog.getDialog().showAndWait();
        });
        
        MenuItem exit = new MenuItem("Выход");
        exit.setOnAction((ActionEvent t) -> {
            Platform.exit();
        });
        MenuItem info = new MenuItem("Правила игры");
        info.setOnAction((ActionEvent t) -> {
            info();
        });
        menuFile.getItems().addAll(gameNew, info, new SeparatorMenuItem(), exit);
        return menuFile;
    }
    
    private Menu createHelpMenu() {//раздел меню"справка"
        Menu help = new Menu("Справка"); 
        MenuItem prog = new MenuItem("О программе");
        prog.setOnAction((ActionEvent t) -> {
            prog();
        });
        help.getItems().addAll(prog);
        return help;
    }
    private static void info(){//правила игры
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bulls and Cows");
        alert.setHeaderText("Правила игры");
        alert.setContentText("Каждый из противников задумывает четырехзначное чис" +
"ло, все цифры которого различны (первая цифра числа отлична " +
"от нуля). Необходимо разгадать задуманное число. Выигрыва" +
"ет тот, кто отгадает первый. Противники по очереди называют " +
"друг другу числа и сообщают о количестве «быков» и «коров» " +
"в названном числе («бык» — цифра есть в записи задуманного " +
"числа и стоит в той же позиции, что и в задуманном числе; «ко" +
"рова» — цифра есть в записи задуманного числа, но не стоит в " +
"той же позиции, что и в задуманном числе).");
        alert.showAndWait();
    }
    private static void prog(){//о программе
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bulls and Cows");
        alert.setHeaderText("Логическая игра \"Быки-Коровы\"");
        alert.setContentText("Версия 1.4\n©Грушевская Дарья\n"
                + "(студентка ЯрГУ ИВТ ИТ-21БО),\n2016");
        alert.showAndWait();
    }
     public Dialog<BullsCows>  getDialog() {
        return dialog;
    }
}
