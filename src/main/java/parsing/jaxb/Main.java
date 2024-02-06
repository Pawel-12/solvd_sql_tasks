package parsing.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parsing.Bank;

import java.io.File;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(com.solvd.laba.block2.bankhierarchy.Main.class);

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Bank.class);
            Unmarshaller um = context.createUnmarshaller();

            Bank bank = (Bank) um.unmarshal(new File("src/main/resources/bank.xml"));
            LOGGER.info(bank);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
