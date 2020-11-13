package ethicalengine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random ;

import ethicalengine.Character.BodyType;
import ethicalengine.Character.Gender;
import ethicalengine.Person.Profession;



public class ScenarioGenerator 
{
		
	private String charType ; 
	private static long seed  = 42; 
	private boolean body; 
	
	// To be able to properly test your scenarios and make sure your results are replicated
	// you must apply psuedo randomness . Therefore you need to familiarize yourself first with the class java.util.random
	// especially with the function setSeed (long seed) . 
	
	public static String[] characters = {"human", "human","animal", "human",  "human"} ; 
	
	public static String[] animals = {"cat", "dog", "bird",  "deer"} ; 
	public static String[] agetypes = {"baby", "child", "adult", "adult", "adult", "senior"} ; 
	
	public static Person.Profession[] proftypesenum = {Person.Profession.DOCTOR, Person.Profession.CEO, Person.Profession.UNKNOWN, Person.Profession.HOMELESS, Person.Profession.UNKNOWN, Person.Profession.CRIMINAL, Person.Profession.UNEMPLOYED, Person.Profession.UNKNOWN } ;
	
	public static boolean[] pregnantchance = { false, false, true, false } ; 
	
	private static Random random  ;
	
	// Setting the limits on the number of passengers . 
	
	private static int minpassengers = 1 ; 
	private static int maxpassengers = 5 ; 
	private static int minpedestrians = 1 ; 
	private static int maxpedestrians = 5 ; 
	private static int MAX_AGE = 120;
	
	public static boolean [] chance_foryou = { true, false, false, false } ; 
	public static boolean [] chance_for_legal_crossing = { true, true, false } ; 
	public static boolean [] chance_for_pedsinline = { true, false } ; 
	public static boolean [] chance_for_samenumber = { true, false } ; 
	
	// This constructor should set the seed to a truly random number 
	// The constructor have set the value of seed to a truly random number . 
	public ScenarioGenerator( ) {
		
		 this.seed = random.nextLong() ;
		 random = new Random(this.seed);
		
	}
	
	
	// This constructor would set the seed with a predefined value  
	// What would be the predefined value. 
	public ScenarioGenerator (long seed) {
		
		this.seed = seed ;
		 random = new Random(this.seed);
		
	}
	
	// This constructor would set the seed as well as the minimum and max number for both the passengers and peds with predefined values. 
	public ScenarioGenerator( long seed, int passengerCountMinimum, int passengerCountMaximum, int pedestrianCountMinimum, int pedestrianCountMaximum ) {
		
		// What are the predefined values .  ? 
		this.seed = seed   ; 
		
		if( passengerCountMaximum >= passengerCountMinimum )
		{
			this.minpassengers = passengerCountMinimum   ; 
			this.maxpassengers = passengerCountMaximum  ;
		}
		
		if( pedestrianCountMaximum >= pedestrianCountMinimum )
		{
			this.minpedestrians = pedestrianCountMinimum  ; 
			this.maxpedestrians = pedestrianCountMaximum ;
		}
	}
	
	
	// sets the minimum number of car passengers for each scenario . 
	public void setPassengerCountMin (int min) {
		
		this.minpassengers = min ; 
		
	}
	
	
	// sets the maximum number of car passengers for each of the scenario . 
	public void setPassengerCountMax (int max) {
		
		this.maxpassengers = max ;
	
	}
	
	public void setPedestrianCountMin (int min) {
		
		this.minpedestrians = min   ; 
		
		
		
	}

	public void setPedestrianCountMax (int max) {
		
		this.maxpedestrians = max   ; 
			
	}
	
	public void setPedestrianMax (int max) {
		
		this.maxpedestrians = max   ; 
			
	}
	
	/*
	 * getRandomPerson() which returns a newly created instance of Person with a 
	 * random age, gender, bodyType, profession, and state of pregnancy . 
	 * 
	 * getRandomAnimal() would return a newly created instance of Animal with a 
	 * random age, gender, bodyType, species and whether it is not. 
	 * 
	 * the public method generate() which returns a newly created instance of Scenario containing 
	 * a random number of passengers and pedestrians with random characteristics as well as a randomly 
	 * red or green light condition with you (the user) being in the car, or the street or abscent. 
	 * 
	 * 
	 * 
	 * */
	

	
	public Person getRandomPerson () {
		
		int age = random.nextInt(MAX_AGE) ;
		Character.Gender gender = Character.Gender.getRandom();
		
		Character.BodyType bodyType = Character.BodyType.getRandom();
		
		Person.Profession profession = ScenarioGenerator.randomProfEnum(proftypesenum);
		
		boolean isPregnant = ScenarioGenerator.randomBoolean(pregnantchance);
		
		Person per = new Person(age, profession, gender, bodyType, isPregnant );
		
		boolean isYou = ScenarioGenerator.randomBoolean(chance_foryou);
		
		per.setAsYou(isYou);
		
		return per;
	}
	
	
	public Animal getRandomAnimal() {
		//random age, gender, bodyType, species, and whether it is a pet or not

		//System.out.println(" test output here ")  ; 
		
		Animal ani = new Animal(randomString(animals));
		
		int age = random.nextInt(15) ;		
		Character.BodyType bodyType = Character.BodyType.getRandom();
		
		if (randomString(animals).equals("cat") || randomString(animals).equals("dog"))
			ani.setAsPet(true);
		else
			ani.setAsPet(false);
		
		ani.setAge(age);
		ani.setBodyType(bodyType);
		
		return ani;
		
	}
	
