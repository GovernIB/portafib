package es.caib.portafib.back.utils;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.controller.CommonBaseController;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static es.caib.portafib.back.utils.Utils.STRINGKEYVALUECOMPARATOR;

/**
 * @author areus
 */
public class TestUtils {

   private static final Logger log = Logger.getLogger(TestUtils.class);

   @Test
   public void testSortEmptyStringKeyValueList() {
      log.info("testSortEmptyStringKeyValueList");

      List<StringKeyValue> list1 = CommonBaseController.EMPTY_STRINGKEYVALUE_LIST;
      Collections.sort(list1, STRINGKEYVALUECOMPARATOR);
   }

}
