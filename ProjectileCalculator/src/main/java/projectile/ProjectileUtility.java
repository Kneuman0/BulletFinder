package projectile;

public class ProjectileUtility {
	
	private final double METERS_PER_YARD = .9144;
	private final double YARDS_PER_METER = 1/METERS_PER_YARD;
	private final double METERS_PER_FOOT = .3048;
	private final double ACCELERATION_FROM_GRAVITY = 9.81;
	
	
	/**
	 * accepts a boolean indicating where the toggle for meters or yards is selected. Converts measurement to meters
	 * depending on boolean
	 * @param doNotConvert
	 * @param measurement
	 * @return
	 */
	public double convertYardsToMeters(boolean doNotConvert, String measurement ){
		double answerInMeters;
		if(doNotConvert){
			answerInMeters = Double.parseDouble(measurement);
    	}else{
    		answerInMeters = this.METERS_PER_YARD*Double.parseDouble(measurement);
    	}
		
		return answerInMeters;
	}
	
	/**
	 * must pass radio button for yards under calc distance text box
	 * @param doNotConvert
	 * @param measurement
	 * @return
	 */
	public double convertMetersToYards(boolean doConvert, double measurement){
		double answerInYards;
		if(doConvert){
			answerInYards = this.YARDS_PER_METER*measurement;
    	}else{
    		answerInYards = measurement;
    	}
		
		return answerInYards;
	}
	
	/**
	 * accepts a boolean indicating where the toggle for meters or feet is selected. Converts measurement to meters
	 * depending on boolean
	 * @param doNotConvert
	 * @param measurement
	 * @return
	 */
	public double convertFeetToMeters(boolean doNotConvert, String measurement ){
		double answerInMeters;
		if(doNotConvert){
			answerInMeters = Double.parseDouble(measurement);
    	}else{
    		answerInMeters = this.METERS_PER_FOOT*Double.parseDouble(measurement);
    	}
		
		return answerInMeters;
	}
	
	public double getThetaUsingZero(double zeroDistance, double muzzleVelocity){
		return (Math.asin((ACCELERATION_FROM_GRAVITY * zeroDistance)/(muzzleVelocity * muzzleVelocity)))/2;
	}
	
	public double getTime(double calcDistance, double muzzleVelocity, double theta){
		double timeInSeconds = calcDistance/(muzzleVelocity * Math.cos(theta));
		return timeInSeconds * 1000;
	}
	
	public double getHeightOfBulletInInches(double calcDistance, double muzzleVelocity, double theta){
		double heightInMeters = (calcDistance * Math.tan(theta)) - (ACCELERATION_FROM_GRAVITY/2) * ((calcDistance*calcDistance)/
				((muzzleVelocity*muzzleVelocity) * (Math.cos(theta)*Math.cos(theta))));
		return (heightInMeters / METERS_PER_FOOT) * 12;
	}
	
	public String getUnitsInMetersOrYards(boolean metersSelected){
		if(metersSelected){
			return "meters";
		}else{
			return "yards";
		}
		
	}

}
