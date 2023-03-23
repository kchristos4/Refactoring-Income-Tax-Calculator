package incometaxcalculator.exceptions;

public class WrongFileFormatException extends Exception {

  private static final long serialVersionUID = -2783561412651046312L;

  public WrongFileFormatException() {
    super("Please check your file format and try again!");
  }
}
