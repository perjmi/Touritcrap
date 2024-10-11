package com.amalie.thymeleaf.touristguide.model;
import com.amalie.thymeleaf.touristguide.repository.CurrencyRates;
import com.amalie.thymeleaf.touristguide.repository.CurrencyService;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private int cityId;
    private List<Tag> tags = new ArrayList<>();
    private double prisDollar;

    public TouristAttraction() {

    }


    public TouristAttraction(String name, String description, int cityId) {
        this.name = name;
        this.description = description;
        this.prisDollar = 0;
        this.cityId = cityId;
    }
    public TouristAttraction(String name, String description, double prisDollar, int cityId) {
        this.name = name;
        this.description = description;
        this.cityId = cityId;
        this.prisDollar = prisDollar;
    }


    public double getPrisDollar() {
        return prisDollar;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
        return "TouristAttraction{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cityId=" + cityId +
                ", tags=" + tags +
                ", prisDollar=" + prisDollar +
                '}';
    }
}