	public static String randomString (String[] array) {
		
		int index = random.nextInt(array.length) ;
		return array[index] ;
		
	}
	
	public static Person.Profession randomProfEnum (Person.Profession[] array) {
		
		int index = random.nextInt(array.length) ;
		return array[index] ;
		
	}
	
	
	
	public static boolean randomBoolean (boolean[] array) {
		int index = random.nextInt(array.length) ; 
		return array[index] ;
	}
	
	
	// This would return a newly created instance of Scenario containing 
	// a random number of passengers and pedestrians with random characteristics. 
	// as well as red or green lights with you with the user being in the car, on the street or abscent. 
	// Method to generate random scenarios. 
	
	
	/*
	 * AUDITING THE ALGORITHM - TO BE DONE 
	 * 
	 * 1. creating a specific number of random scenarios . 
	 * 2. have ethical engine decide on each of the outcomes . 
	 * 3. And summarize the results for each of the characteristics in a so called statistic of project survival . 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	
	
	public Scenario generate()
	{
		
		int numberofpedestrians = randombetweentwo (minpedestrians, maxpedestrians);
		int numberofpassengers  = randombetweentwo (minpassengers, maxpassengers); 
		
		boolean legalCrossing = randomBoolean(chance_for_legal_crossing);
		
		boolean pedsInLane = randomBoolean(chance_for_pedsinline);
		
		Character[] pedestrians = new Character[numberofpedestrians];
		Character[] passengers = new Character[numberofpassengers];
		
		boolean isLegalCrossing;
		
		for(int i = 0; i<numberofpedestrians; i++)
		{
			if(randomString(characters).equals("human"))
				pedestrians[i] = (Character)getRandomPerson();
			else
				pedestrians[i] = (Character)getRandomAnimal();
		}
		
		for(int i = 0; i<numberofpassengers; i++)
		{
			if(randomString(characters).equals("human"))
				passengers[i] = (Character)getRandomPerson();
			else
				passengers[i] = (Character)getRandomAnimal();
		}
		
		Scenario sc = new  Scenario(passengers, pedestrians, legalCrossing);
		
		sc.setPedsontheline(pedsInLane);
		
		return sc;
		
	}
	
	
	public static int randombetweentwo (int min, int max ) {
		
		int diff = random.nextInt((max-min)+1) ; 
		
		return min + diff ; 
		
	}
	
	
	public static String randomString1 (String[ ] array) {
		
		int index = random.nextInt(array.length) ;
		
		return array[index] ; 
		
	}
	
	public static Scenario[] readFromCSVFile(String csvFile) 
	{
		Random random = new Random(seed) ;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        //Character[] pedestrians = new Character[numberofpedestrians];
		//Character[] passengers = new Character[numberofpassengers];
		//Scenario sc = new  Scenario(passengers, pedestrians, legalCrossing);
        Scenario sc ;
        ArrayList <Scenario> scArray = new ArrayList();
        ArrayList<Character> pedestrians = new ArrayList();
        ArrayList<Character> passengers = new ArrayList();
        boolean boolLegal;
        boolean isYou;
        
        //System.out.println("csvFile "+csvFile);

        try {

            br = new BufferedReader(new FileReader(csvFile));
            int lineCount = 0;
            while ((line = br.readLine()) != null)
            {
            	
            	
            	lineCount++;
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                
                try
                {
                                	
                	if( data.length !=10  && !data[0].startsWith("scenario:") )
                		throw new InvalidDataFormatException(""); 
                	
                	if(lineCount ==1 )
                		continue;
                	    
                	//System.out.println(data[0]+" -> lineCount "+lineCount);
                	
                	if( data[0].startsWith("scenario:") )
                	{
                		
                		if( lineCount != 2 )
                		{          	
                			
                			sc = new  Scenario(passengers.toArray(new Character[passengers.size()]), pedestrians.toArray(new Character[pedestrians.size()]), true);
                			scArray.add(sc);   
                			passengers.clear();
                			pedestrians.clear();
                		}
                		
                		if( data[0].substring("scenario:".length()).equals("red"))
                			boolLegal = false;	
                		else if( data[0].substring("scenario:".length()).equals("green"))
                			boolLegal = true;
                		
                		continue;
                	}
                	
                	int age ;
                	
					try
					{
						age = Integer.parseInt(data[2]);
						
					} catch (NumberFormatException ex)
					{  
					    System.out.println("WARNING: invalid number format in config file in line "+lineCount); 
					    age = random.nextInt(120); 
					}
					
					BodyType bodyType;
					
					try
					{			
						 boolean check_flag = false;
						 for(BodyType body: BodyType.values())
						 {
					           if (body.name().equals(data[3].toUpperCase()))
					        	   check_flag = true;
						 }
						 
					     if(check_flag == false)
					    	 throw new InvalidCharacteristicException("BodyType");

						
						
						bodyType = BodyType.valueOf(data[3].toUpperCase());
												
					} catch (InvalidCharacteristicException ex)
					{  
						System.out.println("WARNING: invalid characteristic in config file in line "+lineCount);
						bodyType = Character.BodyType.getRandom();
						
					}

                	
                	if(data[0].equals("person"))
                	{
                		
                		Profession profession;
                		Gender gender;
                		
                		

                		
						try
						{
							
							 boolean check_flag = false;
							 for(Profession prof: Profession.values())
							 {
						           if (prof.name().equals(data[4].toUpperCase()))
						        	   check_flag = true;
							 }
							 
						     if(check_flag == false)
						    	 throw new InvalidCharacteristicException("Profession");
						      
							
							profession = Person.Profession.valueOf(data[4].toUpperCase());
							
													
						} catch (InvalidCharacteristicException ex)
						{  
							System.out.println("WARNING: invalid characteristic in config file in line "+lineCount);
							
							profession = ScenarioGenerator.randomProfEnum(proftypesenum);

						}
						
						try
						{	
							
							 boolean check_flag = false;
							 for(Gender gen: Gender.values())
							 {
						           if (gen.name().equals(data[1].toUpperCase()))
						        	   check_flag = true;
							 }
							 
						     if(check_flag == false)
						    	 throw new InvalidCharacteristicException("Gender");
							
							gender = Gender.valueOf(data[1].toUpperCase());							
										
						} catch (InvalidCharacteristicException ex)
						{  
							System.out.println("WARNING: invalid characteristic in config file in line "+lineCount);
							gender = Character.Gender.getRandom();
							
						}
					
						boolLegal = Boolean.parseBoolean(data[5]);
						isYou = Boolean.parseBoolean(data[6]);
						//per.setAsYou(isYou);
						if(data[9].equals("passenger"))
						{
							Person per = new Person(age, profession, gender, bodyType, boolLegal );
							per.setAsYou(isYou);
							passengers.add(per);
						}
						if(data[9].equals("pedestrian"))
						{
							Person per = new Person(age, profession, gender, bodyType, boolLegal );
							per.setAsYou(isYou);

							pedestrians.add(per);
						}
                	}
                	
                	if(data[0].equals("animal"))
                	{
                		
                		
                		if(data[9].equals("passenger"))
						{
                			Animal ani = new Animal(data[7]);
                			ani.setAge(age);
                			ani.setBodyType(bodyType);
                			ani.setAsPet(Boolean.parseBoolean(data[8]));
							passengers.add(ani);
						}
						if(data[9].equals("pedestrian"))
						{
							Animal ani = new Animal(data[7]);
                			ani.setAge(age);
                			ani.setBodyType(bodyType);
                			ani.setAsPet(Boolean.parseBoolean(data[8]));
							pedestrians.add(ani);
						}
                	}

                	
                	
                	
            	} catch(InvalidDataFormatException e){
  	        
            		System.out.println("WARNING: invalid number format in config file in line "+lineCount);
            		continue;
                }

        	


            }
            
        	sc = new  Scenario(passengers.toArray(new Character[passengers.size()]), pedestrians.toArray(new Character[pedestrians.size()]), true);
			scArray.add(sc);

        } catch (FileNotFoundException e) {
        	System.out.println("ERROR: could not find config file.");
        	System.exit(0);					
        }catch (IOException e) {
        	System.out.println("ERROR: could not find config file.");
        	System.exit(0);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                   // e.printStackTrace();
                }
            }
        } 
        return scArray.toArray(new Scenario[scArray.size()]);
	}	
}


