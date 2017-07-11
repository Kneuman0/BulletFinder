package biz.personalAcademics.projectile;

import biz.personalAcademics.excetions.InvalidMeasureException;

public abstract class Projectile {
	
	/*
	 * All instance fields will be stored as metric units (meters)
	 */
	protected double calcDistance, zeroDistance, muzzleVelocity;
	
	/*
	 * Constants
	 */
	public static final double METERS_PER_YARD = .9144;
	public static final double YARDS_PER_METER = 1 / METERS_PER_YARD;
	public static final double METERS_PER_FOOT = .3048;
	public static final double ACCELERATION_FROM_GRAVITY = 9.81;
	
	/**
	 * All parameters are expected to be metric. Use utility methods for conversion
	 */
	public Projectile(double calcDistanceM, double zeroDistanceM, double muzzleVelocityM){
		this.calcDistance = calcDistanceM;
		this.zeroDistance = zeroDistanceM;
		this.muzzleVelocity = muzzleVelocityM;
	}

	public double getCalcDistance() {
		return calcDistance;
	}

	public void setCalcDistance(double calcDistance) {
		this.calcDistance = calcDistance;
	}

	public double getZeroDistance() {
		return zeroDistance;
	}

	public void setZeroDistance(double zeroDistance) {
		this.zeroDistance = zeroDistance;
	}

	public double getMuzzleVelocity() {
		return muzzleVelocity;
	}

	public void setMuzzleVelocity(double muzzleVelocity) {
		this.muzzleVelocity = muzzleVelocity;
	}

	public static double getMetersPerYard() {
		return METERS_PER_YARD;
	}

	public static double getYardsPerMeter() {
		return YARDS_PER_METER;
	}

	public static double getMetersPerFoot() {
		return METERS_PER_FOOT;
	}

	public static double getAccelerationFromGravity() {
		return ACCELERATION_FROM_GRAVITY;
	}
	
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

}
