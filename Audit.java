
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import ethicalengine.Animal;
import ethicalengine.Character;
import ethicalengine.Person;
import ethicalengine.Scenario;
import ethicalengine.ScenarioGenerator;
import static java.util.stream.Collectors.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.util.Map.Entry.*;

public class Audit {

	/*
	 * The class Audit.java should : 
	 * 
	 * 1. Create a specific number of random scenarios . 
	 * 2.  Have the ethical engine decide on each of the outcomes . 
	 * 3.  And summarize the results for each of the characteristic in a so-called statistic of projected survival . 
	 * 
	 * */
	
	String strAuditType = "Unspecified."  ;
	private int runs = 0; 
	ArrayList survivors = new ArrayList();
	ArrayList allChars = new ArrayList();
	Map sorted = new HashMap();
	ArrayList<Integer> ageList = new ArrayList() ;
	ArrayList<Boolean> allLegalCrossingList = new ArrayList() ;
	ArrayList<Boolean> survLegalCrossingList = new ArrayList() ;
	Scenario[] scenarios;
	
	Double averageAge = 0.0;
	
	public Audit() {
		
		
	}
	
	public Audit(Scenario[] scenarios)
	{
		this.scenarios = scenarios;
	}
	
	/*
	 * It runs the scenario by creating N=runs scenario by running each scenario 
	 * through the ethical engine using it's decide (Scenario scenario) method. 
	 * 
	 * 
	 * */
	

	public void run ()  {
			
		for(int i = 0; i< this.scenarios.length; i++ )
		{
			this.runs = this.runs + 1;
			Scenario sc = this.scenarios[i];
			processScenarios(sc);
		}
		
	
	}
	
	public void run (EthicalEngine.Decision[] dec)  {
		
		for(int i = 0; i< this.scenarios.length; i++ )
		{
			this.runs = this.runs + 1;
			Scenario sc = this.scenarios[i];
			processScenIntr(sc, dec[i]);
		}
		
	}
	
	public void run (int runs)  {
		
		this.runs += runs;
		
		ScenarioGenerator sg = new ScenarioGenerator(42);
	
		for(int i = 0; i< runs; i++ )
		{
			
			Scenario sc = sg.generate();
			
			processScenarios(sc);
		}	
		
	}
	
	
	private void processScenIntr(Scenario sc, EthicalEngine.Decision dec)
	{
	    Character [] chars;
	    
		EthicalEngine.Decision decision = dec;
		
		boolean legality = sc.isLegalCrossing();
		
		this.allLegalCrossingList.add( sc.isLegalCrossing());
		
		chars = sc.getPedestrians();				
		for(int j= 0 ; j< sc.getPedestrians().length; j++)
			this.allChars.add(chars[j]);
		
		
		chars = sc.getPassengers();			
		for(int j= 0 ; j< sc.getPassengers().length; j++)
			this.allChars.add(chars[j]);
		
		
		if(decision == EthicalEngine.Decision.PASSENGERS)
		{
			 chars = sc.getPassengers();
			
			for(int j= 0 ; j< sc.getPassengers().length; j++)
				this.survivors.add(chars[j]);
		}
		else
		{				
			chars = sc.getPedestrians();
			for(int j= 0 ; j< sc.getPedestrians().length; j++)
				this.survivors.add(chars[j]);
			
			this.survLegalCrossingList.add( sc.isLegalCrossing());

		}
		
		Map mapAllCharsDetails = getAllCharacterDetails(allChars, allLegalCrossingList);
		Map mapAllSurvDetails = getAllSurvivorsDetails(survivors, survLegalCrossingList);
		
		this.sorted = calculateStats(mapAllCharsDetails, mapAllSurvDetails );
	    this.averageAge = ageList.stream().mapToInt(val -> val).average().orElse(0.0);
		
	}
	
