package cz.czechitas.java2webapps.ukol2.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class MainController {

    final List<String> quotations = readAllLines("citaty.txt");
    final Logger logger = LogManager.getLogger(MainController.class.getName());
    final List<String> imagesCodes = Arrays.asList("YucGL8SZkIg", "891QM1IH4wI", "DV6CNig-_OE", "gXQCELcnI2U", "WHjaRrnUX3Q");

    private int getRandomNumber(List<String> arr) {
        Random rand = new Random();
        int upperbound = arr.size();
        return rand.nextInt(upperbound);
    }

    private String getImageUrl(List<String> arr, int randomNumber) {
        return String.format("https://source.unsplash.com/%s", arr.get(randomNumber));

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

    @GetMapping("/")
    public ModelAndView citaty() {
        ModelAndView result = new ModelAndView("citaty");
        int randomNumber = getRandomNumber(imagesCodes);
        result.addObject("imageUrl", getImageUrl(imagesCodes, randomNumber));
        result.addObject("quotation", quotations.get(randomNumber));
        return result;
    }

}
