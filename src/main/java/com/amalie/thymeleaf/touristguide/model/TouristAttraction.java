package com.amalie.thymeleaf.touristguide.model;
import com.amalie.thymeleaf.touristguide.repository.CurrencyRates;
import com.amalie.thymeleaf.touristguide.repository.CurrencyService;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private City city;
    private List<Tag> tags = new ArrayList<>();
    private double prisDollar;

    public TouristAttraction() {

    }

    public TouristAttraction(String name, String description, City city) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.prisDollar = 0;
    }

    public TouristAttraction(String name, String description, City city, double prisDollar) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.prisDollar = prisDollar;
    }

    public double getPrisDollar() {
        return prisDollar;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setPrisDollar(double prisDollar) {
        this.prisDollar = prisDollar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public double getPrice(String currency) throws Exception{
        CurrencyService currencyService = new CurrencyService();
        CurrencyRates rates = currencyService.getRates();
        if (currency.equals("EUR")) {
            return rates.getEUR()*prisDollar;
        } else {
            return rates.getDKK()*prisDollar;
        }
    }

    @Override
    public String toString() {
        return
                name + '\'' +
                        ", " + description;
    }
}
