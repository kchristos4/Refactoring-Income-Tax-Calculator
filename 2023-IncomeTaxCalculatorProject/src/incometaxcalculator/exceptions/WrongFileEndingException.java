package incometaxcalculator.exceptions;

public class WrongFileEndingException extends Exception {

  private static final long serialVersionUID = 8930076634284333602L;

  public WrongFileEndingException() {
    super("Please check your file ending and try again!");
  }
}