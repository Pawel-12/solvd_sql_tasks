package parsing.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(com.solvd.laba.block2.bankhierarchy.Main.class);

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Bank bank = mapper.readValue(new File("src/main/resources/bank.json"), Bank.class);
            LOGGER.info(bank);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