	private void processScenarios(Scenario sc)
	{
	    Character [] chars;
	    
		EthicalEngine.Decision decision = EthicalEngine.decide(sc);
		
		boolean legality = sc.isLegalCrossing();
		
		this.allLegalCrossingList.add( sc.isLegalCrossing());
		
		chars = sc.getPedestrians();				
		for(int j= 0 ; j< sc.getPedestrians().length; j++)
			this.allChars.add(chars[j]);
		
		
		chars = sc.getPassengers();			
		for(int j= 0 ; j< sc.getPassengers().length; j++)
			this.allChars.add(chars[j]);
		
		
		if(decision == EthicalEngine.Decision.PASSENGERS)
		{
			 chars = sc.getPassengers();
			
			for(int j= 0 ; j< sc.getPassengers().length; j++)
				this.survivors.add(chars[j]);
		}
		else
		{				
			chars = sc.getPedestrians();
			for(int j= 0 ; j< sc.getPedestrians().length; j++)
				this.survivors.add(chars[j]);
			
			this.survLegalCrossingList.add( sc.isLegalCrossing());

		}
		
		Map mapAllCharsDetails = getAllCharacterDetails(allChars, allLegalCrossingList);
		Map mapAllSurvDetails = getAllSurvivorsDetails(survivors, survLegalCrossingList);
		
		this.sorted = calculateStats(mapAllCharsDetails, mapAllSurvDetails );
	    this.averageAge = ageList.stream().mapToInt(val -> val).average().orElse(0.0);
		
	}
	
