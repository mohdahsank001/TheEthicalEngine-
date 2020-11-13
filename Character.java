package ethicalengine;

import java.util.Random;

public abstract class Character {

	/*
	 * enum Gender . 
	 * enum BodyType . 
	 * 
	 * Gender must include the types FEMALE and MALE as well as a default option UNKNOWN . 
	 * But can also include more diverse options if required, 
	 * 
	 * 
	 * 
	 * In computer programming, specifically object-oriented programming, a class invariant (or type invariant) is an invariant used for constraining objects of a class. Methods of the class should preserve the invariant. The class invariant constrains the state stored in the object.

      > Class invariants are established during construction and constantly maintained between calls to public methods.
      > Code within functions may break invariants as long as the invariants are restored before a public function ends.
	 * 
	 *
	 * Age should be considered as a class invariant for which the following statement should always hold true 
	 * age >= 0 . 
	 * 
	 * */

	
	private int age = 0 ; 
	public Gender gender ;
	private BodyType bodyType ; 
	
	
	/*
	 * 1. Gender must include types FEMALE, MALE AND UNKNOWN but also can include more diverse options 
	 * if you can choose to. - DONE
	 * 
	 * */
	public enum Gender {	
		
		FEMALE, MALE, UNKNOWN  ; 
		
		public static Gender getRandom() {
			
	        return values()[ new Random().nextInt(values().length) ];
	        
	    }
		
	}
	
	/*
	 * 2. BodyType would include the types average, athletic, overweight and unspecified. 
	 * 
	 * DONE . 
	 * */
	
	
	/*
	 * Classes inheriting from Character.java are : Person.java and Animal.java 
	 * 
	 * DONE
	 * 
	 * */
	
	public enum BodyType {
		
		AVERAGE, ATHLETIC, OVERWEIGHT, UNSPECIFIED  ; 
		
		public static BodyType getRandom() {
	
	        return values()[ new Random().nextInt(values().length) ];
	        
	    }
	
	}
	
	/*
	 * Make sure that the empty constructor initializes all of the attributes with the appropriate default values. 
	 * */
	public Character () {
		
		this.age = 0 ;  // age should be considered as a class invariant for which following statement always yield true age >= 0 
		this.gender = Gender.UNKNOWN ;
		this.bodyType = BodyType.UNSPECIFIED ; 
		
	}

	public Character(int age, Gender gender, BodyType bodyType) {
	
		this.age = age ;   
		this.gender = gender;
		this.bodyType = bodyType ; 
		
	}
	
	public Character (Character c) {
		
		this.age = c.getAge() ;   
		this.gender = c.getGender();
		this.bodyType = c.getBodyType() ; 
		
	}
	
	public int getAge() {
		
		return this.age ;
	}
	
	public void setAge(int age) {

		if(age >= 0 )
			this.age = age ;

	}

	public Gender getGender () {
		
		return this.gender;
		
	}
	
	public void setGender (Gender gender) {
		
		this.gender = gender ;
		
	}	

	public BodyType getBodyType () {
		
		return this.bodyType ; 
		
	}
	
	public void setBodyType ( BodyType bodytype) {
		
		this.bodyType = bodytype ; 
		
	}

	protected abstract boolean isYou();
	public abstract String toString();
}


