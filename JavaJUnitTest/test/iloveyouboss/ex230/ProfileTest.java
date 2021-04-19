package iloveyouboss.ex230;

import org.junit.Test;

import iloveyouboss.ex210.Profile;
import iloveyouboss.ex210.sub.Criteria;
import iloveyouboss.ex210.sub.Weight;
import iloveyouboss.ex230.sub.Answer;
import iloveyouboss.ex230.sub.Bool;
import iloveyouboss.ex230.sub.BooleanQuestion;
import iloveyouboss.ex230.sub.Criterion;
import iloveyouboss.ex230.sub.Question;


public class ProfileTest {
	@Test
	public void test() throws Exception {
		Profile profile = new Profile("Bull Hockey, Inc");
		
		Question question = new BooleanQuestion(1, "Got bonuses?");
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		
		Criteria criteria = new Criteria();
		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
		
		criteria.add(criterion);
	}
}
