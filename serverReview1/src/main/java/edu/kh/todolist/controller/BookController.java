package edu.kh.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.todolist.dto.Book;

@Controller
@RequestMapping("book")
public class BookController {

	@GetMapping("select")
	public String selectBook(@ModelAttribute Book book, Model model) {

		// 전달 받은 입력 값을 Model에 세팅해 request scope로 전달하는 코드

		model.addAttribute("title", book.getTitle());
		model.addAttribute("writer", book.getWriter());
		model.addAttribute("price", book.getPrice());
		// templates/book/result.html로 forward

		return "book/result";
	}
}
