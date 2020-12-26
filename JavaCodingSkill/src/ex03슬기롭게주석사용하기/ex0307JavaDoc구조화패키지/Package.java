/* 대상소스 */
/**
 * logistics라는 이 패키지는 물류(logistics)를 위한 클래스를 포함한다.
 * 이 패키지의 inventory 글래스는 화물선에 제품을 선적하고,
 * 변질된 제품은 모두 버릴 수 있다.
 * 
 * 이 패키지의 클래스:
 * - Inventory
 * - Supply
 * - Hull
 * - CargoShip
 * - SupplyCrate
 * 
 * @author jkoogi
 * @version 1.8
 * @since 1.7
 */
/* 개선소스 */
/**
 * 제풀 재고를 관리하는 클래스
 * 
 * <p>
 * 주요 클래스는 {@link logistics.Inventory}로서 아래를 수행한다.
 * 
 * <ul>
 * <li>{@link logistics.CargoShip}으로 선적하고,</li>
 * <li>변질된 {@link logistics.Supply}를 모두 버리고</li>
 * <li>이름으로 어떤 {@link logistics.Supply}든 찾는다.</li>
 * </ul>
 * </p>
 * 이 클래스는 제품을 내리고 변질된 제품은 즉시 모두 버릴 수 있게 해준다.
 * <pre>
 * Inventory inventory new Inventory()；
 * inventory.stockUp(cargoShip.unload0)；
 * inventory.disposeContaminatedSupplies()；
 * inventory.getContaminatedSupplies().isEmpty()； // true
 * </pre>
 * */
package ex03슬기롭게주석사용하기.ex0307JavaDoc구조화패키지;

/**
 * 3.7 패키지를 JavaDoc으로 구조화하기
 *  
 * @author jkoogi
 * * JavaDoc•은 자바 API가 제공하는 문서화 기능
 *  - 패키지를 비롯해 코드에서 public인 요소를 설명하는 데 사용
 *  - API를 작성 중이고 API를 사용하는 데 다른 요소가 필요하다면 반드시 사용
 * ! 불필요한 요약문 정리
 * ! 추상적인 설명 지양
 * ! 버전관리도구와의 중복정보 정리
 * 1. 영역 세 개를 수직으로 분리, 불필요한 정보 정리
 * 2. 소개문 : 매우짧은 요약 제공(패키지 내 클래스로 할수 있는 내용)
 * 3. 설명 : 패키지 내 클래스로 할수 있는 기능 (@link 를 이용한 바로이동 기능 활용)
 * 4. 주요 사용사례(use case) 구현 예제 (개발자가 사용할 수 있는 예제) 
 */
public class Package {

}