	private Map calculateStats(Map allCharsMap, Map allSurvMap )
	{
		Map<String, Double> statsMap = new HashMap<String, Double>();
		
		Set<String> setKeys = allCharsMap.keySet();
			
		for(String key : setKeys)
		{
			Integer allVal  = (Integer) allCharsMap.get( key );
			
			Integer surVal = 0;
			if(allSurvMap.containsKey(key))
			{
				surVal = (Integer) allSurvMap.get( key );
			}
			statsMap.put(key, (double) surVal/allVal );
						
		}
		
			
		 Map<Object, Object> sorted   = statsMap
			        .entrySet()
			        .stream()
			        .sorted(Collections.reverseOrder(comparingByValue()))
			        .collect(
			            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
			                LinkedHashMap::new));

		 
	
	
		return sorted;
	}
		
	private Map getAllCharacterDetails(ArrayList allChars,  ArrayList<Boolean> green)
	{
		
		 Map<String, Integer> mapChars = new HashMap<String, Integer>();
		 
		 for (int counter = 0; counter < green.size(); counter++)
		 {
			  if(green.get(counter))
	          {
				  if( mapChars.containsKey("GREEN"))
		          {
		        	 Integer val =  mapChars.get("GREEN"); 
		        	 mapChars.put( "GREEN", val+1 );
		          }
		          else
		          {
		        	  mapChars.put("GREEN", 1);
		          }
	          }
	        
		 }

//-----------------------------------ALL Character --------------------------------------------
		 for (int counter = 0; counter < allChars.size(); counter++)
		 {
			 
	          Character c = (Character) allChars.get(counter);
	          
              if(c instanceof Animal)
              {
            	  if( mapChars.containsKey("ANIMAL"))
		          {
		        	 Integer val =  mapChars.get("ANIMAL"); 
		        	 mapChars.put( "ANIMAL", val+1 );
		          }
		          else
		          {
		        	  mapChars.put("ANIMAL", 1);
		          }
            	              	  
                  String ageSpecies = ((Animal) c).getSpecies() ;		          
            	  if(mapChars.containsKey(ageSpecies))
     	          {
     	        	 Integer val =  mapChars.get(ageSpecies ); 
     	        	 mapChars.put( ageSpecies, val+1 );
     	          }
     	          else
     	          {
     	        	  mapChars.put(ageSpecies, 1);
     	          }
            	  
            	  
            	  boolean isPet = ((Animal) c).isPet() ;
            	  if(isPet)
            	  {
	              	  if(mapChars.containsKey("PET"))
	       	          {
	       	        	 Integer val =  mapChars.get("PET"); 
	       	        	 mapChars.put("PET", val+1 );
	       	          }
	       	          else
	       	          {
	       	        	  mapChars.put("PET", 1);
	       	          }
            	  }
            	  
            	              	  
              }  
	   
	          if(c instanceof Person)
	          {
            	  if( mapChars.containsKey("PERSON"))
            	  {
            		  Integer val =  mapChars.get("PERSON"); 
            		  mapChars.put( "PERSON", val+1 );
            	  }
            	  else
            	  {
            		  mapChars.put("PERSON", 1);
            	  }
            	  
            	  ageList.add(c.getAge());
		          Enum bodytype = c.getBodyType() ;	        		  
		          if( mapChars.containsKey(bodytype.toString()))
		          {
		        	 Integer val =  mapChars.get(bodytype.toString() ); 
		        	 mapChars.put( bodytype.toString(), val+1 );
		          }
		          else
		          {
		        	  mapChars.put(bodytype.toString(), 1);
		          }
		          
		          Enum gender = c.getGender() ;
		          if( mapChars.containsKey(gender.toString()))
		          {
		        	 Integer val =  mapChars.get(gender.toString() ); 
		        	 mapChars.put( gender.toString(), val+1 );
		          }
		          else
		          {
		        	  mapChars.put(gender.toString(), 1);
		          }
		          
		          Enum prof = ((Person) c).getProfession() ;
            	  if(mapChars.containsKey(prof.toString()))
     	          {
     	        	 Integer val =  mapChars.get(prof.toString() ); 
     	        	 mapChars.put( prof.toString(), val+1 );
     	          }
     	          else
     	          {
     	        	  mapChars.put(prof.toString(), 1);
     	          }
            	  
		          Enum ageCat = ((Person) c).getAgeCategory() ;		          
            	  if(mapChars.containsKey(ageCat.toString()))
     	          {
     	        	 Integer val =  mapChars.get(ageCat.toString() ); 
     	        	 mapChars.put( ageCat.toString(), val+1 );
     	          }
     	          else
     	          {
     	        	  mapChars.put(ageCat.toString(), 1);
     	          }
            	  
            	  if(((Person) c).isPregnant())
            	  {
            		  
            		  if(mapChars.containsKey("PREGNANT"))
         	          {
         	        	 Integer val =  mapChars.get("PREGNANT" ); 
         	        	 mapChars.put( "PREGNANT", val+1 );
         	          }
         	          else
         	          {
         	        	  mapChars.put("PREGNANT", 1);
         	          }
            		  
            	  }
		          
	          }
	          
	          
	          
	          
	      }   
		return mapChars;
			
					 
	}
	
	private Map getAllSurvivorsDetails(ArrayList survivors,  ArrayList<Boolean> green)
	{		
		
		Map<String, Integer> mapSurv = new HashMap<String, Integer>();
		
		 for (int counter = 0; counter < green.size(); counter++)
		 {
			  if(green.get(counter))
	          {
				  if( mapSurv.containsKey("GREEN"))
		          {
		        	 Integer val =  mapSurv.get("GREEN"); 
		        	 mapSurv.put( "GREEN", val+1 );
		          }
		          else
		          {
		        	  mapSurv.put("GREEN", 1);
		          }
	          }
	        
		 }

			
			 for (int counter = 0; counter < survivors.size(); counter++) {
				 
		          Character c = (Character) survivors.get(counter);
		          
		        
		          
		          // TEST output END
		       
	              if(c instanceof Animal)
	              {
		              if( mapSurv.containsKey("ANIMAL"))
			          {
			        	 Integer val =  mapSurv.get("ANIMAL"); 
			        	 mapSurv.put( "ANIMAL", val+1 );
			          }
			          else
			          {
			        	  mapSurv.put("ANIMAL", 1);
			          }
		              
		              String ageSpecies = ((Animal) c).getSpecies() ;		          
	            	  if(mapSurv.containsKey(ageSpecies))
	     	          {
	     	        	 Integer val =  mapSurv.get(ageSpecies ); 
	     	        	mapSurv.put( ageSpecies, val+1 );
	     	          }
	     	          else
	     	          {
	     	        	 mapSurv.put(ageSpecies, 1);
	     	          }
	            	  
	            	  
	            	  boolean isPet = ((Animal) c).isPet() ;
	            	  if(isPet)
	            	  {
		              	  if(mapSurv.containsKey("PET"))
		       	          {
		       	        	 Integer val =  mapSurv.get("PET"); 
		       	        	mapSurv.put("PET", val+1 );
		       	          }
		       	          else
		       	          {
		       	        	mapSurv.put("PET", 1);
		       	          }
	            	  }
	              }
		          
		          
	              if(c instanceof Person)
	              {
	            	            	  
	            	  if(mapSurv.containsKey("PERSON"))
	   		          {
	   		        	 Integer val =  mapSurv.get("PERSON"); 
	   		        	 mapSurv.put( "PERSON", val+1 );
	   		          }
	   		          else
	   		          {
	   		        	  mapSurv.put("PERSON", 1);
	   		          }
	   	              	            	 	            	 
			          Enum bodytype = c.getBodyType() ;
			          if(mapSurv.containsKey(bodytype.toString()))
			          {
			        	 Integer val =  mapSurv.get(bodytype.toString() ); 
			        	 mapSurv.put( bodytype.toString(), val+1 );
			          }
			          else
			          {
			        	  mapSurv.put(bodytype.toString(), 1);
			          }
			          
			          Enum gender = c.getGender() ;
			          if(mapSurv.containsKey(gender.toString()))
			          {
			        	 Integer val =  mapSurv.get(gender.toString() ); 
			        	 mapSurv.put( gender.toString(), val+1 );
			          }
			          else
			          {
			        	  mapSurv.put(gender.toString(), 1);
			          }
			          
	            	  Enum prof = ((Person) c).getProfession() ;
	            	  
	            	  if(mapSurv.containsKey(prof.toString()))
	     	          {
	     	        	 Integer val =  mapSurv.get(prof.toString() ); 
	     	        	 mapSurv.put( prof.toString(), val+1 );
	     	          }
	     	          else
	     	          {
	     	        	 mapSurv.put(prof.toString(), 1);
	     	          }
	            	  
	            	  
			          Enum ageCat = ((Person) c).getAgeCategory() ;
			          
	            	  if(mapSurv.containsKey(ageCat.toString()))
	     	          {
	     	        	 Integer val =  mapSurv.get(ageCat.toString() ); 
	     	        	mapSurv.put( ageCat.toString(), val+1 );
	     	          }
	     	          else
	     	          {
	     	        	 mapSurv.put(ageCat.toString(), 1);
	     	          }
	            	  
	            	  
	            	  if(((Person) c).isPregnant())
	            	  {	            		  
	            		  if(mapSurv.containsKey("PREGNANT"))
	         	          {
	         	        	 Integer val =  mapSurv.get("PREGNANT" ); 
	         	        	mapSurv.put( "PREGNANT", val+1 );
	         	          }
	         	          else
	         	          {
	         	        	 mapSurv.put("PREGNANT", 1);
	         	          }	            		 
	            	  }

		            	  		              
	              }
		          
		      }   
			 
			 
			
		return mapSurv;
		
	}
	
	
	
	public void setAuditType(String name) {
		
		this.strAuditType = name;
	}
	
	public String getAuditType() {
		
		return strAuditType ; 
	}
	

	public String toString() {
		
		String rtnString = "======================================";
			  rtnString += "\n# "+ this.getAuditType() +" Audit";
			  rtnString += "\n======================================";
		
			  rtnString += "\n- % SAVED AFTER "+ runs +" RUNS";
			  
			  Set<Object> setKeyss = sorted.keySet();
				 
			  for(Object key : setKeyss)
			  {								  
				  rtnString += "\n"+key.toString().toLowerCase()+": "+String.format("%.1f",sorted.get( key ));
			  }
			  
			  
			  rtnString += "\n--";
			  rtnString += "\n";
			  
			  rtnString += "\naverage age: "+String.format("%.1f",this.averageAge);
			  rtnString += "\n" ;
			  
			  
			  
		return rtnString;
	}
	
	public void printStatistic()
	{
		System.out.print(this.toString());
		
	}
	
	public void printToFile(String filepath)
	{
		
		 try{
		    	String content = this.toString();
		       		    	
		    	File file =new File(filepath);

		    	/* This logic is to create the file if the file is not already present */
		    	 
		    	if(!file.exists()){
		    	   file.createNewFile();
		    	}

		    	//Here true is to append the content to file
		    	FileWriter fw = new FileWriter(file,true);
		    	
		    	//BufferedWriter writer give better performance
		    	BufferedWriter bw = new BufferedWriter(fw);
		    	
		    	bw.write(content);
		    	//Closing BufferedWriter Stream
		    	bw.close();

		      }catch(IOException e){		        
		        System.out.println("ERROR: could not print results. Target directory does not exist"); 		        		
		    	System.exit(0);
		      }
		
	}
	
}
