package incometaxcalculator.exceptions;

public class WrongReceiptDateException extends Exception {

  private static final long serialVersionUID = 6412939467020438612L;

  public WrongReceiptDateException() {
    super("Please make sure your date is DD/MM/YYYY and try again.");
  }
}
