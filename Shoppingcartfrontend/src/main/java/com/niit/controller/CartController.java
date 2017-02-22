package com.niit.controller;

import java.security.Principal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.niit.shoppingcartback.dao.CartDAO;
import com.niit.shoppingcartback.dao.ProductDAO;
import com.niit.shoppingcartback.dao.UserDAO;
import com.niit.shoppingcartback.model.Cart;
import com.niit.shoppingcartback.model.Product;
import com.niit.shoppingcartback.model.User;

@Controller
public class CartController {
	
	@Autowired
	private Cart cart;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private Product product;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	@RequestMapping ("/productDetails/{id}")
	public ModelAndView productDetails(@PathVariable("id") String id){
		ModelAndView mv = new ModelAndView("productDetails");
		 product = productDAO.get(id);
		
		mv.addObject("product",product );
		mv.addObject("userloggedin",true );
		
		mv.addObject("isProductClicked", true);
		
		
		return mv;
	}
	
	@RequestMapping("/order/{username}")
	public String placeorder(@PathVariable("username") String username){
		
		
		return "redirect:/checkout?user_name"+username;
		
	}
	
	@RequestMapping("/viewcart/{usersId}")
	public String viewcart(@PathVariable("usersId") String usersId, Model model){
		User user = userDAO.getById(usersId);
		String username = user.getUsername();
		List<Cart> cartList = cartDAO.getlist(usersId);
		model.addAttribute("cartList", cartList);
		Long total = cartDAO.getTotalAmount(usersId);
		model.addAttribute("total", total);
		model.addAttribute("username", username);
		System.out.println("haiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		return "myCart";
	}
	
	@RequestMapping(value="/refreshcart/{emailid}", produces="application/json")
	public @ResponseBody List<Cart> refreshCartItem(@PathVariable("emailid") String emailid){
		return cartDAO.list(emailid);
	}
	
	@RequestMapping(value="/addtocart/{itemId}" , method = RequestMethod.PUT)
	public String addtocart(@PathVariable("itemId") String itemId,Principal pr, Model model){
		Product product = productDAO.get(itemId);
		String email = pr.getName();
	    user = userDAO.getByEmail(email);
		String usersId = user.getUsersId();
		String product_Id = product.getProduct_Id();
		model.addAttribute("usersId", usersId);
	
		model.addAttribute("product_Id", product_Id);
		
		
if(product.getProduct_Stock() > 2){
			
			Random t = new Random();
			int day = 2 + t.nextInt(7);
			
		
		
		
if(cartDAO.itemAlreadyExist(usersId, product_Id, true)){
			
			int qt = cart.getQuantity() + 1;
			cart.setQuantity(qt);
			cart.setTotal(product.getProduct_Price() * qt);
			cartDAO.saveOrUpdate(cart);
}
		
		else {

		
		cart.setUsername(user.getUsername());
		cart.setProduct_Name(product.getProduct_Name());
		cart.setUsersId(usersId);
		cart.setEmailid(pr.getName());
		cart.setStatus("N");
		cart.setDays(day);
		cart.setProduct_Id(product.getProduct_Id());
		cart.setPrice(product.getProduct_Price());
		cart.setTotal(product.getProduct_Price());
		cart.setQuantity(1);
		System.out.println("inside insert cartController");
		cartDAO.saveOrUpdate(cart);
			
		}
int stock = product.getProduct_Stock() - 1;
product.setProduct_Stock(stock);
productDAO.saveOrUpdate(product);
return "redirect:/viewcart/{usersId}";
}
else{
	
	model.addAttribute("outOfStock", "Out Of Stock");
	return "redirect:/productDetails/{product_Id}";
	
}

		
		
		
	}
	
	
	
	@RequestMapping(value="/removeItemFromCart/{cart_id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public String removeItemFromCart(@PathVariable("cart_id") String cart_id){
		cartDAO.delete(cart_id);
		return "redirect:/viewcart/{usersId}";
	}
	
	
@RequestMapping("/adminCart")
	
	public ModelAndView admincart(){
	
	List<Cart>cartlist  = cartDAO.list();
	
	ModelAndView mv = new ModelAndView("adminlogin");
	

	mv.addObject("cartlist", cartlist);
	mv.addObject("isadmincartclicked", true);
	mv.addObject("adminloggedin", true);
	
	
	return mv;
}

/*@RequestMapping("/addtocart/{usersId}/product_Id")

public String addCart(@PathVariable("usersId") String usersId, @PathVariable("product_Id") String product_Id, Model model){
	
	Product product = productDAO.get(product_Id);
	User user = userDAO.getById(usersId);
	Cart crt = cartDAO.getByUserandProduct(usersId, product_Id);
	
	if(product.getProduct_Stock() > 1){
		
		Random t = new Random();
		int day = 2 + t.nextInt(7);
		
		if(cartDAO.itemAlreadyExist(usersId, product_Id, true)){
			
			int qt = crt.getQuantity() + 1;
			crt.setQuantity(qt);
			crt.setTotal(product.getProduct_Price() * qt);
			cartDAO.saveOrUpdate(crt);
}
		
		else {
			
			cart.setProduct_Name(product.getProduct_Name());
			cart.setPrice(product.getProduct_Price());
			cart.setQuantity(1);
			cart.setTotal(product.getProduct_Price());
			cart.setStatus("N");
			cart.setDays(5);
			cart.setUsername(user.getUsername());
			cart.setUsersId(usersId);
			cart.setProduct_Id(product.getProduct_Id());
			
			cartDAO.saveOrUpdate(cart);
			
		}
		
		int stock = product.getProduct_Stock() - 1;
		product.setProduct_Stock(stock);
		productDAO.saveOrUpdate(product);
		
		model.addAttribute("isLoggedInUser", true);
		String message = "You Have Successfully Logged In";
		model.addAttribute("msg", message);
		return "redirect:/myCart/{Username}";
		
	}
	else{
		
		model.addAttribute("outOfStock", "Out Of Stock");
		return "redirect:/myCart/{Id}";
	}
	}*/
	}


	
