package se.ifmo.ru.Lab5;
import se.ifmo.ru.Lab5.utility.CollectionManager;
import se.ifmo.ru.Lab5.utility.CommandManager;


import java.util.Scanner;
/**
 * @author Керпик Артём Р3112
 */
public class Run {
    /** Свойство - ответ пользователя на вопрос о возрасте*/
    private static String userAnswer;
    public static void main(String[] args){
        String env=System.getenv("JAVA_HOME");
        Scanner commandReader = new Scanner(System.in);
        System.out.println("алоха, сколько вам лет?");
        userAnswer=commandReader.nextLine();
        try {
            if (Integer.parseInt(userAnswer)>=13){
                System.out.println("Извините, мы не продаём бомбастер лицам старше 12 лет, это закон");
            }
            else{
                System.out.println("Добро пожаловать в мир прекрасных грёз без линукса, питона и доллара по 80");
                CommandManager commandManager= new CommandManager(new CollectionManager(env));
                commandManager.interactiveMod();
            }
        }
        catch (NumberFormatException ex){System.out.println("я просил возраст, больше не хочу с тобой дружить((");}

    }
}
