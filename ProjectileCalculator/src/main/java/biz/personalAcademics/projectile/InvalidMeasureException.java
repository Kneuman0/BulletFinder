package biz.personalAcademics.projectile;

@SuppressWarnings("serial")
public class InvalidMeasureException extends RuntimeException{
	
	public InvalidMeasureException(String input){
		super(String.format("%s is not a valid measurement", input));
	}

}
