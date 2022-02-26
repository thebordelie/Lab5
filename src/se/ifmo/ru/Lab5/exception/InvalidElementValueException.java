package se.ifmo.ru.Lab5.exception;

public class InvalidElementValueException extends Exception{
    public InvalidElementValueException(){
        super("Элемент не удовлетворяет условиям");
    }
}
