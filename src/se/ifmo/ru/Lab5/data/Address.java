package se.ifmo.ru.Lab5.data;

import java.util.Objects;
import javax.xml.bind.annotation.*;
@XmlRootElement(name="address")
@XmlType(propOrder = {"street","zipCode","town"})
public class Address {
    private String street; //Строка не может быть пустой, Поле может быть null
    private String zipCode; //Поле не может быть null
    private Location town; //Поле может быть null

    public Address(String street, String zipCode, Location town) {
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }
    public Address(){}
    @XmlElement
    public String getStreet() {
        return street;
    }
    @XmlElement
    public String getZipCode() {
        return zipCode;
    }
    @XmlElement
    public Location getTown() {
        return town;
    }

    @Override
    public String toString(){
        return "\nУлица:"+street+", Почтовый индекс:"+zipCode+", \nЗдание:"+town;
    }
    @Override
    public int hashCode(){
        return street.hashCode()+zipCode.hashCode()+town.hashCode();
    }
    @Override
    public boolean equals(Object o){
        if (this==o){return true;}
        if (o instanceof Address){
            Address address=(Address) o;
            return street.equals(address.getStreet())&&zipCode.equals(address.getZipCode())&&town.equals(address.getTown());
        }
        return false;
    }
}