package application.rmi.interphace;

import java.io.Serializable;

/**
 * A simple class used to store the result of each questions answered in the survey. 
 * @author Uzoma Oseji - 1715756
 *
 */
public class SurveyAnswers implements Serializable
{
	
	private static final long serialVersionUID = -8145843411531829965L;
	
	
	private String _answerToQuestion1;
	private String _answerToQuestion2;
	private String _answerToQuestion3;
	private String _answerToQuestion4;
	private String _answerToQuestion5;
	

	public SurveyAnswers(String answerToQuestion1, String answerToQuestion2, String answerToQuestion3,
			String answerToQuestion4, String answerToQuestion5)
	{
		_answerToQuestion1 = answerToQuestion1;
		_answerToQuestion2 = answerToQuestion2;
		_answerToQuestion3 = answerToQuestion3;
		_answerToQuestion4 = answerToQuestion4;
		_answerToQuestion5 = answerToQuestion5;
	}
	
	/**
	 * Returns the answer to question 1
	 * @return
	 */
	public String getAnswerToQuestion1()
	{
		return _answerToQuestion1;
	}
	
	/**
	 * Returns the answer to question 2
	 * @return
	 */
	public String getAnswerToQuestion2()
	{
		return _answerToQuestion2;
	}

	/**
	 * Returns the answer to question 3
	 * @return
	 */
	public String getAnswerToQuestion3()
	{
		return _answerToQuestion3;
	}

	/**
	 * Returns the answer to question 4
	 * @return
	 */
	public String getAnswerToQuestion4()
	{
		return _answerToQuestion4;
	}

	/**
	 * Returns the answer to question 5
	 * @return
	 */
	public String getAnswerToQuestion5()
	{
		return _answerToQuestion5;
	}


}
