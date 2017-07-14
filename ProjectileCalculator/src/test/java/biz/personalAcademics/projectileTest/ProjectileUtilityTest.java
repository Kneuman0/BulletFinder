package biz.personalAcademics.projectileTest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import biz.personalAcademics.excetions.InvalidMeasureException;
import biz.personalAcademics.mainapp.BulletProjectile;
import biz.personalAcademics.mainapp.Projectile;

public class ProjectileUtilityTest {
	
	boolean thetaFail = true;
	boolean timeFail = true;
	BulletProjectile bullet = new BulletProjectile(182.88, 91.44, 723.9);

	@Rule
	public ExpectedException invalidInput = ExpectedException.none();
	
	
	
	/**
	 * Tests time and theta in the same method to be sure they are executed sequentially
	 */
	@Before
	public void testPreliminaryCalculationsForThetaAndTime(){
		String theta = String.format("%.8f", bullet.getTheta());
		if(theta.equals("0.00085589")){
			thetaFail = false;
		}else{
			fail(String.format("Theta did not pass: should be %s, but was %s", "0.00085589", theta));
			thetaFail = true;
		}
		
		if(thetaFail){
			fail("Theta did not pass therefore time will not pass");
		}else{
			
		double thetaDouble = bullet.getTheta();
		String timeTest = String.format("%.4f", bullet.getFlightTime());
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
		assertThat(Projectile.convertYardsToMeters(false, "2"), is(2 * .9144));
	}
	
	/**
	 * Converts string yards to yards
	 */
	@Test
	public void testConversionFromYardsToYards(){
		assertThat(Projectile.convertYardsToMeters(true, "2"), is(2.0));
	}
	
	/**
	 * Converts meters to yards
	 */
	@Test
	public void testConversionFromMetersToYards(){
		assertThat(Projectile.convertMetersToYards(true, 2), is(2/(double).9144));
	}
	
	/**
	 * Converts meters to meters
	 */
	@Test
	public void testConversionFromMetersToMeters(){
		assertThat(Projectile.convertMetersToYards(false, 2), is(2.0));
	}
	
	/**
	 * Converts feet to meters
	 */
	@Test
	public void testConversionFromFeetToMeters(){
		assertThat(Projectile.convertFeetToMeters(false, "10"), is(10 * .3048));
	}
	
	/**
	 * Converts feet to feet
	 */
	@Test
	public void testConversionFromFeetToFeet(){
		assertThat(Projectile.convertFeetToMeters(true, "10"), is(10.0));
	}
	
	/**
	 * Tests units label for yards
	 */
	@Test
	public void testUnitsLabelYards(){
		assertThat(BulletProjectile.getUnitsInMetersOrYards(false), is("yards"));
	}
	
	/**
	 * Tests units label for meters
	 */
	@Test
	public void testUnitsLabelMeters(){
		assertThat(BulletProjectile.getUnitsInMetersOrYards(true), is("meters"));
	}
	
	/**
	 * Test feet conversion from feet to feet for non-numbers
	 */
	@Test
	public void testForInvalidUserInputFeetTrue(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		Projectile.convertFeetToMeters(true, "letters");
	}
	
	/**
	 * Test feet conversion from feet to meters for non-numbers
	 */
	@Test
	public void testForInvalidUserInputFeetFalse(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		Projectile.convertFeetToMeters(false, "letters");
	}
	
	/**
	 * Test feet conversion from yards to meters for non-numbers
	 */
	@Test
	public void testForInvalidUserInputYardsToMetersFalse(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		Projectile.convertYardsToMeters(false, "letters");
	}
	
	/**
	 * Test feet conversion from yards to yards for non-numbers
	 */
	@Test
	public void testForInvalidUserInputYardsToMetersTrue(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		Projectile.convertYardsToMeters(true, "letters");
	}
	
		
	/**
	 * Method MUST execute after theta and time have been tested
	 */
	@After
	public void testHeightAtCalcDistance(){
		if(thetaFail || timeFail){
			fail("Theta or time did not pass, therefore height will not pass");
		}else{
			String height = String.format("%.4f", bullet.getBulletHeightInches());
			assertThat("-6.1624", is(height));
			
		}
	}
	

}
