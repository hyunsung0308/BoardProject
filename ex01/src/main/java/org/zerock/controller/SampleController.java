package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;



@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new
	CustomDateEditor(dateFormat, false));
		
	}
	
	@RequestMapping("") // @RequestMapping의 경우 몇가지의 속성을 추가할 수 있습니다. - Method 속성은 get , post 방식을 구분해서 사용
	public void basic() {
		
		log.info("basic.................");
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET , RequestMethod.POST})
	public void baisicGet() {
		
		log.info("basic get..................");
	}
	
	@GetMapping("/basicOnlyGet") // GetMapping , PostMapping 축약형의 표현
	public void basicGet2() {
		log.info("basic get only get.........");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		
		log.info("" + dto);
		
		return "ex01";
	}
	
	//Controller가 파라미터를 수집하는 방식은 파라미터 타입에 따라 장동으로 변환하는 방식을 이용
	// 
	@GetMapping("/ex02")
		public String ex02(@RequestParam("name") String name, 
	@RequestParam("age") int age) {
		//@RequestParam은 파라미터로 사용된 변수의 이름과 전달되는 파라미터의 이름이 다른 경우에 유용하게 사용된다.
		//변수명과 파라미터의 이름이 동일하기 때문에 사용할 필요는 없지만 한번 사용해 보았다.
		
		
		log.info("name: " + name);
		log.info("age: " + age);
		
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("idx")ArrayList<String> ids) {
		
		log.info("ids:" + ids );
		
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		
		log.info("array ids : " + Arrays.toString(ids));
		
		return "ex02Array";
	}
	
	//http://localhost:8080//sample/ex02Bean?list%5B0%5D.name=aaa&5B1%5D.name=BBB&list%5B2%5D.name=CCC
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos:" + list );
		
		return "ex02Bean";
	}
	
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		// @ModelAttribute는 강제로 전달받은 파라미터를 Model에 담아서전달하도록 할 때 필요한 어노테이션입니다.
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex05")
	public void ex05() {
		
		//void로 지정하는 경우 일반적인 경우에는 해당 URL의 경로를 그대로 jsp 파일의이름으로 사용하게 됩니다.
		log.info("/ex05..........");
		
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06.........");
		
		//스프링 MVC는 자동으로 브라우저에 JSON타입으로 객체를 변환해서 전달하게 됩니다.
		SampleDTO dto = new SampleDTO();
		dto.setAge(26);
		dto.setName("유현성");
		
		return dto;
	}
	
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		
		log.info("/ex07............");
		
		
		//스프링 MVC의 사상은 HttpServletRequest나 HttpServletResponse를 직접 핸들링하지 않아도 이런 작업이 가능하도록 작성되었기 때문에 이러한
		//처리를 위해 ResponseEntity를 통해서 원하는 헤더 정보나 데이터를 전달이 가능하다.
		//ResponseEntity는 HttpHeders 객체를 같이 전달 할수 있고 이를 통해서 원하는 HTTP헤더 메시지를 가공하는 것이 가능합니다.
		//ex07()의 경우 브라우저에는 JSON타입이라는 헤더 메시지와 200 OK라는 코드를 전송합니다.
		
		
		//{"name": "유현성"}
		String msg = "{\"name\" : \"유현성\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload...........");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("-------------------------------------");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:" + file.getSize());
			
		});
	}
	
/*
 * @RequestMapping은 GET, POST 방식 모두콜 지원해야 하는 경우에 배열로 처리해 서 지정할 수 있습니다. 일반적인 경우에는 GET, POST 방식만을 사용하지만 최근에는 
	PUT, DELETE 방식 등도 점점 많이 사용하고 있습니다 @GetMapping의 경우오직 GET 방식에만 사용함 수 있으므로， 칸편하기는 하지만 가능에 대한 제한은 많은 편입 니다. 
 */
}
