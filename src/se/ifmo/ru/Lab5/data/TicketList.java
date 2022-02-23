package se.ifmo.ru.Lab5.data;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
@XmlRootElement(name = "root")
@XmlType(propOrder = "tickets")
public class TicketList {
    LinkedList<Ticket> tickets;

    public TicketList(LinkedList<Ticket> tickets){
        this.tickets=tickets;
    }
    public TicketList(){}
    @XmlElement(name = "Ticket")
    public LinkedList<Ticket> getTickets() {
        return tickets;
    }
}
