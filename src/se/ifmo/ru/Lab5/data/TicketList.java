package se.ifmo.ru.Lab5.data;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
@XmlRootElement(name = "Ticket")
@XmlType(propOrder = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketList {
    LinkedList<Ticket> tickets;

    public TicketList(LinkedList<Ticket> tickets){
        this.tickets=tickets;
    }
    public LinkedList<Ticket> getTickets() {
        return tickets;
    }
}
