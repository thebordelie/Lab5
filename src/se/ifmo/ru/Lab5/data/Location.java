package se.ifmo.ru.Lab5.data;
import javax.xml.bind.annotation.*;
@XmlRootElement(name="town")
@XmlType(propOrder = {"x","y","z","name"})
public class Location {
    /** Свойство - координата по x*/
    private long x;
    /** Свойство - координата по y*/
    private float y;
    /** Свойство - координата по z*/
    private Long z; //Поле не может быть null
    /** Свойство - название здания*/
    private String name; //Длина строки не должна быть больше 272, Поле не может быть null

    public Location(long x, float y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
    public Location(){}
    /**
     * метод getX
     * @return возвращает координату x
     */
    @XmlElement
    public long getX() {
        return x;
    }
    /**
     * метод getY
     * @return возвращает координату y
     */
    @XmlElement
    public float getY() {
        return y;
    }
    /**
     * метод getZ
     * @return возвращает координату z
     */
    @XmlElement
    public Long getZ() {
        return z;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return "\n Название :"+name+",x="+x+", y="+y+", z="+z;

    }
    @Override
    public int hashCode(){
        return (int) (name.hashCode()+x+z+(int) y);
    }
    @Override
    public boolean equals(Object o){
        if (this==o){return true;}
        if (o instanceof Location){
            Location coordinates=(Location) o;
            return (x==coordinates.getX())&&(y==coordinates.getY())&&name.equals(coordinates.getName())&&(z.equals(coordinates.getZ()));}
        return false;
    }
}
