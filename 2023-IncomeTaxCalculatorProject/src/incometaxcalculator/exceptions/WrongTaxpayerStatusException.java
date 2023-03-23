package incometaxcalculator.exceptions;

public class WrongTaxpayerStatusException extends Exception {

  private static final long serialVersionUID = -8168283960307911695L;

  public WrongTaxpayerStatusException() {
    super("Please check taxpayer's status and try again!");
  }
}
