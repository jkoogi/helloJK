package ex03슬기롭게주석사용하기.ex0310JavaDoc구조화생성자;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ex03슬기롭게주석사용하기.ex0306설명by예제.Supply;

/**
 * 3.10 생성자를 JavaDoc으로 구조화하기
 * @author jkoogi
 * 1. 생성자 : 메서드명이 고정된(의미에 맞는 이름을 정의할 수 없는) 이름 (클래스명)
 *  - 목적 : 객체를 생성한다.
 *  > 이름으로 설명하기 힘든 관계로 JavaDoc에 충분히 설명
 *  ! 새로운 정보가 없는 요약문, 핵심을 벗어난 요약문(supplies 추가방법 설명 누락)
 *  ! 두 생성자 간의 관계정보 누락 - 외부에서 생성자 사용시 JavaDoc만 보고 사용할 수 있도록 충분한 설명 필요
 *  
 * * 원하는대로 동작하기 위한 전제조건(precondition) 설명
 *  - 두번째 생성자의 initialSupplies 파라미터 정보 명시
 *  - null을 입력시 NullPointException 설명(@throws)
 *  - 두번째 생성자 종료 후 상태정보 명시(상태에 따라 호출가능 메서드 결정) : 사후조건(postCondition)
 *   > 빈상태가 되거나 초기제품으로 등록 : 두 생성자의 요약문에 명시
 *  - 개발자 힌트정보 : @see (두생성자의 관계 등)
 *  
 */
public class Inventory {
	List<Supply> supplies;

	/* 대상소스 */
//	/**
//	 * 새 Inventory의 생성자
//	 */
//	public Inventory() {
//		this(new ArrayList<>());
//	}
//
//	/**
//	 * 새 Inventory의 또 다른 생성자
//	 * 
//	 * 제품을 Inventory에 추가할 수 있는 생성자
//	 */
//	Inventory(Collection<Supply> initialSupplies) {
//		this.supplies = new ArrayList<>(initialSupplies);
//	}

	/* 개선소스 */
	/**
	 * 빈 재고를 생성한다.
	 * 
	 *  @see Inventory#Inventory(Collection) 초기 제품을 초기화하는 함수
	 */
	Inventory() {
		this(new ArrayList<>());
	}
	
	/**
	 * 제픔을 처음으로 선적한 재고를 생성한다.
	 * 
	 * @param initialSupplies 제품을 초기화한다.
	 * 						  널이 안 되고 빌 수 있다.
	 * @throws NullPointException initialSupplies가 널일 때
	 * @see Inventory#Inventory() 제품없이 초기화하는 함수 
	 */
	Inventory(Collection<Supply> initialSupplies) {
		this.supplies = new ArrayList<>(initialSupplies);
	}
}
