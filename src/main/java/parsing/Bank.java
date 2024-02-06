package parsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.block2.bankhierarchy.domain.*;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "bank")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bank {
    @XmlElementWrapper(name = "consultants")
    @XmlElement(name = "consultant")
    private List<Consultant> consultants;

    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    private List<Client> clients;

    @XmlElementWrapper(name = "accounts")
    @XmlElement(name = "account")
    private List<Account> accounts;

    @XmlElementWrapper(name = "cardtypes")
    @XmlElement(name = "cardtype")
    @JsonProperty("cardtypes")
    private List<CardType> cardTypes;

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    private List<Card> cards;

    public Bank() {
    }

    public List<Consultant> getConsultants() {
        return consultants;
    }

    public void setConsultants(List<Consultant> consultants) {
        this.consultants = consultants;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<CardType> getCardTypes() {
        return cardTypes;
    }

    public void setCardTypes(List<CardType> cardTypes) {
        this.cardTypes = cardTypes;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "consultants=" + consultants +
                ", clients=" + clients +
                ", accounts=" + accounts +
                ", cardTypes=" + cardTypes +
                ", cards=" + cards +
                '}';
    }
}
