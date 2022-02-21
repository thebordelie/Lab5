package se.ifmo.ru.Lab5.utility;

import se.ifmo.ru.Lab5.data.*;


import javax.xml.bind.*;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class JaxbWorker {
    protected void convertObjectToXml(LinkedList<Ticket> tickets, String name){
        try {
            JAXBContext context = JAXBContext.newInstance(Ticket.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // маршаллинг объекта в файл
            marshaller.marshal(tickets.get(0), new File(name));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
