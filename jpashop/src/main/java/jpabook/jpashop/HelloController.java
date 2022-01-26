package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) { // Model : Spring UI 의 Model 에 데이터를 실어서 View에 전달 
		model.addAttribute("data","hello!!");
		
		return "hello";	// 화면이름 - resource > templates의 화면 위치로 이동
	}
}
