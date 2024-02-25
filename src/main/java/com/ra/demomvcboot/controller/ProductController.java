package com.ra.demomvcboot.controller;

import com.ra.demomvcboot.model.entity.Category;
import com.ra.demomvcboot.model.entity.Product;
import com.ra.demomvcboot.service.CategoryService;
import com.ra.demomvcboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Value("${path-upload}")
    private String pathUpload;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/product")
    public String product(Model model) {
        List<Product> listProduct = productService.getAll();
        model.addAttribute("listProduct", listProduct);
        return "Product/Product";
    }

    @GetMapping("product/add")
    public String add(Model model){
        List<Category> listCategory = categoryService.getAll();
        model.addAttribute("listCategory", listCategory);
        Product product = new Product();
        model.addAttribute("product", product);
        return "Product/add";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("product") Product product, @RequestParam("img")MultipartFile file) {
        // upload file
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(pathUpload+fileName));
            // lưu tên file vào database
            product.setImage(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productService.save(product);
        return "redirect:/product";
    }

//  delete
    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/product";
    }

//  edit
    @GetMapping("/product/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        List<Category> listCategory = categoryService.getAll();
        model.addAttribute("listCategory", listCategory);
        return "Product/edit";
    }

    @PostMapping("/product/update")
    public String update(@ModelAttribute("product") Product product, @RequestParam("img") MultipartFile file) {
        // upload file
        String fileName = file.getOriginalFilename();
        if(fileName != null && !fileName.equals(product.getImage())) {
            try {
                FileCopyUtils.copy(file.getBytes(),new File(pathUpload+fileName));
                // lưu tên file vào database
                product.setImage(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productService.save(product);
        return "redirect:/product";
    }
}
