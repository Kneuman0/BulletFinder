package biz.personalAcademics.projectileTest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import biz.personalAcademics.projectile.InvalidMeasureException;
import biz.personalAcademics.projectile.ProjectileUtility;

public class ProjectileUtilityTest {
	
	boolean thetaFail = true;
	boolean timeFail = true;

	@Rule
	public ExpectedException invalidInput = ExpectedException.none();
	
	
	/**
	 * Tests time and theta in the same method to be sure they are executed sequentially
	 */
	@Before
	public void testPreliminaryCalculationsForThetaAndTime(){
		String theta = String.format("%.8f", ProjectileUtility.getThetaUsingZero(91.44, 723.9));
		if(theta.equals("0.00085589")){
			thetaFail = false;
		}else{
			fail(String.format("Theta did not pass: should be %s, but was %s", "0.00085589", theta));
			thetaFail = true;
		}
		
		if(thetaFail){
			fail("Theta did not pass therefore time will not pass");
		}else{
			
		double thetaDouble = ProjectileUtility.getThetaUsingZero(91.44, 723.9);
		String timeTest = String.format("%.4f", ProjectileUtility.getTime(182.88, 723.9, thetaDouble));
			if(timeTest.equals("252.6317")){
				timeFail = false;
			}else{
				timeFail = false;
				fail(String.format("time did not pass: should be %s, but was %s", "252.6317", timeTest));
			}
		}
	}
	
	/**
	 * Converts yards to meters
	 */
	@Test
	public void testConversionFromYardsToMeters() {
		assertThat(ProjectileUtility.convertYardsToMeters(false, "2"), is(2 * .9144));
	}
	
	/**
	 * Converts string yards to yards
	 */
	@Test
	public void testConversionFromYardsToYards(){
		assertThat(ProjectileUtility.convertYardsToMeters(true, "2"), is(2.0));
	}
	
	/**
	 * Converts meters to yards
	 */
	@Test
	public void testConversionFromMetersToYards(){
		assertThat(ProjectileUtility.convertMetersToYards(true, 2), is(2/(double).9144));
	}
	
	/**
	 * Converts meters to meters
	 */
	@Test
	public void testConversionFromMetersToMeters(){
		assertThat(ProjectileUtility.convertMetersToYards(false, 2), is(2.0));
	}
	
	/**
	 * Converts feet to meters
	 */
	@Test
	public void testConversionFromFeetToMeters(){
		assertThat(ProjectileUtility.convertFeetToMeters(false, "10"), is(10 * .3048));
	}
	
	/**
	 * Converts feet to feet
	 */
	@Test
	public void testConversionFromFeetToFeet(){
		assertThat(ProjectileUtility.convertFeetToMeters(true, "10"), is(10.0));
	}
	
	/**
	 * Tests units label for yards
	 */
	@Test
	public void testUnitsLabelYards(){
		assertThat(ProjectileUtility.getUnitsInMetersOrYards(false), is("yards"));
	}
	
	/**
	 * Tests units label for meters
	 */
	@Test
	public void testUnitsLabelMeters(){
		assertThat(ProjectileUtility.getUnitsInMetersOrYards(true), is("meters"));
	}
	
	/**
	 * Test feet conversion from feet to feet for non-numbers
	 */
	@Test
	public void testForInvalidUserInputFeetTrue(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		ProjectileUtility.convertFeetToMeters(true, "letters");
	}
	
	/**
	 * Test feet conversion from feet to meters for non-numbers
	 */
	@Test
	public void testForInvalidUserInputFeetFalse(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		ProjectileUtility.convertFeetToMeters(false, "letters");
	}
	
	/**
	 * Test feet conversion from yards to meters for non-numbers
	 */
	@Test
	public void testForInvalidUserInputYardsToMetersFalse(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		ProjectileUtility.convertYardsToMeters(false, "letters");
	}
	
	/**
	 * Test feet conversion from yards to yards for non-numbers
	 */
	@Test
	public void testForInvalidUserInputYardsToMetersTrue(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		ProjectileUtility.convertYardsToMeters(true, "letters");
	}
	
		
	/**
	 * Method MUST execute after theta and time have been tested
	 */
	@Test
	public void testHeightAtCalcDistance(){
		if(thetaFail || timeFail){
			fail("Theta or time did not pass, therefore height will not pass");
		}else{
			double theta = ProjectileUtility.getThetaUsingZero(91.44, 723.9);
			String height = String.format("%.4f", ProjectileUtility.getHeightOfBulletInInches(182.88, 723.9, theta));
			assertThat("-6.1624", is(height));
			
		}
	}
	

}
