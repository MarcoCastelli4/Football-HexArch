package eu.happycoders.shop.application.port.in.product;

import eu.happycoders.shop.model.product.Product;
import java.util.List;

/**
 * Use case: Finding products via a search query.
 *
 * @author Sven Woltmann
 */
public interface FindProductsUseCase {

  /** il cliente dovrebbe essere in grado di scrivere un cmapo di testo per cercare un prodotto */
  List<Product> findByNameOrDescription(String query);
}
