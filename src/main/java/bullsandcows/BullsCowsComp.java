/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Дарьюшка Windows 10
 */
public class BullsCowsComp {
    private String name1;//имя игрока
    private String name2;//имя компьютера
    private int action;//номер, выполняемого действия
    private ArrayList<int[]> list1;//массив введенных чисел  игроком (цифрами)
    private ArrayList<int[]> list2;//массив введенных чисел компьютером (цифрами)
    private ArrayList<Integer> list1N;//массив введенных чисел игроком (числами)
    private ArrayList<Integer> list2N;//массив введенных чисел компьютером (числами)
    private ArrayList<Listener> listeners;//массив слушателей
    private ArrayList<Integer> numbers;//массив всех чисел (числами)
    private ArrayList<int[]> numbersDigit;//массив всех чисел (цифрами)
    private ArrayList<Boolean> numbersYesNo;//массив предположений для всех чисел
    private String var;
    
    public BullsCowsComp(String name1) {//конструктор 
        this.name1 = name1;
        this.name2 = "Компьютер";
        action = 1;
        list1 = new ArrayList<int[]>();
        list2 = new ArrayList<int[]>();
        list1N = new ArrayList<Integer>();
        list2N = new ArrayList<Integer>();
        listeners = new ArrayList<Listener>();
        numbers = new ArrayList<Integer>();
        numbersDigit = new ArrayList<int[]>();
        numbersYesNo = new ArrayList<Boolean>();
        var = "";
        for(int i = 1; i < 10; i++){//все возможные числа
           for(int k = 0; k < 10; k++){
                for(int j = 0; j < 10; j++){
                    for(int m = 0; m < 10; m++){
                        if(!(i == k || i == j || i == m ||
                                j == k || m == k || j == m)){
                                        int num = i * 1000 + k * 100 +
                                                j * 10 + m;
                                        numbers.add(num);
                                        int b[] = {i, k, j, m};
                                        numbersDigit.add(b);
                                    }
                    }
                }
            }
        }     
        for(int i = 0; i < numbers.size(); i++){//сначала предполагаем все числа
            numbersYesNo.add(Boolean.TRUE);
        }
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
   
    public void addNumber1(String s){//добав. номер в списки иг.(разлож. на цифры/числел)
        int number = Integer.parseInt(s);
        int listN[]= new int[4];
        for(int i = 3; i >= 0; i--){
            listN[i] = number % 10;
            number/=10;
        }
        list1.add(listN);
        list1N.add(Integer.valueOf(s));
    }
    public void addNumber2(){//добав. номер в списки компьютер
        int number;//возможное число        
        while(true){
            final Random random = new Random();        
            int iNumb = Integer.parseInt(String.valueOf(
                    random.nextInt(numbers.size())));//случ-ый индекс возможного числа
            number = numbers.get(iNumb);//возможное число 
            if(numbersYesNo.get(iNumb))//если число подходит (true)
                break;            
        }              
        list2N.add(number);//добавляем число в список загаданных чисел компьютером (числом)
        int listN[]= new int[4];// --"-- (цифрами)
        for(int i = 3; i >= 0; i--){
            listN[i] = number % 10;
            number/=10;
        }
        list2.add(listN); 
        if(list2N.size() != 1){//если уже нужно отгадывать
            int cows = cows(2, (list2N.size()-1));//подсчет быков и коров в предполагаемом введенном числе
            int bulls = bulls(2, (list2N.size()-1));
            for(int i = 0; i < numbers.size(); i++){//убирам неподходящие числа из всех возможных вариантов чисел
                if(!numberYesNo(listN, numbersDigit.get(i), bulls, cows))
                    numbersYesNo.set(i, Boolean.FALSE);
            }
        }        
        System.out.println("компьютер загадал:" + list2N.get(list2N.size()-1));
        System.out.println("Варианты у компьютера: ");
        int count = 0; 
        for(int i = 0; i < numbers.size(); i++){
                if(numbersYesNo.get(i)){
                    if(list2N.size() > 3)
                        System.out.print(numbers.get(i).toString() + " ");
                    count++;
                }
            }
        System.out.println("\n У компьютера осталось " + count + " вариантов \n\n");
        var = "У компьютера осталось " + count + " вариант(ов/а)";
    }
    
    public String getVar(){
        return var;
    }
    
    private boolean numberYesNo(int numberEnter[], int numberDigit[],
            int bulls, int cows){//определяем может ли число быть загаданным
        int countB = 0;
        int countC = 0;
        for(int i = 0; i < 4; i++){
            if(numberEnter[i] == numberDigit[i])
                countB++;//сколько сходится быков у введенного числа и будущего возможного числа
            if(numberEnter[i] != numberDigit[i] && (digitYesNo(numberEnter[i], numberDigit)))
                countC++;//сколько совпадает цифр (и НЕ быков) у введенного числа и будущего возможного числа
        }
        if(countB != bulls){ //быков должно быть столько же в будущем возможном числе
           return false;            
        }
        if(countC < cows){/*количество совпадающих цифр (и не быков) у введенного
            числа и будущего возможного числа должно быть не меньше числа коров
            в введенном числе*/
            return false;
        }
        return true;
    }
    private boolean digitYesNo(int n, int numberDigit[]){//цифра есть в массиве?
        for(int i = 0; i < 4; i++)
            if(n == numberDigit[i])
                return true;
        return false;
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
