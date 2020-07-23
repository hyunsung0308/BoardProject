package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor// 모든 속성을 사용하는 생서자를 위한.
@NoArgsConstructor // 비어있는 생성자를 만들기 위한.
public class SampleVO {

	private Integer mno;
	private String firstName;
	private String lastName;
}
