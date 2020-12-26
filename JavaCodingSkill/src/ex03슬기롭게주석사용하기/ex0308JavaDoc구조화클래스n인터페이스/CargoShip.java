package ex03슬기롭게주석사용하기.ex0308JavaDoc구조화클래스n인터페이스;

import java.util.Queue;
import java.util.Stack;

import ex03슬기롭게주석사용하기.ex0306설명by예제.Supply;

/**
 * 3.8 클래스와 인터페이스를 JavaDoc으로 구조화하기
 * 
 * @author jkoogi
 *
 * 1. 모든 public 클래스, interface는 JavaDoc으로 설명 : 자바프로그램 규칙
 * 2. JavaDoc 주석은 요약과 클래스 기능에 대한 상세한 설명을 포함(@link 를 이용해 사용한 자료구조도 표현가능)
 *  - 구조화된 주석 표현
 *  - 요약, 상세설명의 수직적 분리
 *  - 주석의 오류내용 수정(클래스가 아니고 인터페이스)
 *  - 단순한 인터페이스명 반복내용 지양
 * 3. 개선
 *  - JavaDoc 주석 최상단의 요약문은 눈에 잘 띄도록 표현 (이름만 되풀이 하지않고, 유용한 조언 제공)
 *  - 설명 : 동작 상세설명(후입선출법 last-in-first-out)
 *  - 인터페이스 호출 시 capacity에 보장하는 두가지 조건 명시 : 조건불변(invariant)
 *  
 *  * 짧고 간결한 요약 - 클래스나 인터페이스가 보장하는 불변과 수직으로 분리, 예제 도입
 *   
 */

/* 대상소스 */
/**
 * 이 클래스는 화물선을 나타낸다.
 * 제품의 {@link Stack}를 내릴 수 있고 제품의 {©link Queue}를 실을 수 있으며
 * long 타입으로 remainingCapacity를 보여줄 수 있다.
 * */

/* 개선소스 */
/** 
 * 화물선은 용량에 따라 제품은 싣고 내릴 수 있다.
 * 
 * <p>
 * 제품은 순차적으로 선적되고 LIFO(last-in-first-out) 순으로 내려진다.
 * 화물선은 용량만큼만 제품을 저장할 수 있다.
 * 용량은 절대 음수가 아니다.
 */
public interface CargoShip {
	Stack<Supply> unload();
	Queue<Supply> load(Queue<Supply> supplies);
	int getRemainingCapacity();
}
