package ex01우선정리부터.ex0101쓸모없는비교회피;

import ex01우선정리부터.ex0101쓸모없는비교회피.sub.Microscope;
import ex01우선정리부터.ex0101쓸모없는비교회피.sub.Result;
import ex01우선정리부터.ex0101쓸모없는비교회피.sub.Sample;

/**
 * 1.1 쓸모없는 비교 피하기
 * @author jkoogi
 * 1. 비교수식에 boolean을 명시적으로 비교하지 말것 - 쓸모없는 비교
 *  - 명시적 부정비교 생략
 * 2. 다중반환문 OK 
 */
public class Laboratory {

	Microscope microscope;
	
	Result analyze(Sample sample) {
		/* 대상소스 */
		// 1. 비교수식에 boolean을 명시적으로 비교하지 말것 - 쓸모없는 비교
//		if(microscope.isInorganic(sample)==true) {
		/* 개선소스 */
		if(microscope.isInorganic(sample)) {
			return Result.INORGANIC;
		}else {
			return analyzeOrganic(sample);
		}
	}

	private Result analyzeOrganic(Sample sample) {
		/* 대상소스 */
		// 2. 명시적 부정비교 생략 
//		if(microscope.isHumanoid(sample)==false) {
		/* 개선소스 */
		if(!microscope.isHumanoid(sample)) {
			//반환1
			return Result.ALIEN;
		}else {
			//반환2
			return Result.HUMANOID;
		}
	}

}

