package ex06올바르게드러내기.ex604합당한허용값사용.sub;

public class OxygenTank {

	private static double oxygen;
	private static double oxygenCapacity;

	public OxygenTank(double capacity) {
		this.oxygenCapacity = capacity;
	}

	public static OxygenTank withCapacity(double capacity) {
		return new OxygenTank(capacity);
	}

	public double getStatus() {
		return oxygen/100;
	}

	public void fill(double d) {
		this.oxygen += d;
	}

}
