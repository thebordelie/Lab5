package se.ifmo.ru.Lab5.utility;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import se.ifmo.ru.Lab5.data.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;


import org.w3c.dom.NodeList;
import se.ifmo.ru.Lab5.exception.ElementMustNotBeEmptyException;
import sun.util.resources.LocaleData;


public class DOM {
    private LinkedList<Ticket> tickets;
    private ArrayList<String> valueOfTicket;
    public DOM(LinkedList<Ticket> tickets){
        this.tickets=tickets;
        valueOfTicket=new ArrayList<>();
    }
    public void readingFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
        Document document=documentBuilder.parse(new File("input.xml"));
        NodeList nodeList=document.getDocumentElement().getElementsByTagName("Ticket");
        for (int i=0;i<nodeList.getLength();i++){
            Node oneel=nodeList.item(i);
            printInfoAboutAllChildNodes(oneel.getChildNodes());
            LocalDate localDate= LocalDate.of(Integer.parseInt(valueOfTicket.get(4).substring(6)),Integer.parseInt(valueOfTicket.get(4).substring(3,5)),Integer.parseInt(valueOfTicket.get(4).substring(0,2)));
            try {
                for(String string: valueOfTicket){
                    if (string.equals("")){
                        throw new ElementMustNotBeEmptyException();
                    }
                }
                tickets.add(new Ticket(Long.parseLong(valueOfTicket.get(0)),valueOfTicket.get(1),
                        new Coordinates(Float.parseFloat(valueOfTicket.get(2)),Long.parseLong(valueOfTicket.get(3))),
                        localDate,Float.parseFloat(valueOfTicket.get(5)),Boolean.parseBoolean(valueOfTicket.get(6)),
                        TicketType.valueOf(valueOfTicket.get(7)),new Venue(Long.parseLong(valueOfTicket.get(8)),valueOfTicket.get(9), Integer.parseInt(valueOfTicket.get(10)),
                        new Address(valueOfTicket.get(11), valueOfTicket.get(12),
                                new Location(Long.parseLong(valueOfTicket.get(13)),Float.parseFloat(valueOfTicket.get(14)),Long.parseLong(valueOfTicket.get(15)), valueOfTicket.get(16)) ))));
            }
            catch (NumberFormatException ex){
                System.out.println("Неверный формат вводна данных, данные искажены");
            }
            catch (IllegalArgumentException ex){
                System.out.println("Неверный тип Билета");
            }
            catch (ElementMustNotBeEmptyException ex){
                System.out.println("Некоторые данные пусты");
            }
            catch (IndexOutOfBoundsException ex){
                System.out.println("Недостаточно данных, данные будут искажены");
            }

            valueOfTicket.clear();
        }

    }
    private void printInfoAboutAllChildNodes(NodeList list){
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.TEXT_NODE) {
                String textInformation = node.getNodeValue().replace("\n", "").trim();
                if (!textInformation.isEmpty())
                    valueOfTicket.add(node.getNodeValue());
            }
            if (node.hasChildNodes())
                printInfoAboutAllChildNodes(node.getChildNodes());
        }
    }
}
