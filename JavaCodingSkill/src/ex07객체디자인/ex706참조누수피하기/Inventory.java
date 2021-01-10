package ex07객체디자인.ex706참조누수피하기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ex07객체디자인.ex706참조누수피하기.sub.Supply;


/**
 * 7.6 참조 누수 피하기
 * @author jkoogi
 * - 외부에서 접근이 가능한 내부상태의 객체 : 명백하지않은(non-trivial) 객체
 *  . 내부상태를 어떤방식으로 조작할지 신중한 결정 필요(심각한 버그 대비)
 *  > Inventory 내 supplies(자료구조) 상태는 외부에서 초기화 한 후 생성자로 설정
 *  but 생성자외 객체 참조로 정보를 변경하는 참조누수 가능 
 *  
 */
public class Inventory {
	private final List<Supply> supplies;
	
	Inventory(List<Supply> supplies) {
		/* 대상소스 */
//		this.supplies = supplies;
		/* 개선소스 
		 * - 클래스 내부를 초기화 한 후 조작되지 않도록 보호
		 * > 전달한 리스트의 참조가 아니라 리스트 내 Supply 객체로 내부 ArrayList를 설정
		 * > Null이 들어오면 예외발생
		 * */
		this.supplies = new ArrayList<>(supplies);
	}

	List<Supply> getSupplies() {
		/* 대상소스 */
//		return supplies;
		/* 개선소스  
		 * - getSupplies()로 바로 호출하지 않고 unmodifiableList()로 래핑한 후 노출
		 *  > supplies는 읽기접근만 가능 : 리스트에 원소를 추가하려면 별도 기능을 구현해야 함(getSupplies로는 불가)
		 *  내부 ArrayList 인스턴스는 Inventory의 프라이빗 상태 - 인스턴스로의 참조는 외부에 차단
		 * */
		return Collections.unmodifiableList(supplies);
	}

	/* 대상소스 실행코드 
	 * externalSupplies를 새 Inventory에 전달 - getSupplies()가 빈리스트를 반환
	 *  - inventory는 내부 제품 리스트를 전혀 보호하지 않음.
	 *  > externalSupplies 리스트에 제품을 추가하거나
	 *  > getSupplies()가 반환한 리스트에 변경연산을 수행하여 재고상태 변경이 가능
	 *  > supplies 필드에 final 키워드를 선언해도 변경을 제한할 수 없음
	 *  > 생성자에 null이 전달되면 NullPointException 발생
	 * . Inventory 상태를 new ArrayList()로 생성한 리스트의 주소만 메모리로 등록하기 때문
	 *  - inventory는 이 리스트로의 차조만 supplies 필드에 저장하고, getSupplies()를 통해 참조를 반환.
	 *  > Inventory는 내부 구조로의 참조를 게터를 통해 외부에 노출.
	 *  
	 *  * supplies를 외부에서 변경할 수 없도록 제어하고 싶을 경우.
	 * */
	public static void main1(String[] args) {
		List<Supply> externalSupplies = new ArrayList<Supply>();
		Inventory inventory = new Inventory(externalSupplies);
//		Inventory inventory = new Inventory(null);
		
		inventory.getSupplies().size(); // == 0
		externalSupplies.add(new Supply("Apple"));
		inventory.getSupplies().size(); // == 1
		
		inventory.getSupplies().add(new Supply("Banana"));
		inventory.getSupplies().size(); // == 2
	}

	/* 개선소스 실행코드 
	 * - exernalSupplies 리스트와 getSupplies()가 반환한 리스트 모두 조작 불가
	 *  . inventory 내부상태 안정성 확보
	 *  . getSupplies() 가 반환한 리스트의 수정이 시도되면 UnsupportedOperationException 발생
	 *  
	 *  * 방어복사(defensive copying) : 전달된 자료구조를 재사용하는 대신 복사본을 만들어 제어함
	 *   . 세터, 게터 둘 다 보호해야 함 (세터를 허용하지 않는 디자인 유도)
	 * */
	public static void main(String[] args) {
		List<Supply> externalSupplies = new ArrayList<Supply>();
		Inventory inventory = new Inventory(externalSupplies);
		
		inventory.getSupplies().size(); // == 0
		externalSupplies.add(new Supply("Apple"));
		inventory.getSupplies().size(); // == 0
		
		// UnsupportedOperationException
		inventory.getSupplies().add(new Supply("Banana"));
	}
}
