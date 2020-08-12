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
 * @author �������� Windows 10
 */
public class BullsCows {
    private String name1; // ��� 1 ������
    private String name2;//��� 2 ������
    private int action;//�����, ������������ ��������
    private ArrayList<int[]> list1;//������ ��������� ����� 1 ������� (�������)
    private ArrayList<int[]> list2;//������ ��������� ����� 2 ������� (�������)
    private ArrayList<Integer> list1N;//������ ��������� ����� 1 ������� (�������)
    private ArrayList<Integer> list2N;//������ ��������� ����� 2 ������� (�������)
    private ArrayList<Listener> listeners;//������ ����������
    
    public BullsCows(String name1, String name2) {//����������� �� ���������
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
    
    public void setAction(int a){//�������������� ����� � action
        action = a;
        changed();
    }
    
    public void addListener(Listener l) {
        listeners.add(l);
    }
    
    public void addNumber1(String s){//�����. ����� � ������ 1��.(������. �� �����/�����)
        int number = Integer.parseInt(s);
        int listN[]= new int[4];
        for(int i = 3; i >= 0; i--){
            listN[i] = number % 10;
            number /= 10;
        }
        list1.add(listN);
        list1N.add(Integer.valueOf(s));
    }
    public void addNumber2(String s){//�����. ����� � ������ 2��.(������. �� �����/�����)
        int number = Integer.parseInt(s);
        int listN[] = new int[4];
        for(int i = 3; i >= 0; i--){
            listN[i] = number % 10;
            number/=10;
        }
        list2.add(listN);
        list2N.add(Integer.valueOf(s));
    }
    
    public int bulls(int numberL, int indexL){//������� �����
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
    
    public int cows(int numberL, int indexL){//������� �����
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
    void changed() {//��������� ����������
        for (Listener l : listeners) {
            l.dataChanged();
        }
    }
}
