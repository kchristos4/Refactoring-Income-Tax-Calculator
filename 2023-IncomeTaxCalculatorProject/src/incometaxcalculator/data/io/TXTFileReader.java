package incometaxcalculator.data.io;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class TXTFileReader extends FileReader {

  protected int checkForReceipt(BufferedReader inputStream)
      throws NumberFormatException, IOException {
    String line;
    while (!isEmpty(line = inputStream.readLine())) {
      String values[] = line.split(" ", 3);
      if (values[0].equals("Receipt")) {
        if (values[1].equals("ID:")) {
          int receiptId = Integer.parseInt(values[2].trim());
          return receiptId;
        }
      }
    }
    return -1;
  }

  protected String getValueOfField(String fieldsLine) throws WrongFileFormatException {
    if (isEmpty(fieldsLine)) {
      throw new WrongFileFormatException();
    }
    try {
      String values[] = fieldsLine.split(" ", 2);
      values[1] = values[1].trim();
      return values[1];
    } catch (NullPointerException e) {
      throw new WrongFileFormatException();
    }
  }

}