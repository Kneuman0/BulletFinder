package biz.personalAcademics.projectileTest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import biz.personalAcademics.projectile.InvalidMeasureException;
import biz.personalAcademics.projectile.ProjectileUtility;

public class ProjectileUtilityTest {

	@Rule
	public ExpectedException invalidInput = ExpectedException.none();
	
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
	
	

}
