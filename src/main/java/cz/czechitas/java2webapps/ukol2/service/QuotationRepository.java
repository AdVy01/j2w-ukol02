package cz.czechitas.java2webapps.ukol2.service;

import cz.czechitas.java2webapps.ukol2.controller.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotationRepository {

    final List<String> quotations = readAllLines("citaty.txt");
    final Logger logger = LogManager.getLogger(MainController.class.getName());
    final List<String> imagesCodes = Arrays.asList("YucGL8SZkIg", "891QM1IH4wI", "DV6CNig-_OE", "gXQCELcnI2U", "WHjaRrnUX3Q");

    public int quotationsNumber() {
        return quotations.size();
    }

    public int imagesNumber() {
        return imagesCodes.size();
    }

    public String getQuotation(int index) {
        return quotations.get(index);
    }

    public String getImageCode(int index) {
        return imagesCodes.get(index);
    }

    private List<String> readAllLines(String resource) {
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){

            return reader
                    .lines()
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error(ex);
            return null;
        }
    }


}
