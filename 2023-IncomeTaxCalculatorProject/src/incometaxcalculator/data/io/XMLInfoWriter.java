package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;

public class XMLInfoWriter extends FileWriter {

  public void generateFile(int taxRegistrationNumber) throws IOException {

    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO.xml"));
    outputStream.println("<Name> " + getTaxpayerName(taxRegistrationNumber) + " </Name>");
    outputStream.println("<AFM> " + taxRegistrationNumber + " </AFM>");
    outputStream.println("<Status> " + getTaxpayerStatus(taxRegistrationNumber) + " </Status>");
    outputStream.println("<Income> " + getTaxpayerIncome(taxRegistrationNumber) + " </Income>");
    outputStream.println();// den mas emfanize to \n se aplo notepad
    outputStream.println("<Receipts>");
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
      outputStream.println("<ReceiptID> " + getReceiptId(receipt) + " </ReceiptID>");
      outputStream.println("<Date> " + getReceiptIssueDate(receipt) + " </Date>");
      outputStream.println("<Kind> " + getReceiptKind(receipt) + " </Kind>");
      outputStream.println("<Amount> " + getReceiptAmount(receipt) + " </Amount>");
      outputStream.println("<Company> " + getCompanyName(receipt) + " </Company>");
      outputStream.println("<Country> " + getCompanyCountry(receipt) + " </Country>");
      outputStream.println("<City> " + getCompanyCity(receipt) + " </City>");
      outputStream.println("<Street> " + getCompanyStreet(receipt) + " </Street>");
      outputStream.println("<Number> " + getCompanyNumber(receipt) + " </Number>");
      outputStream.println();
    }
  }

}