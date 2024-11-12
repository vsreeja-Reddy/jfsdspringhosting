package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Service {
	
	@Autowired
	DAO dao;
	
	@GetMapping("/")
	public String fun1() {
		return "This is Home Page";
	}
	
	@GetMapping("/welcome/{name}")
	public String fun2(@PathVariable("name") String name) {
		return "welcome " + name;
	}
	
	@PostMapping("/user")
	public String fun3(@RequestBody User user) {
		dao.insert(user);
		return "User Inserted";
	}
	
	//@GetMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String fun4(@RequestParam("email") String email) {
		return dao.findUser(email).toString();
	}
	
	@GetMapping("/all")
	public List<User> fun6() {
		return dao.find();
	}
	
	@GetMapping("/page")
	public String fun5(@RequestParam("page")int page,@RequestParam("limit") int limit) {
		return dao.findPage(page,limit).toString();
	}
	
	@DeleteMapping("/delete")
	public String fun7(@RequestParam("email") String email) {
		return dao.deleteUser(email);
	}
	
	@PutMapping("/update")
	public String fun8(@RequestBody User user) {
		System.out.println(user);
		return dao.editUser(user);
	}
	
	@PostMapping("/login")
	public User fun9(@RequestBody User user) {
		User user2=dao.findUser(user.getEmail());
		if(user2==null)
		{
			System.out.println("user2 is null");
			return user;
		}
		else if(user.password.equals(user2.password) )
		{
			System.out.println("password is correct");
			return user2;
		}
		System.out.println("password is wrong");
		return user;
	}
	
	
	
	
	
	
	
	
	
	

}
