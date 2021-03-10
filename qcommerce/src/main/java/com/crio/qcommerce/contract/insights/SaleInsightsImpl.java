package com.crio.qcommerce.contract.insights;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.crio.qcommerce.contract.exceptions.AnalyticsException;
import com.crio.qcommerce.contract.resolver.DataProvider;
import com.opencsv.bean.CsvToBeanBuilder;
import com.crio.qcommerce.contract.dto.Amazon;
import com.crio.qcommerce.contract.dto.Ebay;
import com.crio.qcommerce.contract.dto.Flipkart;

public class SaleInsightsImpl implements SaleInsights {

    private List<SaleAggregateByMonth> salesAggMonthList = new ArrayList<>();
    private Map<Integer, Double> monthSaleMap = new TreeMap<>();
    private Double totalSales = 0.0;
    private String provider = "";

    @Override
    public SaleAggregate getSaleInsights(DataProvider dataProvider, int inputYear)
            throws IOException, AnalyticsException {

        File file = dataProvider.resolveFile();
        String year = Integer.toString(inputYear);

        provider = dataProvider.getProvider();

        // analytics for amazon
        if (provider.equalsIgnoreCase("amazon")) {
            List<Amazon> amazonBeans = new CsvToBeanBuilder<Amazon>(new FileReader(file)).withType(Amazon.class).build()
                    .parse();

            for (Amazon amazon : amazonBeans) {
                if (!(amazon.getStatus().equals("pending")) && amazon.getDate().contains(year)) {
                    Integer month = 0;
                    Double amount = 0.0;

                    if (amazon.getDate().equals("") || amazon.getAmount().equals("")) {
                        throw new AnalyticsException("Invalid Data Format...");
                    }

                    LocalDate date = LocalDate.parse(amazon.getDate());
                    month = date.getMonthValue();
                    amount = Double.parseDouble(amazon.getAmount());

                    if (monthSaleMap.containsKey(month)) {
                        monthSaleMap.put(month, amount + monthSaleMap.get(month));
                    } else {
                        monthSaleMap.put(month, amount);
                    }
                }
            }

            for (Map.Entry<Integer, Double> entry : monthSaleMap.entrySet()) {
                totalSales += entry.getValue();
                salesAggMonthList.add(new SaleAggregateByMonth(entry.getKey(), entry.getValue()));
            }

        }
        // analytics for flipkart
        else if (provider.equalsIgnoreCase("flipkart")) {
            totalSales = 0.0;
            List<Flipkart> flipkartBeans = new CsvToBeanBuilder<Flipkart>(new FileReader(file)).withType(Flipkart.class)
                    .build().parse();

            for (Flipkart flipkart : flipkartBeans) {
                if (!(flipkart.getStatus().equals("pending")) && flipkart.getDate().contains(year)) {
                    Integer month = 0;
                    Double amount = 0.0;

                    LocalDate date = LocalDate.parse(flipkart.getDate());
                    month = date.getMonthValue();
                    amount = Double.parseDouble(flipkart.getAmount());

                    if (monthSaleMap.containsKey(month)) {
                        monthSaleMap.put(month, amount + monthSaleMap.get(month));
                    } else {
                        monthSaleMap.put(month, amount);
                    }
                }
            }

            for (Map.Entry<Integer, Double> entry : monthSaleMap.entrySet()) {
                totalSales += entry.getValue();
                salesAggMonthList.add(new SaleAggregateByMonth(entry.getKey(), entry.getValue()));
            }
        }
        // analytics for ebay
        else if (provider.equalsIgnoreCase("ebay")) {
            List<Ebay> ebayBeans = new CsvToBeanBuilder<Ebay>(new FileReader(file)).withType(Ebay.class).build()
                    .parse();

            for (Ebay ebay : ebayBeans) {
                if (!(ebay.getStatus().equals("failed")) && ebay.getDate().contains(year)) {
                    Integer month = 0;
                    Double amount = 0.0;

                    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                            .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toFormatter();

                    month = (LocalDate.parse(ebay.getDate(), formatter)).getMonthValue();
                    amount = Double.parseDouble(ebay.getAmount());

                    if (monthSaleMap.containsKey(month)) {
                        monthSaleMap.put(month, amount + monthSaleMap.get(month));
                    } else {
                        monthSaleMap.put(month, amount);
                    }
                }
            }

            for (Map.Entry<Integer, Double> entry : monthSaleMap.entrySet()) {
                totalSales += entry.getValue();
                salesAggMonthList.add(new SaleAggregateByMonth(entry.getKey(), entry.getValue()));
            }
        }

        SaleAggregate salesAggregate = new SaleAggregate();
        salesAggregate.setTotalSales(totalSales);
        salesAggregate.setAggregateByMonths(salesAggMonthList);

        return salesAggregate;
    }

}