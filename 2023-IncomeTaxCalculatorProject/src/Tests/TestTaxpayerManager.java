package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
//import static org.junit.Assert.*;
import incometaxcalculator.data.management.*;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;



public class TestTaxpayerManager {

  @Test
  public void test() throws WrongTaxpayerStatusException {
    TaxpayerManager taxpayerManager = new TaxpayerManager();
    taxpayerManager.createTaxpayer("TestName",111111111,"Married Filing Jointly",100000);
    assertEquals("TestName",taxpayerManager.getTaxpayerName(111111111));
    //assertEquals(100000,taxpayerManager.getTaxpayerIncome(111111111));
    //assertEquals("TestName",taxpayerManager.getTaxpayerName(111111111));
  }

}
