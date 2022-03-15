package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
@Getter
public class Address {// Class 'Address' should have public no-arg constructor

	private String city;
	private String street;
	private String zipcode;

	/* jpa spec에서 @Entity, @Embeddable 사용시 테이블 매핑클래스를 명시함 - 기본생성자 필수 
	 * - 값타입 선언시 변경 불능으로 설계 - 생성자로만 값 설정시 오류? 가 왜 안나지?
	 * > jpa에서 reflaction, proxy 적용을 위한 생성자 정의 : protected 생성자 허용
	 * */
	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}
