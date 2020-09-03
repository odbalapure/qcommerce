
package com.crio.qcommerce.contract.insights;

import com.crio.qcommerce.contract.exceptions.AnalyticsException;
import com.crio.qcommerce.contract.resolver.DataProvider;
import java.io.IOException;

public interface SaleInsights {

  SaleAggregate getSaleInsights(DataProvider dataProvider, int year)
      throws IOException, AnalyticsException;

}

