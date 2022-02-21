package se.ifmo.ru.Lab5.data;
import javax.xml.bind.annotation.*;
@XmlRootElement(name="venue")
@XmlType(propOrder = {"id","name","capacity","address"})
public class Venue {
    //Место отправления
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int capacity; //Значение поля должно быть больше 0
    private Address address; //Поле может быть null
    public Venue(Long id, String name, int capacity,Address address){
        this.id=id;
        this.name=name;
        this.capacity=capacity;
        this.address=address;
    }
    public Venue(){}
    @XmlElement
    public Long getId() {
        return id;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    @XmlElement
    public int getCapacity() {
        return capacity;
    }
    @XmlElement
    public Address getAddress() {
        return address;
    }
    @Override
    public String toString(){
        return "Id="+id+", name="+name+", capacity"+capacity+", adress"+address;
    }
    @Override
    public int hashCode(){
        return (int)(id+name.hashCode()+capacity+address.hashCode());
    }
    @Override
    public boolean equals(Object o){
        if (this==o){return true;}
        if (o instanceof Venue){
            Venue venue=(Venue) o;
            return id.equals(venue.getId())&&name.equals(venue.getName())&&(capacity==venue.getCapacity())&& address.equals(venue.getAddress());

        }
        return false;
    }
}
