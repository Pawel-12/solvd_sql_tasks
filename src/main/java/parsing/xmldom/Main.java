package parsing.xmldom;

import com.solvd.laba.block2.bankhierarchy.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import parsing.Bank;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(com.solvd.laba.block2.bankhierarchy.Main.class);

    public static void main(String[] args) {
        if (!XMLValidator.validateXMLSchema("src/main/resources/bank.xsd", "src/main/resources/bank.xml")) {
            LOGGER.error("Not valid XML file");
            return;
        } else
            LOGGER.info("XML file is valid");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("src/main/resources/bank.xml"));
            doc.getDocumentElement().normalize();

            Bank bank = new Bank();

            bank.setConsultants(parseConsultants(doc.getElementsByTagName("consultants").item(0).getChildNodes()));
            bank.setClients(parseClients(doc.getElementsByTagName("clients").item(0).getChildNodes()));
            bank.setAccounts(parseAccounts(doc.getElementsByTagName("accounts").item(0).getChildNodes()));
            bank.setCardTypes(parseCardTypes(doc.getElementsByTagName("cardtypes").item(0).getChildNodes()));
            bank.setCards(parseCards(doc.getElementsByTagName("cards").item(0).getChildNodes()));

            LOGGER.info(bank);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Consultant> parseConsultants(NodeList conList) {
        List<Consultant> consultants = new ArrayList<>();
        for (int i = 0; i < conList.getLength(); i++) {
            if (conList.item(i).getNodeType() != Node.ELEMENT_NODE)
                continue;

            Consultant consultant = new Consultant();
            Node node = conList.item(i);

            Element element = (Element) node;

            consultant.setId(Long.valueOf(element.getAttribute("id")));
            consultant.setName(element.getElementsByTagName("name").item(0).getTextContent());
            consultant.setSurname(element.getElementsByTagName("surname").item(0).getTextContent());
            consultant.setPhone(element.getElementsByTagName("phone").item(0).getTextContent());

            /*
            for (int j = 0; j < temp.getLength(); j++) {
                consultant.setId(Long.valueOf(conList.item(i).getAttributes().item(0).getTextContent()));
                consultant.setName(temp.item(1).getTextContent());
                consultant.setSurname(temp.item(3).getTextContent());
                consultant.setPhone(temp.item(5).getTextContent());
                consultants.add(consultant);
            }*/
            consultants.add(consultant);
        }
        return consultants;
    }

    public static List<Client> parseClients(NodeList nodeList) {
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE)
                continue;

            Client client = new Client();
            Element element = (Element) nodeList.item(i);

            client.setId(Long.valueOf(element.getAttribute("id")));
            client.setName(element.getElementsByTagName("name").item(0).getTextContent());
            client.setSurname(element.getElementsByTagName("surname").item(0).getTextContent());
            client.setPhone(element.getElementsByTagName("phone").item(0).getTextContent());
            client.setMonthlyIncome(Integer.valueOf(element.getElementsByTagName("monthlyIncome").item(0).getTextContent()));
            client.setTotalDebt(Integer.valueOf(element.getElementsByTagName("totalDebt").item(0).getTextContent()));

            clients.add(client);
        }
        return clients;
    }

    public static List<Account> parseAccounts(NodeList nodeList) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE)
                continue;

            Account account = new Account();
            Node node = nodeList.item(i);

            Element element = (Element) node;

            account.setId(Long.valueOf(element.getAttribute("id")));
            account.setBalance(Long.valueOf(element.getElementsByTagName("balance").item(0).getTextContent()));
            account.setClient(parseClients(element.getElementsByTagName("client")).get(0));
            account.setCurrency(element.getElementsByTagName("currency").item(0).getTextContent());

            accounts.add(account);
        }
        return accounts;
    }

    public static List<CardType> parseCardTypes(NodeList nodeList) {
        List<CardType> cardTypes = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE)
                continue;

            CardType cardType = new CardType();
            Node node = nodeList.item(i);

            Element element = (Element) node;

            cardType.setId(Long.valueOf(element.getAttribute("id")));
            cardType.setName(element.getElementsByTagName("name").item(0).getTextContent());
            cardType.setLimit(Long.valueOf(element.getElementsByTagName("limit").item(0).getTextContent()));
            cardType.setMulticurrency(Boolean.valueOf(element.getElementsByTagName("multicurrency").item(0).getTextContent()));
            cardType.setExchangeFeeRate(Float.valueOf(element.getElementsByTagName("exchangeFeeRate").item(0).getTextContent()));
            cardType.setCreditFeeRate(Float.valueOf(element.getElementsByTagName("creditFeeRate").item(0).getTextContent()));

            cardTypes.add(cardType);
        }
        return cardTypes;
    }

    public static List<Card> parseCards(NodeList nodeList) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE)
                continue;

            Card card = new Card();
            Node node = nodeList.item(i);

            Element element = (Element) node;

            card.setId(Long.valueOf(element.getAttribute("id")));
            card.setCardNumber(element.getElementsByTagName("cardNumber").item(0).getTextContent());
            card.setAccount(parseAccounts(element.getElementsByTagName("account")).get(0));
            card.setCardType(parseCardTypes(element.getElementsByTagName("cardtype")).get(0));
            card.setBlocked(Boolean.valueOf(element.getElementsByTagName("isBlocked").item(0).getTextContent()));
            card.setValidUntil(LocalDate.parse(element.getElementsByTagName("validUntil").item(0).getTextContent()));

            cards.add(card);
        }
        return cards;
    }
}
