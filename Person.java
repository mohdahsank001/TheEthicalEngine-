package ethicalengine;

/*
 * The Classes inheriting from Character.java : 
 * 1. Person.java  .
 * 2, Animal.java  .
 * 
 * 
 * */
public class Person extends Character {
	
	private String charactertype ; 
	private Profession profession  ; 
	public boolean isrepresentive ; 
	private boolean isPregnant = false ;  
	private boolean isYou = false; 
    AgeCategory agecategory ; 
	private String agegroup  ; 
	


	public Person (int age, Gender gender, BodyType bodytype){
		
		super.setAge(age) ; 
		this.profession = profession ; 
		super.setGender(gender); 
		super.setBodyType(bodytype);
		
	}



	public Person(){

		
	}

	//Person [ ] personArray = new Person[7] ;
	
	public Person (int age, Profession profession, Gender gender, BodyType bodytype, boolean isPregnant){
		
		super.setAge(age) ; 
		this.profession = profession ; 
		super.setGender(gender); 
		super.setBodyType(bodytype);
		this.setPregnant(isPregnant) ; 
		
	}
	

	public Person (int age, Profession profession, Gender gender, BodyType bodytype){
		
		super.setAge(age) ; 
		this.profession = profession ; 
		super.setGender(gender); 
		super.setBodyType(bodytype);
		
	}

	// Copy Constructor in Java
	/*
	 * A Copy constructor in Java is a constructor that creates an object using another object of the same Java Class. 
	 * That is helpful when we want to copy a complex object that has several fields, or when we want to make a deep copy 
	 * of an existing object. 
	 * */
	
	
	 public Person (Person otherPerson){
	
		this.charactertype = otherPerson.charactertype ; 
		this.profession = otherPerson.profession ; 
		super.setAge( otherPerson.getAge() ); 
		this.gender = otherPerson.gender ; 
		this.isrepresentive = otherPerson.isrepresentive ; 
		super.setBodyType( otherPerson.getBodyType() ); 
		this.setPregnant( otherPerson.isPregnant ); 
		this.setAsYou(otherPerson.isYou) ; 
		
	}
	 	
	// Enum for age category.   
	
	 public enum AgeCategory {
		
		BABY, CHILD, ADULT, SENIOR  ;
		
	}
	
	// Only ADULTs have professions, other age categories should return the default value NONE.
	// Additionally, you are tasked with coming up with at least two more categories you deem feasible. 
	
	
	//
	 public enum Profession {
		
		DOCTOR,CEO,CRIMINAL,HOMELESS,UNEMPLOYED, UNKNOWN, NONE ;
		
	}
	
	
	/*getAgeCategory() - returns an enumeration value of the type AgeCategory depending
	 * on the persons age with one of the following values .
	 * */
	public AgeCategory getAgeCategory() {
		
		if (0 <= super.getAge() && super.getAge() <= 4) {
			
			return AgeCategory.BABY ;
		} 		
	
		else if ( 4 < super.getAge() && super.getAge() <= 16 ) {
			
			return AgeCategory.CHILD ;
		}
		
		else if (16 < super.getAge() && super.getAge() <= 68) {
			
			return AgeCategory.ADULT ; 	
		}
		else  {
			
			return AgeCategory.SENIOR ; 
		}
		
	}
	
	
	// getProfession returns an enumeration value of type Profession, which must include the values as above. 
	// Only ADULTs would have professions , other age categories should return the default value UNKNOWN . 
	// 

	public Profession getProfession ( ) {
		
		
		if ( this.getAgeCategory() != AgeCategory.ADULT ) {
			
			return Profession.NONE ;
		
		}
		else 
			return this.profession;
		
		
	}
	
	// returns a boolean indicating whether the person is pregnant.
	// For all instances of Person whose gender is not FEMALE this should return false. 
	
	private boolean checkPregnant ( ) {
		
		if (getGender() == Gender.FEMALE && getAgeCategory() == AgeCategory.ADULT) {
			
			return true  ;

		}
		else
			return false;
	}
	
	public boolean isPregnant()
	{
		return this.isPregnant;
	}
	
	
	//  sets the value returned by isPregnant() while preventing invalid states, such as a pregnant male. 
	public void setPregnant (boolean pregnant) {
		
		if (checkPregnant()) { 
				  
			this.isPregnant = pregnant ; 
			   
		}
		else
			this.isPregnant = false;
	}
	
	// Returns a boolean indicating whether a person is a representative of the user eg : you are one of the passengers in the car. 
	
	public void setAsYou (boolean isYou) {
		
		this.isYou = isYou ;
	}
	
	//  isYou(): returns a boolean indicating whether the person is representative of the user, 
	//  e.g., you are one of the passengers in the car. 

	
    public boolean isYou() {
		
		return this.isYou ; 
	}
	
	 
	public String toString() {
		
		String rtnStr = "";
		
		if(isYou()) {
			
			rtnStr += "you "; 
		}
		
		rtnStr += super.getBodyType()+" ";
		
		rtnStr += this.getAgeCategory()+" ";
		
		if (getProfession() != Profession.NONE) {
			
			rtnStr += getProfession()+" ";
		}
		
		rtnStr += super.getGender()+" ";
		
		if (isPregnant()) {
			
			rtnStr += "pregnant";
		}
		
		return rtnStr.toLowerCase();
		
	}
	

	
/*	public static Person gettheRandomPerson() {
		// TODO Auto-generated method stub
		return null;
		
		
	}

*/
	
	
	
	

}

	

	

	
	
	
	
		
		
	
	
	
	

