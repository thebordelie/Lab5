package se.ifmo.ru.Lab5.data;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
@XmlRootElement (name="Ticket")
@XmlType(propOrder = { "id","name","coordinates","creationDate","price","refundable","type","venue"})
public class Ticket {
    /** Свойство - Id билета */
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /** Свойство - название билета*/
    private String name; //Поле не может быть null, Строка не может быть пустой
    /** Свойство - координаты места прибытия*/
    private Coordinates coordinates; //Поле не может быть null
    /** Свойство - дата создания*/
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /** Свойство - цена билета */
    private float price; //Значение поля должно быть больше 0
    /** Свойство - возвращаемый ли билет*/
    private boolean refundable;
    /** Свойство - тип билета*/
    private TicketType type; //Поле не может быть null
    /** Свойство - место отправления*/
    private Venue venue; //Поле может быть null
    public Ticket(long id, String name, Coordinates coordinates, LocalDate creationDate, float price, boolean refundable, TicketType type, Venue venue) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.refundable = refundable;
        this.type = type;
        this.venue = venue;
    }
    public Ticket(){}

    /**
     * Метод id
     * @return Возвращает id билета
     */
    @XmlElement(name="id")
    public long getId() {
        return id;
    }

    /**
     * Метод name
     * @return возвращает название билета
     */
    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    /**
     * Метод getCoordinates
     * @return возвращает координаты место прибытия
     */
    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод getCreationDate
     * @return Возвращает дату создания билета
     */
    @XmlElement
    public String getCreationDate() {
        return creationDate.getDayOfMonth()+"."+creationDate.getMonthValue()+"."+creationDate.getYear();
    }

    /**
     * Метод getPrice
     * @return возвращает цену билета
     */
    @XmlElement(name="price")
    public float getPrice() {
        return price;
    }

    /**
     * Метод isRefundable
     * @return возвращает возвращаемость билета
     */
    @XmlElement(name="refundable")
    public boolean isRefundable() {
        return refundable;
    }

    /**
     * Метод getType
     * @return возвращает тип билета
     */
    @XmlElement(name="type")
    public TicketType getType() {
        return type;
    }

    /**
     * Метод getVenue
     * @return возвращает место отправления
     */
    @XmlElement(name="venue")
    public Venue getVenue() {
        return venue;
    }
    @Override
    public String toString(){
        String info="";
        info+="Билет№"+id;
        info+="\n добавлен: "+ creationDate.atStartOfDay().toLocalDate()+" "+creationDate.atStartOfDay().toLocalTime();
        info+="\n Название: "+ name;
        info+="\n Координаты: "+coordinates.getX()+", "+coordinates.getY();
        info+="\n Цена: "+price;
        info+="\n Возвращаемый:"+refundable;
        info+="\n Тип:"+ type;
        info+="\n Отправление с :"+venue.getName();
        info+="\n Находящемся по адресу: "+ venue.getAddress();
        return info;
    }
    @Override
    public int hashCode(){
        return name.hashCode()+coordinates.hashCode()+(int)price+type.hashCode()+venue.hashCode();
    }
    @Override
    public boolean equals(Object o){
        if(this==o){return true; }
        if(o instanceof Ticket){
            Ticket ticket=(Ticket) o;
            return name.equals(ticket.getName())&& coordinates.equals(ticket.getCoordinates())
                    &&(price==ticket.getPrice())&& (refundable==ticket.isRefundable())&&(type==ticket.getType())
                    &&venue.equals(ticket.getVenue());
        }
        return false;
    }


}
