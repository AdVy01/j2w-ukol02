package cz.czechitas.java2webapps.ukol2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {

    private final RandomNumberService numberService;
    private final QuotationRepository quotationRepository;

    @Autowired
    public QuotationService(RandomNumberService numberService, QuotationRepository quotationRepository) {
        this.numberService = numberService;
        this.quotationRepository = quotationRepository;
    }

    public String randomQuotation() {
        return quotationRepository.getQuotation(numberService.getRandomNumber(quotationRepository.quotationsNumber()));
    }

    public String getImageUrl() {
        return String.format("https://source.unsplash.com/%s", randomImageCode());

    }

    private String randomImageCode() {
        return quotationRepository.getImageCode(numberService.getRandomNumber(quotationRepository.imagesNumber()));
    }


}
