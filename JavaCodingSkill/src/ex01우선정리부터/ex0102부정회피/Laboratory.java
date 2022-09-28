package ex01우선정리부터.ex0102부정회피;

import ex01우선정리부터.ex0101쓸모없는비교회피.sub.Result;
import ex01우선정리부터.ex0101쓸모없는비교회피.sub.Sample;
import ex01우선정리부터.ex0102부정회피.sub.Microscope;

/**
 * 1.2 부정 피하기
 * @author jkoogi
 * 1. 긍정표현의 위해 메소드명 변경
 *  - 부정메소드는 제거하자
 * 2. 긍정 표현을 위해 본문 위치변경
 */
public class Laboratory {

	Microscope microscope;
	
	Result analyze(Sample sample) {
		/* 대상소스 */
//		if(microscope.isInorganic(sample)) {
//			return Result.INORGANIC;		//무기물
//		}else {
//			return analyzeOrganic(sample);  //유기물
//		}

		/* 개선소스 */
		// 1. 긍정표현의 위해 메소드명 변경
		if(microscope.isOrganic(sample)) {
			return analyzeOrganic(sample);	//위치변경
		}else {
			return Result.INORGANIC;		//위치변경
		}
	}

	private Result analyzeOrganic(Sample sample) {
		/* 대상소스 */
//		if(!microscope.isHumanoid(sample)) {
//			return Result.ALIEN;
//		}else {
//			return Result.HUMANOID;
//		}

		/* 개선소스 */
		// 2. 긍정 표현을 위해 본문 위치변경
		if(microscope.isHumanoid(sample)) {
			return Result.HUMANOID;			//위치변경
		}else {
			return Result.ALIEN;			//위치변경
		}
	}

}

