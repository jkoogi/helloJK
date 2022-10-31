package ex07객체디자인.ex703구체타입보다추상타입;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

import ex07객체디자인.ex703구체타입보다추상타입.sub.CargoShip;
import ex07객체디자인.ex703구체타입보다추상타입.sub.Supply;


/**
 * 7.3 구체 타입보다 추상 타입
 * @author jkoogi
 * . 인터페이스와 클래스는 넓은 타입 계층구조를 형성함
 * . 변수에 더 추상적인 타입을 사용할 수록 코드는 더 유연해짐
 * - getContaminatedSupplies() : 
 *   stockUp() 메서드를 통해 생성한 Supply 객체들의 LinkedList를 반복처리
 */
public class Inventory {
	static CargoShip cargoShip;
/* 대상소스 */	
//	LinkedList<Supply> supplies = new LinkedList<>();

//	void stockUp(ArrayList<Supply> delivery) {
//		supplies.addAll(delivery);
//	}
//	
//	LinkedList<Supply> getContaminatedSupplies(){
//		LinkedList<Supply> contaminatedSupplies = new LinkedList<>();
//		for (Supply supply : supplies) {
//			if (supply.isContaminated()) {
//				contaminatedSupplies.add(supply);
//			}
//			
//		}
//		return contaminatedSupplies ;
//	}

	/* 대상소스 실행코드 
	 * - Stack으로 제품을 후입선출(LIFO,Last-In-First-Out)순으로 전달
	 * > Inventory에 제품을 등록하기 위해 ArrayList 필요에 따라 제품을 ArrayList에 담는다.
	 * : Inventory에 ArrayList를 넣으면 stockUp()메서드가 제품을 내부 LinkedList에서 
	 *   변질된 제품을 제거 
	 * . 여러 자료구조 타입간 변환 - 불필요한 변환 남용
	 * . Inventory 변경시 다른부분에 영향
	 * */
//	Stack<Supply> delivery = cargoShip.unload();
//	ArrayList<Supply> loadableDelivery = new ArrayList<>(delivery); // 형변환을 위한 임시 전환타입
//	inventory.stockUp(loadableDelivery); // stockUP 메서드를 이용하여 ArrayList에 담긴 정보를 LinkedList에 형변환하여 전달
//	inventory.getContaminatedSupplies();

	/* 개선소스 
	 * - Stack이 Collection이라 변환 없이 Stack을 받아서 처리할 수 있음
	 * > ArrayList, Set, List 등 사용 가능
	 * */
	List<Supply> supplies = new LinkedList<>();
	
	void stockUp(Collection<Supply> delivery) {
		supplies.addAll(delivery);
	}
	
	List<Supply> getContaminatedSupplies(){
		List<Supply> contaminatedSupplies = new LinkedList<>();
		for (Supply supply : supplies) {
			if (supply.isContaminated()) {
				contaminatedSupplies.add(supply);
			}
			
		}
		return contaminatedSupplies ;
	}
	/* 개선소스 실행코드 
	 * 1. supplies 필드에 LinkedList 대신 List 인터페이스 타입 사용
	 *  - ArrayList 배열이나 LinkedList의 링크드 래퍼 객체를 통해 순서대로 저장되지만, 구조는 알수 없음
	 * 2. stockUp() 메서드가 어떤 Collection(자료구조 객체를 저장하는 가장 기본적인 인터페이스)이든 허용
	 *  - Set,List,Map,SortedSet,SortedMap,HashSet,TreeSet
	 *   ,ArrayList,LinkedList,Vector,Collections,Arrays,AbstractCollection
	 * 3. getContaminatedSupplies() 메서드가 좀 더 추상적인 List타입을 반환
	 *  - 제품은 정렬된 상태로 반환되지만, 내부적 리스트 구현은 알수 없음 
	 *  > 코드의 유연성 증가
	 * */
	//
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		Stack<Supply> delivery = cargoShip.unload();
		inventory.stockUp(delivery);
		inventory.getContaminatedSupplies();
	}
}

/*
 *  Contaminated : 오염된
 */