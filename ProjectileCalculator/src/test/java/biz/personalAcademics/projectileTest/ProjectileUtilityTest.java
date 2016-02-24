package biz.personalAcademics.projectileTest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.core.StringContains;
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
		ProjectileUtility projectile = new ProjectileUtility();
		assertThat(projectile.convertYardsToMeters(false, "2"), is(2 * .9144));
	}
	
	@Test
	public void testConversionFromMetersToYards(){
		ProjectileUtility projectile = new ProjectileUtility();
		assertThat(projectile.convertMetersToYards(true, 2), is(2/(double).9144));
	}
	
	@Test
	public void testConversionFromFeetToMeters(){
		ProjectileUtility projectile = new ProjectileUtility();
		assertThat(projectile.convertFeetToMeters(false, "10"), is(10 * .3048));
	}
	
	@Test
	public void testUnitsLabel(){
		ProjectileUtility projectile = new ProjectileUtility();
		assertThat(projectile.getUnitsInMetersOrYards(false), is("yards"));
	}
	
	@Test
	public void testForInvalidUserInputFeetTrue(){
		ProjectileUtility projectile = new ProjectileUtility();
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(StringContains.containsString("letters"));
		projectile.convertFeetToMeters(true, "letters");
	}
	
	@Test
	public void testForInvalidUserInputFeetFalse(){
		ProjectileUtility projectile = new ProjectileUtility();
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(StringContains.containsString("letters"));
		projectile.convertFeetToMeters(false, "letters");
	}
	
	@Test
	public void testForInvalidUserInputYardsToMetersFalse(){
		ProjectileUtility projectile = new ProjectileUtility();
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(StringContains.containsString("letters"));
		projectile.convertYardsToMeters(false, "letters");
	}
	
	@Test
	public void testForInvalidUserInputYardsToMetersTrue(){
		ProjectileUtility projectile = new ProjectileUtility();
		invalidInput.expect(InvalidMeasureException.class);
		invalidInput.expectMessage(StringContains.containsString("letters"));
		projectile.convertYardsToMeters(true, "letters");
	}
	
	

}
