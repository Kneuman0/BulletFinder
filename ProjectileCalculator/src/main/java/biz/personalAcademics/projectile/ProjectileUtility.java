package biz.personalAcademics.projectile;

public class ProjectileUtility {

	private static final double METERS_PER_YARD = .9144;
	private static final double YARDS_PER_METER = 1 / METERS_PER_YARD;
	private static final double METERS_PER_FOOT = .3048;
	private static final double ACCELERATION_FROM_GRAVITY = 9.81;

	/**
	 * accepts a boolean indicating where the toggle for meters or yards is
	 * selected. Converts measurement to meters depending on boolean
	 * 
	 * @param doNotConvert
	 * @param measurement
	 * @return
	 */
	public static double convertYardsToMeters(boolean doNotConvert, String measurement)
			throws InvalidMeasureException {
		double answerInMeters;
		try {
			
			if (doNotConvert) {
				answerInMeters = Double.parseDouble(measurement);
			} else {
				answerInMeters = METERS_PER_YARD
						* Double.parseDouble(measurement);
			}
			
		} catch (NumberFormatException e) {
			throw new InvalidMeasureException(measurement);
		}

		return answerInMeters;
	}

	/**
	 * must pass radio button for yards under calc distance text box
	 * 
	 * @param doNotConvert
	 * @param measurement
	 * @return
	 */
	public static double convertMetersToYards(boolean doConvert, double measurement) {
		double answerInYards;
		if (doConvert) {
			answerInYards = YARDS_PER_METER * measurement;
		} else {
			answerInYards = measurement;
		}

		return answerInYards;
	}

	/**
	 * accepts a boolean indicating where the toggle for meters or feet is
	 * selected. Converts measurement to meters depending on boolean
	 * 
	 * @param doNotConvert
	 * @param measurement
	 * @return
	 */
	public static double convertFeetToMeters(boolean doNotConvert, String measurement)
			throws InvalidMeasureException {
		double answerInMeters;
		try {
			if (doNotConvert) {
				answerInMeters = Double.parseDouble(measurement);
			} else {
				answerInMeters = METERS_PER_FOOT
						* Double.parseDouble(measurement);
			}
		} catch (NumberFormatException e) {
			throw new InvalidMeasureException(measurement);
		}

		return answerInMeters;
	}

	/**
	 * Calculates theta using the distance the user zeroed the firearm at
	 * @param zeroDistance
	 * @param muzzleVelocity
	 * @return
	 */
	public static double getThetaUsingZero(double zeroDistance, double muzzleVelocity) {
		return (Math.asin((ACCELERATION_FROM_GRAVITY * zeroDistance)
				/ (muzzleVelocity * muzzleVelocity))) / 2;
	}

	/**
	 * Calculates the time it takes for the projective to make it to the distance 
	 * the user wants to calculate drop for
	 * @param calcDistance
	 * @param muzzleVelocity
	 * @param theta
	 * @return
	 */
	public static double getTime(double calcDistance, double muzzleVelocity,
			double theta) {
		double timeInSeconds = calcDistance
				/ (muzzleVelocity * Math.cos(theta));
		return timeInSeconds * 1000;
	}

	/**
	 * Calculates drop of bullet relative to zero distance
	 * @param calcDistance
	 * @param muzzleVelocity
	 * @param theta
	 * @return
	 */
	public static double getHeightOfBulletInInches(double calcDistance,
			double muzzleVelocity, double theta) {
		double heightInMeters = (calcDistance * Math.tan(theta))
				- (ACCELERATION_FROM_GRAVITY / 2)
				* ((calcDistance * calcDistance) / ((muzzleVelocity * muzzleVelocity) * (Math
						.cos(theta) * Math.cos(theta))));
		return (heightInMeters / METERS_PER_FOOT) * 12;
	}

	public static String getUnitsInMetersOrYards(boolean metersSelected) {
		if (metersSelected) {
			return "meters";
		} else {
			return "yards";
		}

	}

}
