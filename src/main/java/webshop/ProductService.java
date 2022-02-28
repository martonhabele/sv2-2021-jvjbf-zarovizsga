package webshop;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saleProduct(long id, int amount) {
        Product product = productRepository.findProductById(id);
        if (product.getStock() < amount) {
            throw new IllegalArgumentException("Not enough in stock");
        }
        productRepository.updateProductStock(id, amount);
    }
}