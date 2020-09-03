
package com.crio.qcommerce.contract.resolver;

import java.io.File;

public interface DataProvider {

  String getProvider();

  File resolveFile();
}

