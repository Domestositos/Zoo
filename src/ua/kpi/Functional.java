package ua.kpi;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Functional {

    public static boolean work = true;
    public static String s0="";
    public static int k=0;

    public static void menu(List<ZooDTO> products){
        while(work){
            Scanner s=new Scanner(System.in);
            System.out.println("1.Добавить товар\n2.Удалить товар\n3.Изменить товар\n4.Найти товар\n5.Показать все товары\n6.Выйти");
            k=s.nextInt();
            System.out.println();
            switch(k){
                case 1:
                    input(products);
                    work=true;
                    System.out.println();
                    break;
                case 2,3,4:
                    delSearchChange(products,k);
                    System.out.println();
                    break;
                case 5:
                    for(int i=0;i< products.size();i++){
                        System.out.printf("%d товар\n",i+1);
                        print(products.get(i));
                        System.out.println();
                    }
                    break;
                case 6:
                    work=false;
                    break;
            }
        }
    }

    public static void input(List<ZooDTO> products){
        ZooDTO product=new ZooDTO();
        inputId(product,products);
        inputName(product);
        inputPrice(product);
        inputCount(product);
        products.add(product);
    }

    public static void inputId(ZooDTO product,List<ZooDTO> products){
        while(work) {
            Scanner s=new Scanner(System.in);
            System.out.printf("Введите id товара: ");
            s0=s.nextLine();
            k=Check(s0);
            if (products.size() == 0) {
                product.setId(k);
                work = false;
            }
            else {
                for (int i = 0; i < products.size(); i++) {
                    if (k == products.get(i).getId()) {
                        work = true;
                        System.out.println("В списке уже есть товар с данным id.");
                        break;
                    }
                    else {
                        work = false;
                    }
                }
            }
        }
        product.setId(k);
    }

    public static void inputName(ZooDTO product){
        Scanner s=new Scanner(System.in);
        System.out.printf("Введите имя товара: ");
        product.setName(s.nextLine());
    }

    public static void inputPrice(ZooDTO product){
        System.out.printf("Введите цену товара: ");
        Scanner s=new Scanner(System.in);
        s0 = s.nextLine();
        k=Check(s0);
        product.setPrice(k);
    }

    public static void inputCount(ZooDTO product){
        System.out.printf("Введите количество товаров: " );
        Scanner s=new Scanner(System.in);
        s0 = s.nextLine();
        k=Check(s0);
        product.setCount(k);
    }

    public static void change(List<ZooDTO> products,ZooDTO product){
        System.out.println("Введите имя поля, которое хотите изменить (id, имя, цена, количество)");
        Scanner s=new Scanner(System.in);
        s0=s.nextLine();
        switch(s0.toLowerCase(Locale.ROOT)){
            case "id":
                inputId(product,products);
                break;
            case "имя":
                inputName(product);
                break;
            case "цена":
                inputPrice(product);
                break;
            case "количество":
                inputCount(product);
                break;
        }
    }

    public static void delSearchChange(List<ZooDTO> products,int k){
        System.out.printf("Введите имя или id товара: ");
        Scanner s1=new Scanner(System.in);
        String s0=s1.nextLine();
        if(isDigit(s0)){
            for(int i=0;i<products.size();i++) {
                if (products.get(i).getId() == Integer.parseInt(s0)) {
                    switch(k){
                        case 2:
                            products.remove(i);
                            break;
                        case 3:
                            print(products.get(i));
                            change(products,products.get(i));
                            break;
                        case 4:
                            print(products.get(i));
                            break;
                    }
                    return;
                }
            }
        }
        else{
            for(int i=0;i<products.size();i++){
                if(products.get(i).getName().equals(s0)){
                    switch(k){
                        case 2:
                            products.remove(i);
                            break;
                        case 3:
                            print(products.get(i));
                            change(products,products.get(i));
                            break;
                        case 4:
                            print(products.get(i));
                            break;
                    }
                    return;
                }
            }
        }
        System.out.println("Такого объекта нет в списке.");
    }

    public static void print(ZooDTO product){
        System.out.println("id товара: "+product.getId());
        System.out.println("Имя товара: "+product.getName());
        System.out.println("Цена товара: "+product.getPrice());
        System.out.println("Количество товаров: "+product.getCount());

    }

    public static int Check(String s) throws NumberFormatException{
        while(true) {
            try {
                Integer.parseInt(s);
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Данные не число, введите их повторно");
                Scanner s0=new Scanner(System.in);
                s=s0.nextLine();
            }
        }
    }

    public static boolean isDigit(String s) throws NumberFormatException{
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
