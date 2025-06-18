package com.Advaith.damodar.controller;


import com.Advaith.damodar.model.Product;
import com.Advaith.damodar.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class productController {
   @Autowired
    private productService service;
    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return  new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);

    }
    @GetMapping("/product/{id}")
    public  ResponseEntity<Product> getProduct(@PathVariable int id){

        Product prod=service.getProductById(id);
        if(prod !=null)
            return  new ResponseEntity<>(prod,HttpStatus.OK) ;
        else
            return( new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;


    }

    @PostMapping("/product")
    public ResponseEntity<?>  addProduct(@RequestPart Product product,
                                         @RequestPart MultipartFile imageFile) {
        try {
            Product prod1 = service.addProduct(product, imageFile);

            return new ResponseEntity<>(prod1, HttpStatus.CREATED);
        } catch (Exception e) {
            
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product =service.getProductById(productId);
        byte[] imageFile= product.getImageDate();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String>updateProduct(@PathVariable int id,@RequestPart Product product,
                                               @RequestPart MultipartFile imageFile) {

        Product pro;
        try {
            pro = service.updateProduct(id, product, imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (pro != null)
            return new ResponseEntity<>("updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed To Update", HttpStatus.BAD_REQUEST);


    }





    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product prod =service.getProductById(id);
        if(prod != null) {
            service.deleteProduct(id);
            return  new ResponseEntity<>("Deleted",HttpStatus.OK);

        } else

            return  new ResponseEntity<>("product not Found",HttpStatus.NOT_FOUND);
    }


    @GetMapping("/products" +
            "/search")
    public ResponseEntity<List<Product>> searchProducts(String keyword){
        List<Product> products=service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);

    }
}
