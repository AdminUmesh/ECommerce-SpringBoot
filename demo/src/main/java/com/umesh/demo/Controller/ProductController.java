package com.umesh.demo.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.umesh.demo.Model.ProductDetail;
import com.umesh.demo.Repository.ProductRepo;
import com.umesh.demo.Service.ProductService;

@Controller
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepo productrepo;
    @Autowired
    ProductService productservice;

    @PostMapping("/SaveProduct")
    public ResponseEntity<?> ProductSave(@RequestParam("file") MultipartFile file, @RequestParam("name") String name)
            throws IOException {

        String filename = productservice.Save(file);
        ProductDetail Products = new ProductDetail();
        Products.setName(name);
        Products.setImagePath(filename);
        productrepo.save(Products);
        return ResponseEntity.ok("Product saved successfully");

    }

    @GetMapping("/cards")
    public ResponseEntity<?> GetAllCards() {
        List<ProductDetail> allproduct = productrepo.findAll();
        return new ResponseEntity<>(allproduct, HttpStatus.OK);
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<Resource> getimage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get("Products").resolve(filename);
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(imagePath))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> GetAllProduct() {
        List<ProductDetail> allproduct = productrepo.findAll();

        return new ResponseEntity<>(allproduct, HttpStatus.OK);
    }

    @DeleteMapping("/DeleteProduct")
    public ResponseEntity<?> DeleteData(@RequestParam int deleteId) {

        ProductDetail DatabaseProductData = productrepo.findById(deleteId);

        if (DatabaseProductData.getId() == deleteId) {

            productrepo.delete(DatabaseProductData);
            return new ResponseEntity<>(DatabaseProductData.getId(), HttpStatus.OK);
        } else {
            String loginResponse = "no product Found with" + "DeleteId" + deleteId;
            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
