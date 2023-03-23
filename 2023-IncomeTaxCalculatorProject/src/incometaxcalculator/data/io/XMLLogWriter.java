package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class XMLLogWriter extends FileWriter {

  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;

  public void generateFile(int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_LOG.xml"));
    outputStream.println("<Name> " + getTaxpayerName(taxRegistrationNumber) + " </Name>");
    outputStream.println("<AFM> " + taxRegistrationNumber + " </AFM>");
    outputStream.println("<Income> " + getTaxpayerIncome(taxRegistrationNumber) + " </Income>");
    outputStream
        .println("<BasicTax> " + getTaxpayerBasicTax(taxRegistrationNumber) + " </BasicTax>");
    if (getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream.println("<TaxIncrease> "
          + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + " </TaxIncrease>");
    } else {
      outputStream.println("<TaxDecrease> "
          + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + " </TaxDecrease>");
    }
    outputStream
        .println("<TotalTax> " + getTaxpayerTotalTax(taxRegistrationNumber) + " </TotalTax>");
    outputStream.println(
        "<Receipts> " + getTaxpayerTotalReceiptsGathered(taxRegistrationNumber) + " </Receipts>");
    outputStream.println(
        "<Entertainment> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)
            + " </Entertainment>");
    outputStream.println(
        "<Basic> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC) + " </Basic>");
    outputStream.println(
        "<Travel> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL) + " </Travel>");
    outputStream.println(
        "<Health> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH) + " </Health>");
    outputStream.println(
        "<Other> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER) + " </Other>");
    outputStream.close();
  }

}
