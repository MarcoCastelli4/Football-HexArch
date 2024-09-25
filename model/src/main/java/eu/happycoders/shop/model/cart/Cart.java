package eu.happycoders.shop.model.cart;

import eu.happycoders.shop.model.annotation.ImportantCalculation;
import eu.happycoders.shop.model.customer.CustomerId;
import eu.happycoders.shop.model.money.Money;
import eu.happycoders.shop.model.product.Product;
import eu.happycoders.shop.model.product.ProductId;

import java.util.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * A shopping cart of a particular customer, containing several line items.
 *
 * @author Sven Woltmann
 */
@Accessors(fluent = true)
@RequiredArgsConstructor
public class Cart {

  // il carrello è una mappa di coppir prodotti, e prodotti nel carrello

  @Getter private final CustomerId id; // cart ID = customer ID

  private final Map<ProductId, CartLineItem> lineItems = new LinkedHashMap<>();
 // private final List<String> test = new ArrayList<>();

  public void addProduct(Product product, int quantity) throws NotEnoughItemsInStockException {
    lineItems
            .computeIfAbsent(product.id(), ignored -> new CartLineItem(product))
            .increaseQuantityBy(quantity, product.itemsInStock());
  }

  // Use only for reconstituting a Cart entity from the database
  public void putProductIgnoringNotEnoughItemsInStock(Product product, int quantity) {
    lineItems.put(product.id(), new CartLineItem(product, quantity));
  }

  public List<CartLineItem> lineItems() {
    return List.copyOf(lineItems.values());
  }

  public int numberOfItems() {
    return lineItems.values().stream().mapToInt(CartLineItem::quantity).sum();
  }

  @ImportantCalculation
  public Money subTotal() {
    return lineItems.values().stream().map(CartLineItem::subTotal).reduce(Money::add).orElse(null);
  }


}
