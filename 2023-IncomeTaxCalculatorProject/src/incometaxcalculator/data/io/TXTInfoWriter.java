package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;

public class TXTInfoWriter extends FileWriter {

  public void generateFile(int taxRegistrationNumber) throws IOException {

    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
    outputStream.println("Name: " + getTaxpayerName(taxRegistrationNumber));
    outputStream.println("AFM: " + taxRegistrationNumber);
    outputStream.println("Status: " + getTaxpayerStatus(taxRegistrationNumber));
    outputStream.println("Income: " + getTaxpayerIncome(taxRegistrationNumber));
    outputStream.println();// den mas emfanize to \n se aplo notepad
    outputStream.println("Receipts:");
    outputStream.println();
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }

  private void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {

    HashMap<Integer, Receipt> receiptsHashMap = getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      outputStream.println("Receipt ID: " + getReceiptId(receipt));
      outputStream.println("Date: " + getReceiptIssueDate(receipt));
      outputStream.println("Kind: " + getReceiptKind(receipt));
      outputStream.println("Amount: " + getReceiptAmount(receipt));
      outputStream.println("Company: " + getCompanyName(receipt));
      outputStream.println("Country: " + getCompanyCountry(receipt));
      outputStream.println("City: " + getCompanyCity(receipt));
      outputStream.println("Street: " + getCompanyStreet(receipt));
      outputStream.println("Number: " + getCompanyNumber(receipt));
      outputStream.println();
    }
  }

}