package com.caseStudy.newFinal;

import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	//make a careers database
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepository repo;
	
	//controller for home page
	@GetMapping("/index")
	public String viewHomePage() {
		return "index";
	}
	
	//sign up registration form
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());		
		return "signup_form";
		
	}
	
	/*controller to take password from sign form and encrypt it using BCrypt
	 * while keeping the raw password from user but encrypting it on the database*/
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String encodedPassword=encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);		
		return "register_success";
	}
	
	//controller to list all the users with the findAll() method 
	@GetMapping("/list_users")
	public String viewUsersList(Model model) {
		List<User> listUsers= repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	/*controller for products page. the controller list all the products and with my serch bar
	 * I can filter out the search criteria entering input for the keyword and after input
	 * the program gives me the mathes for the keyword if no mathes then it'll display nothing 
	 */	
	@GetMapping("/products")
	public String getProducts(Model model, @Param("keyword") String keyword) {
		List<Product> listProducts=productService.listAll(keyword);
		model.addAttribute("listProducts",listProducts);
		model.addAttribute("keyword",keyword);
		return "/products";
	}
	
	/*new product controller that take users input and enter it into database to be displayed
	on products page*/
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Product product= new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)  
	public String saveProduct(@ModelAttribute("product")Product product) {
		productService.save(product);
		return "/index";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name="id")Long id) {
		ModelAndView mav=new ModelAndView("edit_product");
		Product product=productService.get(id);
		mav.addObject("product",product);		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id")Long id) {
		productService.delete(id);
		return"redirect:/index";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		if (authentication==null || authentication instanceof AnonymousAuthenticationToken) {
			return"login";
		}
		
		return "redirect:/login";
	}
	
}
