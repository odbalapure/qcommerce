
package com.crio.qcommerce.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.crio.qcommerce.contract.exceptions.AnalyticsException;
import com.crio.qcommerce.contract.insights.SaleAggregate;
import com.crio.qcommerce.contract.insights.SaleAggregateByMonth;
import com.crio.qcommerce.contract.insights.SaleInsights;
import com.crio.qcommerce.contract.resolver.DataProvider;
import com.crio.qcommerce.sale.insights.SaleInsightsImpl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaleInsightsImplTest {

  @Mock
  private DataProvider dataProvider;

  @Test
  void getSaleInsightsFlipkart() throws IOException, AnalyticsException {
    URL flipkartUrl = Thread.currentThread().getContextClassLoader()
        .getResource("assessments/flipkart.csv");
    File flipkartFile = new File(flipkartUrl.getFile());
    Mockito.doReturn(flipkartFile)
        .when(dataProvider).resolveFile();
    Mockito.doReturn("flipkart")
        .when(dataProvider).getProvider();
    SaleInsights insights = new SaleInsightsImpl();
    SaleAggregate aggregates = insights.getSaleInsights(dataProvider, 2020);
    assertEquals(aggregates.getTotalSales(), 146642.12, 1);
    List<SaleAggregateByMonth> aggregateByMonths = aggregates.getAggregateByMonths();
    assertEquals(aggregateByMonths.get(0).getSales(), 20152.41, 1);
    assertEquals(aggregateByMonths.get(1).getSales(), 15568.73, 1);
    assertEquals(aggregateByMonths.get(2).getSales(), 21259.4, 1);
    assertEquals(aggregateByMonths.get(3).getSales(), 19881.28, 1);
    assertEquals(aggregateByMonths.get(7).getSales(), 11802.44, 1);
  }

  @Test
  void getSaleInsightsAmazon() throws IOException, AnalyticsException {
    URL flipkartUrl = Thread.currentThread().getContextClassLoader()
        .getResource("assessments/amazon.csv");
    File flipkartFile = new File(flipkartUrl.getFile());
    Mockito.doReturn(flipkartFile)
        .when(dataProvider).resolveFile();
    Mockito.doReturn("amazon")
        .when(dataProvider).getProvider();
    SaleInsights insights = new SaleInsightsImpl();
    SaleAggregate aggregates = insights.getSaleInsights(dataProvider, 2020);
    assertEquals(aggregates.getTotalSales(), 124076.6, 1);
    List<SaleAggregateByMonth> aggregateByMonths = aggregates.getAggregateByMonths();
    assertEquals(aggregateByMonths.get(0).getSales(), 19898.04, 1);
    assertEquals(aggregateByMonths.get(1).getSales(), 16680.59, 1);
    assertEquals(aggregateByMonths.get(2).getSales(), 17988.25, 1);
    assertEquals(aggregateByMonths.get(3).getSales(), 14993.05, 1);
    assertEquals(aggregateByMonths.get(7).getSales(), 8287.78, 1);
  }


  @Test
  void getSaleInsightsEbay() throws IOException, AnalyticsException {
    URL flipkartUrl = Thread.currentThread().getContextClassLoader()
        .getResource("assessments/ebay.csv");
    File flipkartFile = new File(flipkartUrl.getFile());
    Mockito.doReturn(flipkartFile)
        .when(dataProvider).resolveFile();
    Mockito.doReturn("ebay")
        .when(dataProvider).getProvider();
    SaleInsights insights = new SaleInsightsImpl();
    SaleAggregate aggregates = insights.getSaleInsights(dataProvider, 2020);
    assertEquals(aggregates.getTotalSales(), 163361.0, 1);
    List<SaleAggregateByMonth> aggregateByMonths = aggregates.getAggregateByMonths();
    assertEquals(aggregateByMonths.get(0).getSales(), 19768.0, 1);
    assertEquals(aggregateByMonths.get(1).getSales(), 17176.0, 1);
    assertEquals(aggregateByMonths.get(2).getSales(), 19004.0, 1);
    assertEquals(aggregateByMonths.get(3).getSales(), 20939.0, 1);
    assertEquals(aggregateByMonths.get(7).getSales(), 16007.0, 1);
  }

  @Test
  void getSaleInsightsInvalidData() throws IOException, AnalyticsException {
    URL flipkartUrl = Thread.currentThread().getContextClassLoader()
        .getResource("assessments/invalid_data.csv");
    File flipkartFile = new File(flipkartUrl.getFile());
    Mockito.doReturn(flipkartFile)
        .when(dataProvider).resolveFile();
    Mockito.doReturn("amazon")
        .when(dataProvider).getProvider();
    SaleInsights insights = new SaleInsightsImpl();
    assertThrows(AnalyticsException.class, () -> insights.getSaleInsights(dataProvider, 2020));
  }

}
