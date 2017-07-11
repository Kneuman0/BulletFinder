package biz.personalAcademics.projectile;

import java.text.DecimalFormat;

import biz.personalAcademics.excetions.InvalidMeasureException;
import biz.personalAcademics.excetions.MeasureTooBigException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class ProjectileCalcController {

    @FXML
    private RadioButton 
    		zeroYrdsTog,
    		zeroMtrsTog,
    		muzzleMtrsPSecTog,
    		calcDistYrdsTog,
    		muzzleFtPSecTog,
    		calcDistMtrsTog;

    @FXML
    private Label 
    		userWarningLabel,
    		answerLabel,
    		timeLabel;
    
    @FXML
    private TextField 
    		muzzleVelocText,
    		zeroDistText,
    		calcDistText;

    @FXML
    private ToggleGroup 
    		zeroDistance,
    		muzzleVelocity,
    		calcDistance;
    
    @FXML
    private Button calculateButton;
    
    @FXML
    private AnchorPane anchorPane;
    
    DecimalFormat inches;
    
    public void initialize(){
    	inches = new DecimalFormat("0.000");
    	setBackgroundImage();
    }
    
    
    
    public void calcButtonListener(){
    	answerLabel.setText("");
    	timeLabel.setText("");
    	userWarningLabel.setText("");
    	
    	if(ensureAllEntriesLogged()){
    		return;
    	}
    	
		
		BulletProjectile bullet = null;
		try {
			// convert shooters zero distance to meters
			double zeroDistanceM = Projectile.convertYardsToMeters(zeroMtrsTog.isSelected(), zeroDistText.getText());
			// convert muzzle velocity to meters per second
			double muzzleVelocityM = Projectile.convertFeetToMeters(muzzleMtrsPSecTog.isSelected(), muzzleVelocText.getText());
			double calcDistanceM = Projectile.convertYardsToMeters(calcDistMtrsTog.isSelected(), calcDistText.getText());
			// angle of muzzle
			
			bullet = new BulletProjectile(calcDistanceM, zeroDistanceM, muzzleVelocityM);
		} catch (InvalidMeasureException e) {
			userWarningLabel.setText(e.getMessage());
			return;
		} catch (ArithmeticException error){
			userWarningLabel.setText("You have entered impossible parameters");
			return;
		}
		
		// Checks to see if user number is too big to avoid double overflow
		try {
			CheckIfMeaurementSizeIsTooBig();
		} catch (MeasureTooBigException e) {
			userWarningLabel.setText(e.getMessage());
			return;
		}
			
    	// sets the label indicating the time it takes the bullet to travel the user specified distance
    	timeLabel.setText(String.format("It will take %.2f miliseconds to travel %.1f %s", bullet.getFlightTime(), 
    	Projectile.convertMetersToYards(calcDistYrdsTog.isSelected(), bullet.getCalcDistance()), 
    	BulletProjectile.getUnitsInMetersOrYards(calcDistMtrsTog.isSelected())));
    	// sets the label telling the user the height of the specified distance
    	answerLabel.setText(String.format("%.2f inches relative to zeroed distance", bullet.getBulletHeightInches()));
    	
    }
    
    private boolean ensureAllEntriesLogged(){
    	boolean incompleteForm = false;
    	if(muzzleVelocText.getText().equals("")){
    		userWarningLabel.setText("Please enter a muzzle velocity");
    		incompleteForm = true;
    	}
    	
    	if(zeroDistText.getText().equals("")){
    		userWarningLabel.setText("Please enter a zero distance");
    		incompleteForm = true;
    	}
    	
    	if(calcDistText.getText().equals("")){
    		userWarningLabel.setText("Please enter a distance to calculate for");
    		incompleteForm = true;
    	}
    	
    	return incompleteForm;
    }
    
    private void CheckIfMeaurementSizeIsTooBig() throws MeasureTooBigException{
    	if(muzzleVelocText.getText().length() > 6){
    		throw new MeasureTooBigException(muzzleVelocText.getText());
    	}
    	
    	if(zeroDistText.getText().length() > 6){
    		throw new MeasureTooBigException(zeroDistText.getText());
    	}
    	
    	if(calcDistText.getText().length() > 6){
    		throw new MeasureTooBigException(calcDistText.getText());
    	}
    }
    

    
    private void setBackgroundImage(){
    	Image logo = new Image(ProjectileCalcMain.class.getResourceAsStream("/resources/diamondPlate.jpg"));
    	BackgroundSize logoSize = new BackgroundSize(600, 400, false, false, true, true);
		 BackgroundImage image = new BackgroundImage(logo, BackgroundRepeat.NO_REPEAT, 
				 BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, logoSize);
		 Background background = new Background(image);
		 anchorPane.setBackground(background);
    }

}
