package com.example.demo.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Category;
import com.example.demo.Model.User;
import com.example.demo.Service.CategoryService;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.UserNotFoundException;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	CategoryService catservice;
	@PostMapping("/addCategory") 
	public String createEmployee(@RequestBody Category category)
	{
		boolean b =catservice.isAddNewCategory(category);
		if(b)
		{
			return "Category Added";
		}
		  return "Category Not Added";
	}
	
	@GetMapping("/viewAllCategory")
	public List<Category> getAllCategories()
	{
		List <Category> list= catservice.getAllCategories();
		if(list.size()!=0)
		{
			return list;
		}
		else
		{
			throw new CategoryNotFoundException("there is No data in Database table");
		}
		
		
	}

    @GetMapping("/searchCategory/{id}")
    public Category searchUserById(@PathVariable int id) {
    	Category category = catservice.searchCategoryById(id);

        if (category != null) {
            return category;
        } else {
            throw new CategoryNotFoundException("User with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/deleteCategory/{id}")
    public Category deleteCategoryById(@PathVariable int id) {
    	Category category = catservice.deletehCategoryById(id);

        if (category != null) {
            return category ;
        } else {
            throw new CategoryNotFoundException("User with ID " + id + " not found.");
        }
    }

    @PutMapping("/updateCategory/{id}")
    public Category updateUserById(@PathVariable int id, @RequestBody Category updatedCategory) {
    	Category category = catservice.updateCategoryById(id, updatedCategory);

        if (category != null) {
            return category; 
        } else {
            throw new CategoryNotFoundException("User with ID " + id + " not found.");
        }
    }
	
}





