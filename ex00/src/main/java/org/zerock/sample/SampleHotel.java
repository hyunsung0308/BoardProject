package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
@AllArgsConstructor
@RequiredArgsConstructor // 만일 여러개의 인스터스 변수들 중에서 특정한 변수에 대해서만 생성자를 작성하고 싶다면
public class SampleHotel {

	// @NonNull
	private Chef chef;
	
	/* @AllArgsConstructor는 인스턴스 변수로 선언된 모든 것을 파라미터로 받는 생성자를
	 * 작성하게 됩니다. 
	 * 
	 * public SampleHotel(Chef chef) {
	 * 
	 * this.chef = chef; 
	 * 
	 * }
	 */

	public Object getChef() {
		// TODO Auto-generated method stub
		return null;
	}
}
