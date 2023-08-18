package com.example.zadanie10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class Zadanie10Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Zadanie10Application.class, args);
	}

	private List<String> items = new ArrayList<>();

	@GetMapping("/")
	public String getShoppingList(Model model)
	{
		model.addAttribute("items", items);
		return "index";
	}

	@PostMapping("/add")
	public String addItem(@RequestParam String item)
	{
		if (!item.equals(""))
			items.add(item);

		return "redirect:/";
	}

	@GetMapping("/delete")
	public String deleteItem(@RequestParam int index)
	{
		if (index >= 0 && index < items.size())
			items.remove(index);

		return "redirect:/";
	}

	@PostMapping("/edit")
	public String editItem(@RequestParam int index, @RequestParam String newItem)
	{
		if (index >= 0 && index < items.size() && !newItem.equals(""))
			items.set(index, newItem);

		return "redirect:/";
	}
}
