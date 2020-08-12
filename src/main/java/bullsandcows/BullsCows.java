/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Дарьюшка Windows 10
 */
public class BullsCows {
    private String name1; // Имя 1 игрока
    private String name2;//имя 2 игрока
    private int action;//номер, выполняемого действия
    private ArrayList<int[]> list1;//массив введенных чисел 1 игроком (цифрами)
    private ArrayList<int[]> list2;//массив введенных чисел 2 игроком (цифрами)
    private ArrayList<Integer> list1N;//массив введенных чисел 1 игроком (числами)
    private ArrayList<Integer> list2N;//массив введенных чисел 2 игроком (числами)
    private ArrayList<Listener> listeners;//массив слушателей
    
    public BullsCows(String name1, String name2) {//конструктор по умолчанию
        this.name1 = name1;
        this.name2 = name2;
        action = 1;
        list1 = new ArrayList<int[]>();
        list2 = new ArrayList<int[]>();
        list1N = new ArrayList<Integer>();
        list2N = new ArrayList<Integer>();
        listeners = new ArrayList<Listener>();
    }
    
    public String getName1(){
        return name1;
    }
    public String getName2(){
        return name2;
    }
    public ArrayList<Integer> getList1(){
        return list1N;
    }
    public ArrayList<Integer> getList2(){
        return list2N;
    }
    public int getAction(){
        return action;
    }
    
    public void setAction(int a){//информационное табло № action
        action = a;
        changed();
    }
    
    public void addListener(Listener l) {
        listeners.add(l);
    }
    
    public void addNumber1(String s){//добав. номер в списки 1иг.(разлож. на цифры/числа)
        int number = Integer.parseInt(s);
        int listN[]= new int[4];
        for(int i = 3; i >= 0; i--){
            listN[i] = number % 10;
            number /= 10;
        }
        list1.add(listN);
        list1N.add(Integer.valueOf(s));
    }
    public void addNumber2(String s){//добав. номер в списки 2иг.(разлож. на цифры/числа)
        int number = Integer.parseInt(s);
        int listN[] = new int[4];
        for(int i = 3; i >= 0; i--){
            listN[i] = number % 10;
            number/=10;
        }
        list2.add(listN);
        list2N.add(Integer.valueOf(s));
    }
    
    public int bulls(int numberL, int indexL){//подсчет быков
        int count = 0;
        switch (numberL){
            case 1:
                for(int i = 0; i < 4; i++){
                   if(list1.get(indexL)[i] == list2.get(0)[i]){
                       count++;
                   }
                }
                return count;
            case 2:
                for(int i = 0; i < 4; i++){
                   if(list2.get(indexL)[i] == list1.get(0)[i]){
                       count++;
                   }
                }
                return count;
        }
        return -10;
    }
    
    public int cows(int numberL, int indexL){//подсчет коров
        int count = 0;
        switch (numberL){
            case 1:
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 4; j++){
                        if(i != j && list1.get(indexL)[i] == list2.get(0)[j]){
                            count++;
                        }
                    }   
                }
                return count;
            case 2:
                for(int i = 0; i < 4; i++){
                   for(int j = 0; j < 4; j++){
                        if(i != j && list2.get(indexL)[i] == list1.get(0)[j]){
                            count++;
                        }
                   }
                }
                return count;
        }
        return -10;
    }
    void changed() {//изменение слушателей
        for (Listener l : listeners) {
            l.dataChanged();
        }
    }
}
