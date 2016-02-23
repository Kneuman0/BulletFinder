package biz.personalAcademics.projectileTest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import biz.personalAcademics.projectile.ProjectileUtility;

public class ProjectileUtilityTest {

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
	
	

}
