package se.ifmo.ru.Lab5.utility;

import org.xml.sax.SAXException;
import se.ifmo.ru.Lab5.data.Ticket;
import se.ifmo.ru.Lab5.data.TicketList;
import se.ifmo.ru.Lab5.data.TicketType;
import se.ifmo.ru.Lab5.data.Venue;
import se.ifmo.ru.Lab5.exception.RecursiveCommandException;
import sun.util.resources.LocaleData;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Керпик Артём
 */
public class CollectionManager {
    /**Свойство - Сохраняет коллекцию*/
    private JaxbWorker jaxbWorker;
    /**Свойство - дата*/
    private java.time.LocalDate localDate;
    /**Свойство - Спрашивает у пользователя параметры билета*/
    private final TicketAsker ticketAsker;
    /**Свойство - Связный список, хранящий билеты*/
    private LinkedList<Ticket> tickets;
    /**Свойство - Карта, хранящая все команды*/
    private final HashMap<String,String> helpCommand;
    /**Свойство - объект, хранящий в себе список билетов */
    private TicketList ticketList;
    /**Свойство - переменная окружения */
    private String env;

    /**
     *
     * @param env Переменная окружения
     */
    public CollectionManager(String env)   {
        this.env=env;
        jaxbWorker=new JaxbWorker();
        tickets=new LinkedList<>();
        localDate=LocalDate.now();
        DOM dom =new DOM(tickets);

        try{
            dom.readingFile(env);
        }
         catch (ParserConfigurationException e) {
            System.out.println("Невозможно прочитать файл");
        } catch (IOException e) {
            System.out.println("Файл отсутствует");
        } catch (SAXException e) {
            System.out.println("Ошибка!");
        }
        ticketAsker= new TicketAsker(new Scanner(System.in));
        tickets.sort(Comparator.comparing(Ticket::getId));
        ticketList=new TicketList(tickets);
        helpCommand=new HashMap<>();
        helpCommand.put("help :","Список команд");
        helpCommand.put("info:"," вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) ");
        helpCommand.put("show:"," вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        helpCommand.put("add {element}:"," добавить новый элемент в коллекцию");
        helpCommand.put("update id {element}:", " обновить значение элемента коллекции, id которого равен заданному");
        helpCommand.put("remove_by_id id:","  удалить элемент из коллекции по его id");
        helpCommand.put("clear:"," очистить коллекцию");
        helpCommand.put("save:"," сохранить коллекцию в файл");
        helpCommand.put("execute_script file_name:"," считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        helpCommand.put("exit:"," завершить программу (без сохранения в файл)");
        helpCommand.put("head:"," вывести первый элемент коллекции");
        helpCommand.put("remove_head:"," вывести первый элемент коллекции и удалить его");
        helpCommand.put("add_if_min {element}"," добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        helpCommand.put("remove_any_by_venue venue:"," удалить из коллекции один элемент, значение поля venue которого эквивалентно заданному");
        helpCommand.put("group_counting_by_type:"," сгруппировать элементы коллекции по значению поля type, вывести количество элементов в каждой группе");
        helpCommand.put("count_less_than_refundable refundable:"," вывести количество элементов, значение поля refundable которых меньше заданного");
    }
    /**
     * Метод - выводит список всех команд
     */
    protected void help(){
        for(Map.Entry<String,String> entry:helpCommand.entrySet() ){
            System.out.println(entry );
        }
    }
    /**
     * Метод - выводит на экран основную информацию про коллекцию
     */
    protected void info(){
        System.out.println("Коллекция содержит элементы типа Ticket");
        System.out.println("Дата создания коллекции:"+localDate);
        System.out.println("В коллекции находится: "+tickets.size());
    }
    /**
     * Метод - выводит все билеты в коллекции
     */
    protected void show(){
        if (tickets.size()==0){
            System.out.println("Коллекция пуста");
        }
        else{
            System.out.println("В данный момент хранятся следующие элементы:");
            for(Ticket ticket: tickets){
                System.out.println(ticket);
            }
        }

    }
    /**
     * Метод - создает объект класса Ticket и добавляет в коллекцию, хранящую все билеты
     */
    protected void add(){
        System.out.println("Добавление элемента");
        long id=(long)(Math.random()* Integer.MAX_VALUE);
        if (tickets.size()==0){
            tickets.add(new Ticket(id,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(id, ticketAsker.askName(), ticketAsker.askCapacity(),ticketAsker.askAddress())));
        }
        else {
            tickets.add(new Ticket(tickets.getLast().getId()+1,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(tickets.getLast().getId()+1, ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress())));
        }
        System.out.println("Элемент успешно добавлен");
    }
    /**
     * Метод - обновляет билет по его id
     * @param Id id, по которому происходит обновление элемента
     */
    protected void update(String Id){
        try{
            long id=Long.parseLong(Id);
            for (Ticket ticket: tickets){
                if(ticket.getId()==id){
                    System.out.println("Получилось найти элемент");
                    System.out.println("Обновите элемент");
                    tickets.set(tickets.indexOf(ticket),new Ticket(id,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue( id, ticketAsker.askName(), ticketAsker.askCapacity(),ticketAsker.askAddress())));
                    break;
                }
                if (ticket.equals(tickets.getLast())){System.out.println("Не удалось найти элемент");}
            }
        }
        catch (NoSuchElementException ex){
            System.out.println("Коллекция пуста, обновление невозможно");
        }
        catch (NumberFormatException ex){
            System.out.println("Неверное значение id");
        }
    }
    /**
     * Метод - удаляет билет по его id
     * @param id id, по которому происходит удаление
     */
    protected void remove_by_id(String id){
        try {
            for (Ticket ticket:tickets){
                if(ticket.getId()==Integer.parseInt(id)){
                    tickets.remove(ticket);
                    System.out.println("Элемент найден:\n"+ticket);
                    break;
                }
                if (ticket.equals(tickets.getLast())){
                    System.out.println("Элемент не найден");
                }
            }
        }
        catch (NoSuchElementException ex){
            System.out.println("Коллекция пуста, удаление невозможно");
        }
        catch (NumberFormatException ex){
            System.out.println("Неверное значение id");
        }
    }
    /**
     * Метод - очищает коллекцию
     */
    protected void clear(){
        tickets.clear();
        System.out.println("Коллекция успешно очищена");
    }
    /**
     * Метод - сохраняет коллекцию в файл
     */
    protected void save(){
        String file_name="input.xml";
        jaxbWorker.convertObjectToXml(ticketList,file_name);
        System.out.println("Файл успешно сохранён");
    }
    /**
     * Метод - считывает из файла команды и выполняет их
     * @param file_name имя файла
     */
    protected void execute_script(String file_name) {
        String command;
        String[] finalCommad;
        try {
            System.out.println("Началось считывание файла "+ file_name);
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(file_name)));
            command = inputStreamReader.readLine();

            while (command != null) {
                finalCommad = command.trim().split(" ", 2);
                System.out.println("Сейчас идёт обработка команды "+ finalCommad[0]);
                switch (finalCommad[0]) {
                    case "help":
                        help();
                        break;
                    case "info":
                        info();
                        break;
                    case "show":
                        show();
                        break;
                    case "add":
                        add();
                        break;
                    case "update":
                        update(finalCommad[1]);
                        break;
                    case "remove_by_id":
                        remove_by_id(finalCommad[1]);
                        break;
                    case "clear":
                        clear();
                        break;
                    case "exit":
                        exit();
                        break;
                    case "save":
                        save();
                        break;
                    case "execute_script":
                        if (finalCommad[1].equals(file_name)) throw new RecursiveCommandException();
                        execute_script(finalCommad[1]);
                        break;
                    case "head":
                        head();
                        break;
                    case "remove_head":
                        remove_head();
                        break;
                    case "add_if_min":
                        add_if_min();
                        break;
                    case "remove_any_by_venue":
                        remove_any_by_venue();
                        break;
                    case "group_counting_by_type":
                        group_counting_by_type();
                        break;
                    case "count_less_than_refundable":
                        count_less_than_refundable(finalCommad[1]);
                        break;
                    default:
                        System.out.println("Команды "+finalCommad[0]+" не удалось обнаружить");
                    }
                command = inputStreamReader.readLine();

            }
        }
        catch(FileNotFoundException ex){
                System.out.println("Файла не существует");
            }
        catch(IOException ex){
                System.out.println("Считывание невозможно");
            }
        catch (RecursiveCommandException ex){
            System.out.println("Команда вызывает исполняемый скрипт - выполнение скрипта будет бесконечным ");
        }



    }
    /**
     * Метод - остановка программы
     */
    protected void exit(){System.out.println("До новых встреч!");}
    /**
     * Метод - выводит первый элемент, если таковой имеется
     */
    protected void head(){
        try {
            System.out.println("Первый элемент:"+tickets.peekFirst());
        }
        catch (NoSuchElementException e){
            System.out.println("В коллекции отсутствуют элементы");
        }
    }
    /**
     * Метод - удаляет первый элемент, если таковой имеется
     */
    protected void remove_head(){
        try{
            System.out.println("Удаленный элемент: "+tickets.remove().getName());

        }
        catch (NoSuchElementException e){
            System.out.println("В коллекции отсутствуют элементы, удаление невозможно");
        }
    }
    /**
     * Метод - добавляет в коллекцию новый элемент, если его id наименьший
     */
    protected void add_if_min(){
        if (tickets.size()==0){
            System.out.println("Коллекция пуста, сравнение невозможно");
        }
        else {
            long id=tickets.get(0).getId();
            Ticket ticket=(new Ticket(tickets.getLast().getId()+1,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(tickets.getLast().getId()+1, ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress())));
            for (Ticket ticket1:tickets){
                if(id>ticket1.getId()){
                    id=ticket1.getId();
                }

            }
            if (id>ticket.getId()){
                System.out.println("Элемент успешно добавлен");
                tickets.add(ticket);
            }
            else System.out.println("Элемент не наименьший");
        }
    }
    /**
     * Метод - удаление билета, если их Venue эквивалентны
     */
    protected void remove_any_by_venue(){
        System.out.println("Создание venue:");
        Venue venue=new Venue((long)(Math.random()*10),ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress());
        try {
            for(Ticket ticket: tickets){
                if(ticket.getVenue().equals(venue)){
                    tickets.remove(ticket);
                    System.out.println("Элемент найден и удалён");
                    break;
                }
                if(ticket.equals(tickets.getLast())){System.out.println("Элемент не найден");}
            }

        }
        catch (NoSuchElementException ex){
            System.out.println("Коллекция пуста");
        }
    }
    /**
     * Метод - Сортирует по типу и считает количество типов в коллекции
     */
    protected void group_counting_by_type(){
        int countVIP=0,countUSUAL =0,countBUDGETARY=0,countCHEAP=0;
        TicketType type;
        if (!(tickets.size()==0)){
            for(Ticket ticket:tickets){
                type=ticket.getType();
                switch (type){
                    case VIP:{
                        countVIP+=1;
                        break;
                    }
                    case USUAL:{countUSUAL+=1;break;}
                    case CHEAP:{countCHEAP+=1;break;}
                    case BUDGETARY:{countBUDGETARY+=1;break;}
                }
            }
            tickets.sort(Comparator.comparing(Ticket::getType));
            System.out.println("Коллекция успешно отсортирована");
            System.out.println("Количество VIP="+countVIP);
            System.out.println("Количество USUAL="+countUSUAL);
            System.out.println("Количество BUDGETARY="+countBUDGETARY);
            System.out.println("Количество CHEAP="+countCHEAP);
        }
        else{
            System.out.println("Сортировка невозможна, коллекция пуста");
        }
    }

    /**
     * Считает количество refundable
     * @param refundabl значение
     */
    protected void count_less_than_refundable (String refundabl){
        try {
            int refundable=Integer.parseInt(refundabl);
            int count=0;
            if(refundable>1){
                System.out.println("Количество элементов="+tickets.size());
            }
            if (refundable==1){
                for(Ticket ticket: tickets){
                    if(!ticket.isRefundable()){
                        count+=1;
                    }
                }
                System.out.println(count);
            }


        }
        catch (NoSuchElementException ex){
            System.out.println("Коллекция пуста");
        }
        catch (NumberFormatException ex){
            System.out.println("Сравнение невозможно! refundable не число");
        }
    }
}
