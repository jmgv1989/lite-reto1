package com.josegal.producto1rp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.josegal.producto1rp.domain.model.Product;
import com.josegal.producto1rp.exception.ProductNotFoundException;
import com.josegal.producto1rp.repository.ProductRepository;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/findAll")
    public List<Product> getAllProducts() {
        logger.info("Mensaje de getAllProducts ");
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        logger.info("Mensaje de getProductById: "+id);
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        logger.info("Mensaje de createProduct: "+product);
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        logger.info("Mensaje de updateProduct: "+ productDetails);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        logger.info("Mensaje de deleteProduct");
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
        return "Product " + id + " deleted successfully!";
    }

    @GetMapping("/test/{id}")
    public String testIdProduct(@PathVariable Long id) {
        logger.info("Mensaje de testIdProduct");
        return "Product " + id + " recivido";
    }
}
