package item04올바르게명명하기.ex402프레임워크GetterSetter;

/**
 * 4.2 프레임워크에는 Getter/Setter 규칙적용
 * @author jkoogi
 * 1. 클래스의 필드는 외부에서 직접 접근하지 않고 getter, setter메서드로 접근 : 접근제어
 *  - Astronaut 클래스를 자바 빈으로 변경
 */
public class Astronaut {

	private String name;
	private boolean retired;

	/* 대상소스 */
//	Astronaut(String name) {
//		this.name = name;
//	}
//	
//	String getFullName() {
//		return name;
//	}
//	
//	void setFullName(String name) {
//		this.name = name;
//	} 
//	
//	boolean getRetired() {
//		return retired;
//	}
//	
//	void setRetired(boolean retired) {
//		this.retired = retired;
//	}

	/* 개선소스 
	 * - 클래스필드의 한정자를 private 적용
	 * - getter, setter 한정자를 public 적용
	 * - 기본생성자 추가 (매개변수 생성자를 추가한 경우 기본생성자 불용 - 별도생성 필요)
	 * - boolean 의 getter명 is~ prefix 적용
	 * */
	public Astronaut() {
	}
	public Astronaut(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isRetired() {
		return retired;
	}
	public void setRetired(boolean retired) {
		this.retired = retired;
	}
}
