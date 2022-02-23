package se.ifmo.ru.Lab5.utility;

import se.ifmo.ru.Lab5.data.Venue;

import java.util.Scanner;

public class CommandManager {
    /** Свойство - объект класса CollectionManager*/
    private CollectionManager manager;
    /** Свойство - Изначальное значение команды пользователя*/
    private String userCommand="";
    /** Свойство - Список, содержащий команду и аргументы команды, вводимой пользователем */
    private String[] finalCommad;

    /**
     *
     * @param manager Объект класса CollectionManager, из которого вызываются методы для работы с коллекцией
     */
    public CommandManager(CollectionManager manager){
        this.manager=manager;
    }


    public void interactiveMod(){
        Scanner commandReader = new Scanner(System.in);
        while (!userCommand.equals("exit")) {
            System.out.print("Введите команду:");
            userCommand = commandReader.nextLine();
            finalCommad = userCommand.trim().split(" ", 2);
            // commandManager - обрабатывает команду, введённой пользователем
            commandManager();
            System.out.println("------------------------------------------------------------");
        }




    }
    public void commandManager(){
        switch (finalCommad[0]){
            case "help":
                manager.help();
                break;
            case "info":
                manager.info();
                break;
            case "show":
                manager.show();
                break;
            case "add":
                manager.add();
                break;
            case "update":
                manager.update(finalCommad[1]);
                break;
            case "remove_by_id":
                manager.remove_by_id(finalCommad[1]);
                break;
            case "clear":
                manager.clear();
                break;
            case "exit":
                manager.exit();
                break;
            case "save":
                manager.save();
                break;
            case "execute_script":
                manager.execute_script(finalCommad[1]);
                break;
            case "head":
                manager.head();
                break;
            case "remove_head":
                manager.remove_head();
                break;
            case "add_if_min":
                manager.add_if_min();
                break;
            case "remove_any_by_venue":
                manager.remove_any_by_venue();
                break;
            case  "group_counting_by_type":
                manager.group_counting_by_type();
                break;
            case "count_less_than_refundable":
                manager.count_less_than_refundable(finalCommad[1]);
                break;
            default:
                System.out.println("Команда не найдена, напишите 'help' для получения списка возможных команд");
        }
    }
}
