/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Дарьюшка Windows 10
 */
public class View extends Group
        implements Listener  {
    private BullsCows bc;
    private GridPane grid;
    private Label name1;
    private Label name2;
    private Label message;
    private Label number1;
    private Label number2;
    private Font font;
    private int count;
    
    private void createPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label titleGame = new Label("Игра \"Быки-Коровы\"");
        titleGame.setFont(font);
        titleGame.setPrefSize(450, 40);
        titleGame.setAlignment(Pos.CENTER);
        grid.add(titleGame, 0, 0, 2, 1);
        
        name1 = new Label(bc.getName1());
        name1.setFont(font);
        name1.setPrefSize(220, 40);
        name1.setAlignment(Pos.CENTER);
        name1.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(name1, 0, 5, 1, 1);

        name2 = new Label(bc.getName2());
        name2.setFont(font);
        name2.setPrefSize(220, 40);
        name2.setAlignment(Pos.CENTER);
        name2.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(name2, 1, 5, 1, 1);
        
        for(int i = 6; i < 13; i++){//раскраска строк, чтобы сетка не "скакала"
            Label label = new Label(" ");
            label.setPrefSize(220, 30);
            label.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
            grid.add(label, 0, i);
            Label label2 = new Label(" ");
            label2.setPrefSize(220, 30);
            label2.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
            grid.add(label2, 1, i);
        }
        Label l = new Label(" ");
        l.setPrefSize(220, 30);
        l.setBackground(new Background(new BackgroundFill(Color.rgb(56, 217, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(l, 0, 4, 1, 1);
        Label l2 = new Label(" ");
        l2.setPrefSize(220, 30);
        l2.setBackground(new Background(new BackgroundFill(Color.rgb(56, 217, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(l2, 1, 4, 1, 1);
        
        count = -1;

        //grid.setGridLinesVisible(true);//отображение сетки
    }
    
    public View(BullsCows bc) { 
        this.bc = bc;
        this.bc.addListener(this);
        font = Font.font("Tahoma", FontWeight.NORMAL, 20);
        createPane();
        dataChanged();
        this.getChildren().add(grid);
    }
    
    @Override
    public void dataChanged(){//изменение вида при изменении модели
        switch (bc.getAction()){
            case 1: scoreboard1(); break;
            case 2: scoreboard2(); break;
            case 3: scoreboard3(); break;
            case 4: scoreboard4(); break;
            case 5: scoreboard3(); break;
            case 6: scoreboard5(); break;
            case 7: scoreboard6(); break;
        }
        switch(bc.getAction()){            
            case 2:
                number1 = new Label("????");
                number1.setFont(font);
                number1.setPrefSize(220, 30);
                number1.setAlignment(Pos.CENTER);
                grid.add(number1, 1, 4);   
                break;
            case 3:
                number2 = new Label("????");
                number2.setFont(font);
                number2.setPrefSize(220, 30);
                number2.setAlignment(Pos.CENTER);                
                grid.add(number2, 0, 4);   
                break;
            case 4:
                Label text2 = new Label(bc.getList1().get(1+count).toString() + " — " + bc.bulls(1, 1+count) + " б., " + bc.cows(1, 1+count) + " к.");
                text2.setFont(font);
                text2.setPrefSize(220, 30);
                text2.setAlignment(Pos.CENTER);
                text2.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
                grid.add(text2, 0, 6+count); 
                Label label = new Label(" ");
                label.setPrefSize(220, 30);
                label.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
                grid.add(label, 1, 6+count);
                break;
            case 5:
                Label text3 = new Label(bc.getList2().get(count).toString() + " — " + bc.bulls(2, count)+ " б., " + bc.cows(2, count) + " к.");
                text3.setFont(font);
                text3.setPrefSize(220, 30);
                text3.setAlignment(Pos.CENTER);
                grid.add(text3, 1, 5+count);   
                break;
            case 6:
                Label text4 = new Label(bc.getList1().get(1+count).toString() + " — " + bc.bulls(1, 1+count) + " б., " + bc.cows(1, 1+count) + " к.");
                text4.setFont(font);
                text4.setPrefSize(220, 30);
                text4.setAlignment(Pos.CENTER);
                text4.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
                grid.add(text4, 0, 6+count); 
                Label label2 = new Label(" ");
                label2.setPrefSize(220, 30);
                label2.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
                grid.add(label2, 1, 6+count);
                break;
            case 7:
                Label text5 = new Label(bc.getList2().get(1+count).toString() + " — " + bc.bulls(2, 1+count)+ " б., " + bc.cows(2, 1+count) + " к.");
                text5.setFont(font);
                text5.setPrefSize(220, 30);
                text5.setAlignment(Pos.CENTER);
                grid.add(text5, 1, 6+count);   
                break;
        }
    }
    
    private void scoreboard1(){//ввод загаданного числа 1 игроком
        message = new Label(bc.getName1()+", введите загаданное Вами число:");
        message.setFont(font);
        message.setPrefSize(450, 40);
        message.setAlignment(Pos.CENTER);
        message.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(message, 0, 1, 2, 1);
        PasswordField password = new PasswordField();
        password.setFont(font);
        grid.add(password, 0, 2, 2, 1);
        Button btn = new Button();
        btn.setText("Верно!");
        btn.setFont(font);
        grid.setMargin(btn, new Insets(0.0, 0.0, 0.0, 177.5));
        grid.add(btn, 0, 3, 2 ,1);
        password.setOnKeyPressed(k -> {
                if (k.getCode().equals(KeyCode.ENTER)){ 
                    if(check(password.getText())){
                        bc.addNumber1(password.getText());
                        bc.setAction(2);
                        grid.getChildren().remove(password);
                        grid.getChildren().remove(btn);
                    }
                    else{
                        alert();
                    }
                }            
        });        
        btn.setOnKeyPressed(k -> {
                if (k.getCode().equals(KeyCode.ENTER)){    
                    if(check(password.getText())){
                        bc.addNumber1(password.getText());
                        bc.setAction(2);
                        grid.getChildren().remove(password);
                        grid.getChildren().remove(btn);
                    }
                    else{
                        alert();
                    }
                }            
        });
        btn.setOnAction((ActionEvent event) -> {
            if(check(password.getText())){ 
                bc.addNumber1(password.getText());
                bc.setAction(2);
                grid.getChildren().remove(password);
                grid.getChildren().remove(btn);
            }
            else{
                alert();
            }
        });
        
    }
    private void scoreboard2(){//ввод загаданного числа 2 игроком
        message.setText(bc.getName2()+", введите загаданное Вами число:");
        PasswordField password = new PasswordField();
        password.setFont(font);
        password.requestFocus();
        grid.add(password, 0, 2, 2, 1);        
        Button btn = new Button();
        btn.setText("Верно!");
        btn.setFont(font);
        grid.setMargin(btn, new Insets(0.0, 0.0, 0.0, 177.5));
        grid.add(btn, 0, 3, 2 ,1);
        password.setOnKeyPressed(k -> {
                if (k.getCode().equals(KeyCode.ENTER)){
                    if(check(password.getText())){
                        bc.addNumber2(password.getText());
                        bc.setAction(3);
                        grid.getChildren().remove(password);
                        grid.getChildren().remove(btn);
                    }
                    else{
                        alert();
                    }
                }            
        });
        btn.setOnAction((ActionEvent event) -> {
            if(check(password.getText())){
                bc.addNumber2(password.getText());
                bc.setAction(3);
                grid.getChildren().remove(password);
                grid.getChildren().remove(btn);
            }
            else{
                alert();
            }    
        });
        btn.setOnKeyPressed(k -> {
                if (k.getCode().equals(KeyCode.ENTER)){    
                    if(check(password.getText())){
                        bc.addNumber2(password.getText());
                        bc.setAction(3);
                        grid.getChildren().remove(password);
                        grid.getChildren().remove(btn);
                    }
                    else{
                        alert();
                    }
                }            
        });
    }
    private void scoreboard3(){//ввод предполагаемого числа 1 игроком
        count++;  
        message.setText(bc.getName1()+", введите число:");
        TextField textF = new TextField();
        textF.setFont(font);
        grid.add(textF, 0, 2, 2, 1);        
        Button btn = new Button();
        btn.setText("Верно!");
        btn.setFont(font);
        grid.setMargin(btn, new Insets(0.0, 0.0, 0.0, 177.5));
        grid.add(btn, 0, 3, 2 ,1);
        textF.setOnKeyPressed(k -> {
                if (k.getCode().equals(KeyCode.ENTER)){ 
                    if(check(textF.getText())){
                        bc.addNumber1(textF.getText());
                        if(!bc.getList2().get(0).equals(bc.getList1().get(bc.getList1().size()-1))){
                            bc.setAction(4);
                        }
                        else{
                            bc.setAction(6);
                        }
                        grid.getChildren().remove(textF);
                        grid.getChildren().remove(btn);
                    }
                    else{
                        alert();
                    }
                }            
        }); 
        btn.setOnKeyPressed(k -> {
                if (k.getCode().equals(KeyCode.ENTER)){    
                    if(check(textF.getText())){
                        bc.addNumber1(textF.getText());
                        if(!bc.getList2().get(0).equals(bc.getList1().get(bc.getList1().size()-1))){
                            bc.setAction(4);
                        }
                        else{
                            bc.setAction(6);
                        }
                        grid.getChildren().remove(textF);
                        grid.getChildren().remove(btn);
                    }
                    else{
                        alert();
                    }
                }            
        });
        btn.setOnAction((ActionEvent event) -> {
            if(check(textF.getText())){
            bc.addNumber1(textF.getText());
                if(!bc.getList2().get(0).equals(bc.getList1().get(bc.getList1().size()-1))){
                    bc.setAction(4);
                }
                else{
                    bc.setAction(6);
                }
                grid.getChildren().remove(textF);
                grid.getChildren().remove(btn);
            }
            else{
                alert();
            }
        }); 
            
    }
    private void scoreboard4(){//ввод предполагаемого числа 2 игроком
        message.setText(bc.getName2()+", введите число:");
        TextField textF = new TextField();
        textF.setFont(font);
        grid.add(textF, 0, 2, 2, 1);       
        Button btn = new Button();
        btn.setText("Верно!");
        btn.setFont(font);
        grid.setMargin(btn, new Insets(0.0, 0.0, 0.0, 177.5));
        grid.add(btn, 0, 3, 2 ,1);
        textF.setOnKeyPressed(k -> {
                if (k.getCode().equals(KeyCode.ENTER)){    
                    if(check(textF.getText())){
                        bc.addNumber2(textF.getText());
                        if(!bc.getList1().get(0).equals(bc.getList2().get(bc.getList2().size()-1))){
                            bc.setAction(5);
                        }
                        else{
                            bc.setAction(7);
                        }
                        grid.getChildren().remove(textF);
                        grid.getChildren().remove(btn);
                    }
                    else{
                        alert();
                    }
                }            
        }); 
        btn.setOnKeyPressed(k -> {
                if (k.getCode().equals(KeyCode.ENTER)){    
                    if(check(textF.getText())){
                        bc.addNumber2(textF.getText());
                        if(!bc.getList1().get(0).equals(bc.getList2().get(bc.getList2().size()-1))){
                            bc.setAction(5);
                        }
                        else{
                            bc.setAction(7);
                        }
                        grid.getChildren().remove(textF);
                        grid.getChildren().remove(btn);
                    }
                    else{
                        alert();
                    }
                }            
        });
        btn.setOnAction((ActionEvent event) -> {
            if(check(textF.getText())){ 
                bc.addNumber2(textF.getText());
                if(!bc.getList1().get(0).equals(bc.getList2().get(bc.getList2().size()-1))){
                    bc.setAction(5);
                }
                else{
                    bc.setAction(7);
                }
                grid.getChildren().remove(textF);
                grid.getChildren().remove(btn);
            }
            else{
                alert();
            }
        });
    }
     private void scoreboard5(){//вывод победного поздравления
        message.setText(bc.getName1()+", Вы победили!!!");
        number1.setText(bc.getList1().get(0).toString());
        number2.setText(bc.getList2().get(0).toString());
        Label text = new Label(bc.getName2() + " загадал: " + bc.getList2().get(0).toString());
        text.setFont(font);
        text.setPrefSize(450, 40);
        text.setAlignment(Pos.CENTER);
        text.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(text, 0, 2, 2, 1);   
        Label message2 = new Label("");
        message2.setPrefSize(450, 30);
        grid.add(message2, 0, 3, 2, 1); 
     }
     private void scoreboard6(){//вывод победного поздравления
        message.setText(bc.getName2()+", Вы победили!!!");
        number1.setText(bc.getList1().get(0).toString());
        number2.setText(bc.getList2().get(0).toString());        
        Label text = new Label(bc.getName1() + " загадал: " + bc.getList1().get(0).toString());
        text.setFont(font);
        text.setPrefSize(450, 40);
        text.setAlignment(Pos.CENTER);
        text.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(text, 0, 2, 2, 1); 
        Label message2 = new Label("");
        message2.setPrefSize(450, 30);
        grid.add(message2, 0, 3, 2, 1); 
     }
     private static boolean check(String s){
             if(s.matches("[1-9][0-9][0-9][0-9]")){
                int number = Integer.parseInt(s);
                int listN[]= new int[4];
                for(int i = 3; i >= 0; i--){
                    listN[i] = number % 10;
                    number/=10;
                }
                if(listN[0] != listN[1] && listN[2] != listN[3]
                        && listN[0] != listN[2] && listN[1] != listN[3]
                        && listN[0] != listN[3] && listN[2] != listN[1]){
                    return true;
                }
                else
                    return false;                 
             }
             return false;
     }
    private static void alert(){//сообщение об ошибке при вводе числа
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bulls and Cows");
        alert.setHeaderText("Вы допустили ошибку!");
        alert.setContentText("Перечитайте правила игры");
        alert.showAndWait();
    }
}
