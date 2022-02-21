package se.ifmo.ru.Lab5.exception;

public class ElementMustNotBeEmptyException extends Exception{
    public ElementMustNotBeEmptyException(){
        super("Элемент не может быть пустым");
    }
}
