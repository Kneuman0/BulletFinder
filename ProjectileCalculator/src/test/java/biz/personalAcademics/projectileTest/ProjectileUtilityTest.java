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
			fail("Theta did not pass therefor time will not pass");
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
	
	@Test
	public void testConversionFromYardsToMeters() {
		assertThat(ProjectileUtility.convertYardsToMeters(false, "2"), is(2 * .9144));
	}
	
	@Test
	public void testConversionFromMetersToYards(){
		assertThat(ProjectileUtility.convertMetersToYards(true, 2), is(2/(double).9144));
	}
	
	@Test
	public void testConversionFromFeetToMeters(){
		assertThat(ProjectileUtility.convertFeetToMeters(false, "10"), is(10 * .3048));
	}
	
	@Test
	public void testUnitsLabel(){
		assertThat(ProjectileUtility.getUnitsInMetersOrYards(false), is("yards"));
	}
	
	@Test
	public void testForInvalidUserInputFeetTrue(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		ProjectileUtility.convertFeetToMeters(true, "letters");
	}
	
	@Test
	public void testForInvalidUserInputFeetFalse(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		ProjectileUtility.convertFeetToMeters(false, "letters");
	}
	
	@Test
	public void testForInvalidUserInputYardsToMetersFalse(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		ProjectileUtility.convertYardsToMeters(false, "letters");
	}
	
	@Test
	public void testForInvalidUserInputYardsToMetersTrue(){
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(containsString("letters"));
		ProjectileUtility.convertYardsToMeters(true, "letters");
	}
	
		
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
