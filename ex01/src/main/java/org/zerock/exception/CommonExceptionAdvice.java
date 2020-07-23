package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {

	//공통적인 관심사 (cross-concern)는 분리 하는 개념 .
	// Controller를 작성할때 는 메서드의 모든 예외사항을 전부 핸들링해야 한다면 중복적이고 많은 양의 코드를 작성해야 하지만, AOP방식을 이용하면 공통적인
	// 예외사항에 대해서는 별도로 @ControllerAdvice를 이용해서 분리하는 방식입니다.
	
	
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		log.error("Exception .........."  + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		return "error_page";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		
		
		return "custom404";
	}
	
	/*
	 * @ControllerAdvice는 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시하는
	 * 용도로 사용
	 * 
	 * @ExceptionHandler는 해당 메서드가 ()에 들어가는 예외 타입을 처리한다는 것을 의미합니다.
	 * 
	 * @ExceptionHandler 어노테이션의 속서으로는 Exception클래스 타입을 지정 할수 있습니다. 위와 같은 경우 Exception.class를 지정하였으므로 모든 예외에 대한 처리가 except()만을 이용해서 처리할 수 있습니다.
	 */
}
