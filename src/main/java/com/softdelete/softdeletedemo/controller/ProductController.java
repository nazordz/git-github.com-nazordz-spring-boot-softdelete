package com.softdelete.softdeletedemo.controller;

import com.softdelete.softdeletedemo.dto.HttpResponseDto;
import com.softdelete.softdeletedemo.dto.ProductDto;
import com.softdelete.softdeletedemo.entity.Product;
import com.softdelete.softdeletedemo.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<HttpResponseDto<Product>> create(@RequestBody ProductDto productDto){
        Product newProduct = modelMapper.map(productDto, Product.class);
        HttpResponseDto<Product> responseDto = new HttpResponseDto<>();
        Product gotNewOne = productService.create(newProduct);
        responseDto.setStatus(true);
        responseDto.setMessage("created");
        responseDto.setPayload(gotNewOne);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<HttpResponseDto<Iterable<Product>>> findAll() {
        HttpResponseDto<Iterable<Product>> body = new HttpResponseDto<>();
        body.setMessage("show all products");
        body.setStatus(true);
        body.setPayload(productService.findAll());
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponseDto<Boolean>> delete(@PathVariable("id") String id) {
        productService.remove(id);
        HttpResponseDto<Boolean> responseDto = new HttpResponseDto<>();
        responseDto.setStatus(true);
        responseDto.setMessage("Product Deleted");
        responseDto.setPayload(true);
        return ResponseEntity.ok(responseDto);
    }
}
