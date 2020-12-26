package ex01우선정리부터.ex0102부정회피.sub;

import ex01우선정리부터.ex0101쓸모없는비교회피.sub.Sample;

public class Microscope {

//	2. 부정메소드는 제거하자
	@Deprecated
	public boolean isInorganic(Sample sample) {
		return false;
	}
	
//	1. 긍정표현을 위해 메소드명 변경
	public boolean isOrganic(Sample sample) {
		return false;
	}

	public boolean isHumanoid(Sample sample) {
		return false;
	}


}
