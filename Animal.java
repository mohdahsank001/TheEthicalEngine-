package ethicalengine;

public class Animal extends Character {
	
	Animal otherAnimal ;
	String species ;
	boolean isbpet = false;
	
	public Animal() {


	}

	public Animal (String species ) {
		
		this.species = species ;
			
	}
	
	
	public Animal (Animal otherAnimal) {
		
		this.otherAnimal = otherAnimal;
	//this.species = otherAnimal.getSpecies() ;
	//	this.ispet = otherAnimal.getSpecies() ;
		
	}
	
	public void setSpecies(String species) {
		
		this.species = species ;
	
	}


	public void setPet(boolean isPet)
	{
		this.isbpet = isPet;
	}

	public void setAsPet(boolean isPet)
	{
		this.isbpet = isPet;
	}
	
	public String getSpecies () {
		
		return this.species ; 
		
	}
	
	
	public boolean isPet() {
		
		return this.isbpet;
		 
	}
	
	
	public String toString () {
		
		String rtnStr = "";
		
		rtnStr += getSpecies();
		if(isPet()) {
			rtnStr += " is pet";
		}
		
		return rtnStr.toLowerCase()  ; 
		
	}
	
    public boolean isYou() {
		
		return false ; 
	}
	
	
	
}
