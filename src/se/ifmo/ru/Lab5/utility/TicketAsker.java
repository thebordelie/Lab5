package se.ifmo.ru.Lab5.utility;
import se.ifmo.ru.Lab5.data.Address;
import se.ifmo.ru.Lab5.data.Coordinates;
import se.ifmo.ru.Lab5.data.Location;
import se.ifmo.ru.Lab5.data.TicketType;
import se.ifmo.ru.Lab5.exception.ElementMustNotBeEmptyException;

import java.util.Scanner;
public class TicketAsker {
    private Scanner scanner;
    public TicketAsker(Scanner scanner){
        this.scanner=scanner;
    }
    public void setScanner(Scanner scanner){
        this.scanner=scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }
    public String askName(){
        String name;
        try{
            System.out.print("Введите название:");
            name = scanner.nextLine().trim();
            if (name.equals("")) throw new ElementMustNotBeEmptyException();
            }
        catch (ElementMustNotBeEmptyException e){
            System.out.println(e.getMessage());
            return askName();
            }

        return name;
    }

    public Coordinates askCoordinates(){
        System.out.println("Введите координаты");
        Coordinates coordinates;
        coordinates=new Coordinates(askX(),askY());
        return coordinates;
    }
    public float askX(){
        String strX;
        float x;
        try {
            System.out.print("x=");
            strX=scanner.nextLine().trim();
            if (strX.equals("")) throw new ElementMustNotBeEmptyException();
            x=Float.parseFloat(strX);


        }
        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askX();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askX();
        }

        return x;
    }
    public long askY(){
        String strY;
        long y;
        try {
            System.out.print("y=");
            strY=scanner.nextLine().trim();
            if (strY.equals("")) throw new ElementMustNotBeEmptyException();
            y=Long.parseLong(strY);

        }
        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных!");
            return askY();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askY();
        }

        return y;
    }
    public float askPrice(){
        String strPrice;
        float price;
        try {
            System.out.print("Цена=");
            strPrice=scanner.nextLine().trim();
            if (strPrice.equals("")) throw new ElementMustNotBeEmptyException();
            price=Float.parseFloat(strPrice);

        }
        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askPrice();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askPrice();
        }
        return price;
    }
    public boolean askRefundable(){
        String strRefundable;
        boolean refundable;
        try {
            System.out.print("Возвращаемый:");
            strRefundable=scanner.nextLine().trim();
            if (strRefundable.equals("")) throw new ElementMustNotBeEmptyException();
            if(!(strRefundable.equals("true")||strRefundable.equals("false"))) throw new NumberFormatException();
            refundable=Boolean.parseBoolean(strRefundable);

        }

        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askRefundable();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askRefundable();
        }
        return refundable;
    }
    public TicketType askTicketType(){
        String strType;
        TicketType type;
        try {
            System.out.print("Тип:");
            strType=scanner.nextLine().trim();
            if (strType.equals("")) throw new ElementMustNotBeEmptyException();
            type=TicketType.valueOf(strType);
        }

        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askTicketType();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askTicketType();
        }
        catch (IllegalArgumentException ex){
            System.out.println("Такого типа не существует");
            return askTicketType();
        }
        return type;
    }
    public Long askZ(){
        String strZ;
        long z;
        try {
            System.out.print("z=");
            strZ=scanner.nextLine().trim();
            if (strZ.equals("")) throw new ElementMustNotBeEmptyException();
            z=Long.parseLong(strZ);
        }

        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askZ();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askZ();
        }
        return z;
    }
    public Location askLocation(){
        System.out.println("Координаты здания:");
        Location location;
        location=new Location((long)askX(),askY(),askZ(),askName());
        return location;
    }
    public String askStreet(){
        String strStreet;
        try {
            System.out.print("Улица:");
            strStreet=scanner.nextLine().trim();
            if (strStreet.equals("")) throw new ElementMustNotBeEmptyException();
        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askStreet();
        }


        return strStreet;
    }
    public String askZipCode(){
        String strZipCode;
        try {
            System.out.print("Индекс:");
            strZipCode=scanner.nextLine().trim();
            if (strZipCode.equals("")) throw new ElementMustNotBeEmptyException();
        }

        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askZipCode();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askZipCode();
        }


        return strZipCode;
    }
    public Address askAddress(){
        System.out.println("Введите адрес");
        Address address;
        address=new Address(askStreet(),askZipCode(),askLocation());
        return address;
    }
    public int askCapacity(){
        String strCapacity;
        int price;
        try {
            System.out.print("Вместимость=");
            strCapacity=scanner.nextLine().trim();
            if (strCapacity.equals("")) throw new ElementMustNotBeEmptyException();
            price=Integer.parseInt(strCapacity);
        }
        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askCapacity();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askCapacity();
        }

        return price;
    }
}
