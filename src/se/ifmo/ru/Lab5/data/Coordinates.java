package se.ifmo.ru.Lab5.data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="coordinates")
@XmlType( propOrder={"x","y"})
public class Coordinates {
    private float x;
    private long y; //Значение поля должно быть больше -132

    public Coordinates(float x, long y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates(){}
    @XmlElement(name="x")
    public float getX() {
        return x;
    }
    @XmlElement(name="y")
    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return  "x=" + x +", y=" + y ;

    }
    @Override
    public int hashCode(){
        return (int) ((int) x+y);
    }
    @Override
    public boolean equals(Object o){
        if (this==o){return true;}
        if (o instanceof Coordinates){return (x==((Coordinates) o).getX())&&(y==((Coordinates) o).getY());}
        return false;
    }
}
