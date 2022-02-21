package se.ifmo.ru.Lab5.data;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
@XmlRootElement (name="Ticket")
@XmlType(propOrder = { "id","name","coordinates","creationDate","price","refundable","type","venue"})
public class Ticket {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private boolean refundable;
    private TicketType type; //Поле не может быть null
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
    @XmlElement(name="id")
    public long getId() {
        return id;
    }
    @XmlElement(name="name")
    public String getName() {
        return name;
    }
    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }
    @XmlElement
    public String getCreationDate() {
        return creationDate.getDayOfMonth()+"."+creationDate.getMonthValue()+"."+creationDate.getYear();
    }
    @XmlElement(name="price")
    public float getPrice() {
        return price;
    }
    @XmlElement(name="refundable")
    public boolean isRefundable() {
        return refundable;
    }
    @XmlElement(name="type")
    public TicketType getType() {
        return type;
    }
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
