package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final List<Product> products = new CopyOnWriteArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public ProductController() {
        // Pre-populate with some basic products
        products.add(new Product(idGenerator.getAndIncrement(), "Laptop", 999.99));
        products.add(new Product(idGenerator.getAndIncrement(), "Smartphone", 499.99));
    }

    // 1. CREATE (POST)
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setId(idGenerator.getAndIncrement());
        products.add(product);
        return product;
    }

    // 2. READ ALL (GET)
    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    // 3. READ ONE (GET by ID)
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // 4. UPDATE (PUT)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        Product existingProduct = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return existingProduct;
    }

    // 5. DELETE (DELETE)
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (removed) {
            return "Product with id " + id + " deleted successfully.";
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
}
