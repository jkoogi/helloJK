package iloveyouboss.ex210.sub;

public enum Weight {

   MustMatch(Integer.MAX_VALUE),
   DontCare(0);
	
	private int value;

   Weight(int value) { this.value = value; }
   public int getValue() { return value; }
   
	public void setValue(int value) {
		this.value = value;
	}

}
