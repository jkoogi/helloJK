package ex07객체디자인.ex707널반환하지않기.sub;

public class SpaceNation {
	String nationCode;
	String nationName;
	public SpaceNation(String nationCode, String nationName) {
		this.nationCode = nationCode;
		this.nationName = nationName;
	}

	public String getCode() {
		return nationCode;
	}

	public String getName() {
		return nationName;
	}

}
