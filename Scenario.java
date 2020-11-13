package ethicalengine;

import java.util.Random ;

import ethicalengine.Person.Profession; 

public class Scenario {

	// Each case can have only one instance of the Person. 
	// control variable to set the value of the legal crossing. 
	 
	public boolean hasyouincar ;
	private boolean hasyouinlane ;
	
	private Character[] passengers = {} ;
	private Character[] pedestrians = {} ; 
	 
	
	public boolean legalcrossing  ; 
	public boolean pedsontheline ; 
	
	
	
	/*
	 * 
	 * You can use arrays or array lists but you need to make sure that this constructor 
	 * takes a person array as an argument. 
	 * 
	 * */
	
    public boolean isPedsontheline() {
		return pedsontheline;
	}

	public void setPedsontheline(boolean pedsontheline) {
		this.pedsontheline = pedsontheline;
	}

	// This constructor needs to take the person array as an argument . 
	public Scenario (Character[] passengers, Character[] pedestrians, boolean isLegalCrossing){
		
		this.passengers = passengers ; 
		this.pedestrians = pedestrians ; 
		this.legalcrossing = isLegalCrossing  ; 
	
	}
	
	// returns a boolean to indicate whether the user is in the car. 
	public boolean hasYouInCar() {
	
		for (Character character : this.passengers) {
			
			
			if (character.isYou()) {	 // how to use the array in the person class here.  -> MAJOR FIX REQUIRED . 
				
				return true ; 
			}
		}
		return false ;	
	}

	
	// 
	public boolean hasYouInLane() {
		
		if(hasYouInCar())
			return false;
		
		for (Character character : this.pedestrians) {
			
			
			if (character.isYou()) {	 // how to use the array in the person class here.  -> MAJOR FIX REQUIRED . 
				
				return true ; 
			}
		}
		return false ;	
		
	}	
	

	public Character[] getPassengers() {
		
		return passengers  ;
		 
	}

	

	public Character[] getPedestrians() {
		
		return pedestrians ;
		
	}

	// pedcross is the flag variable to check if the pedestrians are crossing the street normally or not 
	public boolean isLegalCrossing( ) {
		 
		return this.legalcrossing;
	}

 
	public void setLegalCrossing ( boolean isLegalCrossing ) {
		
			this.legalcrossing = isLegalCrossing;
	}

	
	public int getPassengerCount() {
		
		return  this.passengers.length  ; 
		
	}
	
	
	public int getPedestrianCount() {
		
		return this.pedestrians.length  ;
	}
	
	
	public String toString() {
		
		String rtnStr = "======================================";		
		rtnStr += "\n# Scenario";
		rtnStr += "\n======================================";
		
		if(this.isLegalCrossing())
			rtnStr += "\nLegal Crossing: yes";
		else
			rtnStr += "\nLegal Crossing: no";
		
		rtnStr += "\nPassengers: ("+ this.getPassengerCount() +")";
 	
		
		for (Character character : this.passengers) {
			
			rtnStr += "\n- "+character.toString();  
							
		}
		
		rtnStr += "\nPedestrian: ("+ this.getPedestrianCount() +")";
		
		for (Character character : this.pedestrians) {
			
			rtnStr += "\n- "+character.toString();  
							
		}
		 		
		return rtnStr;
		
	}
		
}
