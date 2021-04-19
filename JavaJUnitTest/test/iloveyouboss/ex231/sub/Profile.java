package iloveyouboss.ex231.sub;

import java.util.HashMap;
import java.util.Map;

import iloveyouboss.ex210.sub.Answer;
import iloveyouboss.ex210.sub.Criteria;
import iloveyouboss.ex210.sub.Criterion;
import iloveyouboss.ex210.sub.Weight;

public class Profile {

	private Map<String, Answer> answers = new HashMap<>();
	private int score;
	private String name;
	
	public Profile(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	private void add(Answer answer) {
		answer.put(answer.getQuestionText(), answer);
	}
	
	public boolean matches(Criteria criteria) {
		score = 0;
		
		boolean kill = false;
		boolean anyMatches = false;
		for (Criterion criterion : criteria) {
			Answer answer = answers.get(
					criterion.getAnswer().getQuestionText());
			boolean match = 
					criterion.getWeight() == Weight.DontCare 
					|| answer.match(criterion.getAnswer());
			
			if(!match && criterion.getWeight() == Weight.MustMatch) {
				kill = true;
			}
			if(match) {
				score += criterion.getWeight().getValue();
			}
			anyMatches |= match;
		}
		if(kill) {
			return false;
		}
		return anyMatches;
	}
	
	public int score() {
		return score;
	}

	public void add(iloveyouboss.ex230.sub.Answer profileAnswer) {
		// TODO Auto-generated method stub
		
	}
	
	
}
