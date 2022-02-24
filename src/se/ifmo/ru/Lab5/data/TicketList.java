package se.ifmo.ru.Lab5.data;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
@XmlRootElement(name = "root")
@XmlType(propOrder = "tickets")
public class TicketList {
    /**
     * Список билетов
     */
    LinkedList<Ticket> tickets;

    /**
     * Конструктор задает список билетов
     * @param tickets список билетов
     */
    public TicketList(LinkedList<Ticket> tickets){
        this.tickets=tickets;
    }
    public TicketList(){}
    @XmlElement(name = "Ticket")
    public LinkedList<Ticket> getTickets() {
        return tickets;
    }
}
