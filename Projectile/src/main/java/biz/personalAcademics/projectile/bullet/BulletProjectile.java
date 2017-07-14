package biz.personalAcademics.projectile.bullet;

import biz.personalAcademics.projectile.Projectile;

public class BulletProjectile extends Projectile {

	/**
	 * All parameters are expected to be metric. Use utility methods for
	 * conversion
	 */
	public BulletProjectile(double calcDistanceM, double zeroDistanceM, double muzzleVelocityM)
			throws ArithmeticException {
		super(calcDistanceM, zeroDistanceM, muzzleVelocityM);

		this.checkForImpossibleSituations();
	}

	/**
	 * Calculates theta using the distance the user zeroed the firearm at
	 * 
	 * @param zeroDistance
	 * @param muzzleVelocity
	 * @return
	 */
	private double getThetaUsingZero() {
		return (Math
				.asin((ACCELERATION_FROM_GRAVITY * this.zeroDistance) / (this.muzzleVelocity * this.muzzleVelocity)))
				/ 2;
	}

	/**
	 * Calculates the time it takes for the projective to make it to the
	 * distance the user wants to calculate drop for
	 * 
	 * @return
	 */
	private double getTime() {
		double timeInSeconds = this.calcDistance / (this.muzzleVelocity * Math.cos(this.getThetaUsingZero()));
		return timeInSeconds * 1000;
	}

	/**
	 * Calculates drop of bullet relative to zero distance
	 * 
	 * @return
	 */
	private double getHeightOfBulletInInches() {
		double heightInMeters = (this.calcDistance * Math.tan(this.getThetaUsingZero()))
				- (ACCELERATION_FROM_GRAVITY / 2)
						* ((this.calcDistance * this.calcDistance) / ((this.muzzleVelocity * this.muzzleVelocity)
								* (Math.cos(this.getThetaUsingZero()) * Math.cos(this.getThetaUsingZero()))));
		return (heightInMeters / METERS_PER_FOOT) * 12;
	}

	/**
	 * utility method for return the units at the end of a string.
	 * 
	 * returns 'meters' if true or 'yards' if false
	 * 
	 * @param metersSelected
	 * @return
	 */
	public static String getUnitsInMetersOrYards(boolean metersSelected) {
		if (metersSelected) {
			return "meters";
		} else {
			return "yards";
		}

	}

	/**
	 * return flight time in miliseconds
	 * 
	 * @return
	 */
	public double getFlightTime() {
		return this.getTime();
	}

	/**
	 * return angle of shot
	 * 
	 * @return
	 */
	public double getTheta() {
		return this.getThetaUsingZero();
	}

	/**
	 * return the height of the bullet at the desired distance (calcDistance) in
	 * inches.
	 * 
	 * Number will be relative to the zero distance. a positive value will mean
	 * the bullet is above the zero distance. A negative value means the bullet
	 * is below the zero distance
	 * 
	 * @return
	 */
	public double getBulletHeightInches() {
		return this.getHeightOfBulletInInches();
	}

	/**
	 * Checks if entered values produce impossible results
	 * 
	 * @param time
	 * @param height
	 * @throws ArithmeticException
	 */
	public void checkForImpossibleSituations() throws ArithmeticException {
		if (Double.isNaN(this.getFlightTime()) || Double.isInfinite(this.getFlightTime())) {
			throw new ArithmeticException();
		}

		if (Double.isNaN(this.getBulletHeightInches()) || Double.isInfinite(this.getBulletHeightInches())) {
			throw new ArithmeticException();
		}
	}

}
