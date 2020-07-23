package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //테스트 코드가 스프링을 실행하는 역할을 할 것이라는거을
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록하게 됩니다.
//흔히 스프링의 빈으로 등록된다고 표현하곤 합니다.
@Log4j
//lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성한다.
public class SampleTests {

	@Setter(onMethod_ = {@Autowired})
	//Autowired는 해당 인스턴스 변수가 스프링으로 자동으로 주입해달하는 표시이다.
	private Restaurant restaurant;
	//스프링에 정상적으로 주입이 가능하다면 obj변수에 Restaurant 타입의 객체를 주입하게 된다.
	
	
	@Test
	//Test는 JUnit에서 테스트 대상을 표시하는 어노테이션입니다. 해당 메서드를 선택하고 JUnit Test기능을 실행합니다.
	public void testExist() {
		assertNotNull(restaurant); 
		// assertNotNull은 restaurant 변수가 null이 아니어야만 테스트가 성공한다는 것을 의미합니다.
		
		log.info(restaurant);
		log.info("-----------------------");
		log.info(restaurant.getChef());
	}
}
