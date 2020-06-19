package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component //스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시하는 어노테이션입니다.
@Data
public class Restaurant {

	@Setter(onMethod_ = @Autowired) // Setter에서 사용된 onMethod속성은 생성되는 setChef()
									//	에 @Autowired 어노테이션을 추가하도록 합니다.
	private Chef chef;
}
