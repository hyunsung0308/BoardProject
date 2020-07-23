package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor // @AllArgsConstructor 없는 경우에는 @Setter(onMethod_ = {@Autowired})를 이용해서 처리해야됨.
public class BoardController {

	
	private BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		
//		log.info("list");
//		
//		model.addAttribute("list" , service.getList());
//		
//	}
	
	@GetMapping("/list")
	public void list(Criteria cri , Model model) {
		
		log.info("list:" + cri);
		model.addAttribute("list", service.getList(cri));
		// model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total = service.getTotal(cri);
		
		log.info("total: " + total );
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
	}
	
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr ) {
		
		log.info("register : "  + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result" , board.getBno());
		//addFlashAttribute 는 일회성으로만 데이트를 전달하기 때문에
		//보관된 데이터는 단 한번만 사용할 수 있게 보관된다.(내부적으로는 HttpSession을 이용해서 처리)
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri")
	Criteria cri, Model model) {
		
		/*
		 * @ModelAttribute는 자동으로 Model에 데이터를지정한 이름으로 담아줍니다.
		 * @ModelAttribute를 사용하지 않아도 Controller에서 화면으로 파라미터가 된 객체는
		 * 전달되지만, 명시적으로 이름을 지정하기 위해서 사용합니다.
		 */
		
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}
	
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove....." + bno);
		
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
//		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board , @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify:" + board);
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result" , "success");
		}
		
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
}
