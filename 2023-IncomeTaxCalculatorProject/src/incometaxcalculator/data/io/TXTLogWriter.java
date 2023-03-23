package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class TXTLogWriter extends FileWriter {

  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;

  public void generateFile(int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_LOG.txt"));
    outputStream.println("Name: " + getTaxpayerName(taxRegistrationNumber));
    outputStream.println("AFM: " + taxRegistrationNumber);
    outputStream.println("Income: " + getTaxpayerIncome(taxRegistrationNumber));
    outputStream.println("Basic Tax: " + getTaxpayerBasicTax(taxRegistrationNumber));
    if (getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream
          .println("Tax Increase: " + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber));
    } else {
      outputStream
          .println("Tax Decrease: " + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber));
    }
    outputStream.println("Total Tax: " + getTaxpayerTotalTax(taxRegistrationNumber));
    outputStream.println(
        "TotalReceiptsGathered: " + getTaxpayerTotalReceiptsGathered(taxRegistrationNumber));
    outputStream.println(
        "Entertainment: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT));
    outputStream.println("Basic: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC));
    outputStream
        .println("Travel: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL));
    outputStream
        .println("Health: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH));
    outputStream.println("Other: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER));
    outputStream.close();
  }

}
