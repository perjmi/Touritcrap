package com.amalie.thymeleaf.touristguide.repository;

    public class CurrencyRates {
        private String table;
        private Rate rates;

        static final class Rate {
            private double DKK;
            private double EUR;
            private double USD;

            @Override
            public String toString() {
                return "DKK: " + DKK + ", " +
                        "EUR: " + EUR + ", "+
                        "USD: " + USD;
            }
        }

        public CurrencyRates(String table, Rate rates) {
            this.table = table;
            this.rates = rates;
        }

        @Override
        public String toString() {
            return "CurrencyRates{" +
                    "table='" + table + '\'' +
                    ", rates=" + rates +
                    '}';
        }

        public double getDKK() {
            return rates.DKK;
        }
        public double getEUR() {
            return rates.EUR;
        }

    }

