package eu.happycoders.shop.model.customer;

/**
 * A customer ID value object (enabling type-safety and validation).
 *
 * @author Sven Woltmann
 */

/**
 * in questo modo non facciamo codesmell perchè usiamo il wrapper che fa direttamente il controllo
 * sul valore"""*
 */
public record CustomerId(int value) {

  public CustomerId {
    if (value < 1) {
      throw new IllegalArgumentException("'value' must be a positive integer");
    }
  }
}
