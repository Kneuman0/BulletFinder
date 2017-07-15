package biz.personalAcademics.mainapp;

import biz.ui.launchers.generic.AppLauncher;

public class ProjectileCalcMain extends AppLauncher<ProjectileCalcController> {
	/**
	 * creates application in memory
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public String getPathtoFXML() {
		return "/resources/ProjectileGUI.fxml";
	}

	@Override
	public String getStageTitle() {
		return "Bullet Finder";
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
