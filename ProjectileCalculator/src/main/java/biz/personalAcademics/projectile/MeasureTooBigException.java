package biz.personalAcademics.projectile;

@SuppressWarnings("serial")
public class MeasureTooBigException extends RuntimeException{
	
	public MeasureTooBigException(String measure){
		super(String.format("%s is too big of a measurement. Measurements are limited to 6 digits or less.", measure));
	}

}
