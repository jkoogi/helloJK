package iloveyouboss.ex210.sub;

public interface Answer {

	String getQuestionText();

	void put(String questionText, Answer answer);

	boolean match(Answer answer);

}
