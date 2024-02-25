package com.ra.demomvcboot.controller;

import com.ra.demomvcboot.model.entity.Category;
import com.ra.demomvcboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String findAll(Model model) {
        List<Category> listCategory = categoryService.getAll();
        model.addAttribute("listCategory", listCategory);
        return "Category/Category";
    }

//  thêm mới
    @GetMapping("/category/add")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "Category/add";
    }

    @PostMapping("category/save")
    public String save(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/category";
    }

//  xóa
    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable Long id){
        categoryService.delete(id);
        return "redirect:/category";
    }

//   cập nhật
    @GetMapping("/category/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "Category/edit";
    }

    @PostMapping("category/update")
    public String update(@ModelAttribute("category") Category category){
        categoryService.save(category);
        return "redirect:/category";
    }
}
