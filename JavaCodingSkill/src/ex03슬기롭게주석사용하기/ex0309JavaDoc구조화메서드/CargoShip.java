package ex03슬기롭게주석사용하기.ex0309JavaDoc구조화메서드;

import java.util.Queue;
import java.util.Stack;

import ex03슬기롭게주석사용하기.ex0305구현결정설명.sub.Supply;

/**
 * 3.9 메서드를 JavaDoc으로 구조화하기
 * @author jkoogi
 * 1. 객체의 동작을 표현하는 메서드는 상태변경과 부수효과를 일으킨다. (설명 중요)
 *  ! 서명만 반복하는 주석 지양
 *  - 계약서 읽듯 읽히는 주석(설명, 코드예제)
 *  - 입력과 내부 상태가 출력과 상태변경을 어떻게 보장하는지 명시
 *  - null 입력상황도 @param 설명에 명시(NullPointException 을 @throws 함)
 *  - @see 표기로 다른 메소드 참조 : 메소드가 일으킨 효과를 되돌리는 방법, 상태변화를 관찰할 수 있는 방법 설명
 */
public interface CargoShip {

	Stack<Supply> unload();
	/* 대상소스 */
	/**
	 * {@link Supply}를 싣는다.
	 * 
	 * @param {@link Queue} 타입의 제품 제공
	 * @return {@link Queue} 타입의 적재되지 않은 제품
	 * */
	/* 개선소스 */
	/**
	 * 제품을 화물선에 싣는다.
	 * 
	 * <p>
	 * 남은 용량만큼만 제품을 싣게 해준다.
	 * 
	 * 예:
	 * <pre>
	 * int capacity = cargoShip.getRemainingCapacity(); // 1
	 * Queue<Supply> supplies = Arrays.asList(new Supply("Apple"));
	 * Queue<Supply> spareSupplies = cargoShip.load(supplies);
	 * spareSupplies.isEmpty(); //참
	 * cargoShip.getRemainingCapacity() == 0; //참
	 * </pre>
	 * 
	 * @param 적재할 제품; 널이면 안된다.
	 * @return 용량이 작아 실을 쉬 없었던 제품;
	 * 			모두 실었다면 empty
	 * @throws NullPointException 제품이 널 이면 
	 * @see CargoShip#getReminingCapacity() 용량을 확인하는 함수
	 * @see CargoShip#unload() 제품을 내리는 함수
	 */
	Queue<Supply> load(Queue<Supply> supplies);
	
	int getReminingCapacity();
	
}
