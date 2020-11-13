

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ethicalengine.Scenario;
import ethicalengine.ScenarioGenerator;
import ethicalengine.InvalidInputException;;

public class EthicalEngine {

	/*
	 * The class holds the main method and manages the program execution. It takes care of the program 
	 * parameters as well as the user input. 
	 * 
	 * The class also houses the decide(scenario) method, which implements the decision making algorithm 
	 * outputting either PEDESTRIANS or PASSENGERS depending on whom to save. 
	 * 
	 * Decision Algorithm : 
	 * Your task is to implement the public static method decide (Scenario scenario) that either returns 
	 * a value of the enumeration type Decision, which is either PEDESTRIANS or PASSENGERS. 
	 * 
	 * 
	 * 
	 * */
	
	private long seedVal = 10l;
	static Scanner input ;
	
	public static void main(String arg[])
	{
		String configPath = null;
		String resultPath= null;
		Audit au;
		
		Map flagMap = new HashMap(); 
		
		
		
		for(int i = 0; i< arg.length; i++)
		{
			if(arg[i].equals("-h") || arg[i].equals("--help"))
			{
				
				flagMap.put("help", true);
			}
			
			
			if(arg[i].equals("-r") || arg[i].equals("--result"))
			{
				
				try
				{
					
					resultPath = arg[i+1];	
				
				} catch (ArrayIndexOutOfBoundsException e) {
					        		
										
				}
				
				flagMap.put("result", resultPath);
				
			}
			
			if(arg[i].equals("-c") || arg[i].equals("--config"))
			{
				
				try {
					
					configPath = arg[i+1];
					
					checkFileExists(configPath);
				
				} catch (ArrayIndexOutOfBoundsException e) {
					
					 		        		
										
				}catch (FileNotFoundException e) {
					System.out.println("ERROR: could not find config file."); 		        		
			    	System.exit(0);					
				}
				
				flagMap.put("config", configPath);
				
				
			}
			
			if(arg[i].equals("-i") || arg[i].equals("--interactive"))
			{
				flagMap.put("interactive", true);
			}
			

		}
		
		
		if(flagMap.containsKey("help") || (flagMap.containsKey("config") && configPath == null) || (flagMap.containsKey("result") && resultPath == null) )
		{
			printHelp();
		}
		
		
		
		if(flagMap.containsKey("interactive"))
		{
			readAndPrintWelcomeMessage();
			input = new Scanner(System.in);
			ArrayList<EthicalEngine.Decision> alDec = new ArrayList();
			//EthicalEngine.Decision[] dec = new EthicalEngine.Decision[3];
			boolean inputflag = false;
			String inputStr = "";
			String inputStrCon = "";
			System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");
			while(!inputflag)
			{
				
				inputStr = input.nextLine();
				try {
					
					if(!inputStr.equals("yes") && !inputStr.equals("no"))
						throw new InvalidInputException("");
					else 
						inputflag = true;
				
				} catch (InvalidInputException e) {
					
					System.out.println("Invalid response. Do you consent to have your decisions saved to a file? (yes/no)");	
				}
				
			}
			
			if(flagMap.containsKey("config") && configPath != null)
			{
				
				Scenario[] scenarios = ScenarioGenerator.readFromCSVFile(configPath);
				ArrayList<Scenario> alThreeScenarios = new ArrayList();
				int processCount =0; 
				
				for(int i = 0; i< scenarios.length; i++ )
				{

					if(i!=0 && (i%3) == 0)
					{
						au = new Audit(alThreeScenarios.toArray(new Scenario[alThreeScenarios.size()]));
						au.run(alDec.toArray(new EthicalEngine.Decision[alDec.size()]));
						
						au.setAuditType("User");
						au.printStatistic();
						
						if( inputflag )
						{

							//if(flagMap.containsKey("result"))
							//	au.printToFile(flagMap.get("result")+"/user.log");
							//else
								au.printToFile("user.log");
						}
						
						//threeScenarios = new Scenario[3];
						alThreeScenarios.clear();
						alDec.clear();
						processCount=0;
						
						
						System.out.println("Would you like to continue? (yes/no)");
						inputflag = false;
						while(!inputflag)
						{
							
							inputStr = input.nextLine();
							try {
								
								if(!inputStr.equals("yes") && !inputStr.equals("no"))
									throw new InvalidInputException("");
								else 
									inputflag = true;
							
							} catch (InvalidInputException e) {
								
								System.out.println("Invalid response. Would you like to continue? (yes/no)");	
							}
							
							if(inputStr.equals("no"))
							{
								System.exit(0);
							}
							
						}
						
		
					}
					
					//threeScenarios[processCount-1] = scenarios[i];
					alThreeScenarios.add(scenarios[i]);
					
					System.out.println(scenarios[i].toString());
					
					System.out.println("Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");
					inputflag = false;
					while(!inputflag)
					{
						
						inputStr = input.nextLine();
						try {
							
							if( !inputStr.equals("passenger") && !inputStr.equals("passengers") && !inputStr.equals("1") && !inputStr.equals("pedestrian") && !inputStr.equals("pedestrian") && !inputStr.equals("2"))
								throw new InvalidInputException("");
							else 
								inputflag = true;
						
						} catch (InvalidInputException e) {
							
							System.out.println("Invalid response. Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");	
						}
						
						if(inputStr.equals("no"))
						{
							System.exit(0);
						}
						
					}
					

					if(inputStr.equals("passenger") || inputStr.equals("passengers") || inputStr.equals("1"))
						alDec.add(Decision.PASSENGERS);
					else if(inputStr.equals("pedestrian") || inputStr.equals("pedestrian") || inputStr.equals("2"))
						alDec.add( Decision.PEDESTRIANS);
						
					processCount++;
				}
				
				if( processCount != 0 )
				{
					au = new Audit(alThreeScenarios.toArray(new Scenario[alThreeScenarios.size()]));
					au.run();
					au.setAuditType("User");
					au.printStatistic();
					
					
					if( inputflag )
					{
						//if(flagMap.containsKey("result"))
						//	au.printToFile(flagMap.get("result")+"/user.log");
						//else
							au.printToFile("user.log");
					}	
				}
				
				System.out.println("That's all. Press Enter to quit.");	
				inputStr = input.nextLine();
				
	
			}
			else
			{
				
				/////////////////////////////////////////////////////////////////////////////////////////////////
				/*Audit a = new Audit();
				a.run(3);
				a.setAuditType("User");
				a.printStatistic();	
				
				if( inputflag )
				{
					//if(flagMap.containsKey("result"))
					//	a.printToFile(flagMap.get("result")+"/user.log");
					//else
						a.printToFile("user.log");
				}*/		
				
				/////////////////////////////////////////////////////////////////////////////////
				
				
				//Scenario[] scenarios = ScenarioGenerator.readFromCSVFile(configPath);
				
				ArrayList<Scenario> alThreeScenarios = new ArrayList();
				
				int processCount = 0;
				
				ScenarioGenerator sg = new ScenarioGenerator(42);
				
				//for(int i = 0; i< scenarios.length; i++ )
				int i = 0;
				boolean inputStrCheck = true;
				while(inputStrCheck)
				{

					if(i!=0 && (i%3) == 0)
					{
						au = new Audit(alThreeScenarios.toArray(new Scenario[alThreeScenarios.size()]));
						
						au.run(alDec.toArray(new EthicalEngine.Decision[alDec.size()]));
						
						au.setAuditType("User");
						au.printStatistic();
						
						if( inputflag )
						{

							//if(flagMap.containsKey("result"))
							//	au.printToFile(flagMap.get("result")+"/user.log");
							//else
								au.printToFile("user.log");
						}
						
						//threeScenarios = new Scenario[3];
						alThreeScenarios.clear();
						alDec.clear();
						processCount=0;
						
						
						System.out.println("Would you like to continue? (yes/no)");
						inputflag = false;
						while(!inputflag)
						{
							
							inputStr = input.nextLine();
							try {
								
								if(!inputStr.equals("yes") && !inputStr.equals("no"))
									throw new InvalidInputException("");
								else 
									inputflag = true;
							
							} catch (InvalidInputException e) {
								
								System.out.println("Invalid response. Would you like to continue? (yes/no)");	
							}
							
							if(inputStr.equals("no"))
							{
								System.exit(0);
								inputStrCheck = false;
							}
							
						}
						
		
					}
					Scenario sc = sg.generate();
					//threeScenarios[processCount-1] = scenarios[i];
					alThreeScenarios.add(sc);
					
					System.out.println(sc.toString());
					
					System.out.println("Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");
					inputflag = false;
					while(!inputflag)
					{
						
						inputStr = input.nextLine();
						try {
							
							if( !inputStr.equals("passenger") && !inputStr.equals("passengers") && !inputStr.equals("1") && !inputStr.equals("pedestrian") && !inputStr.equals("pedestrian") && !inputStr.equals("2"))
								throw new InvalidInputException("");
							else 
								inputflag = true;
						
						} catch (InvalidInputException e) {
							
							System.out.println("Invalid response. Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");	
						}
						
						if(inputStr.equals("no"))
						{
							System.exit(0);
						}
						
					}
					
					
					
					if(inputStr.equals("passenger") || inputStr.equals("passengers") || inputStr.equals("1"))
						alDec.add(Decision.PASSENGERS);
					else if(inputStr.equals("pedestrian") || inputStr.equals("pedestrian") || inputStr.equals("2"))
						alDec.add( Decision.PEDESTRIANS);
						
						
					i++;

				}
				
				au = new Audit(alThreeScenarios.toArray(new Scenario[alThreeScenarios.size()]));
				au.run();
				au.setAuditType("User");
				au.printStatistic();
				
				
				if( inputflag )
				{
					//if(flagMap.containsKey("result"))
					//	au.printToFile(flagMap.get("result")+"/user.log");
					//else
						au.printToFile("user.log");
				}	
				
				
				
				//////////////////////////////////////////////////////////////////////////////////////////
			}

			
		}				
		else if(flagMap.containsKey("config") && configPath != null)
		{
			au = new Audit(ScenarioGenerator.readFromCSVFile(configPath));
			au.run();
			au.setAuditType("Config File Scenarios");
			au.printStatistic();
			
			
			if(flagMap.containsKey("result"))
				au.printToFile(flagMap.get("result").toString());
			
			
					
		}
		else
		{
			Audit a = new Audit();
			a.run(5);
			a.setAuditType("Random Scenarios");
			a.printStatistic();	
			
			if(flagMap.containsKey("result"))
				a.printToFile(flagMap.get("result").toString());
		}
	}
	
	public enum Decision {
		
		PASSENGERS ,
		PEDESTRIANS ; 
	}
	
	public static void checkFileExists(String filePath) throws FileNotFoundException {
		
		File file = new File(filePath);
		
    	if(!file.exists()){
	    	 throw new FileNotFoundException();
	    	}
		
	}
	
	
	
	public static Decision decide(Scenario scenario) {
		
		
		int numofpassengers = scenario.getPassengers().length ;
		int numofpedestrians = scenario.getPedestrians().length ;
		
		boolean incar = scenario.hasYouInCar() ; 
		boolean inlane = scenario.hasYouInLane() ; 
		
		boolean legalcross = scenario.isLegalCrossing() ; 
		
		
		// On the basis of count. 
		if(numofpassengers > numofpedestrians) {
			
			return Decision.PASSENGERS ;
			
		} else if (numofpedestrians > numofpassengers) {
			
			return Decision.PEDESTRIANS ; 
			
			// On the basis of inlane or incar . 
			
		}   else if (incar == true) {
			
			return Decision.PASSENGERS ; 
			
		}  else if (inlane == true ) {
			
			return Decision.PEDESTRIANS ; 
			
		}  else if (legalcross == true ) {
			
		    return Decision.PEDESTRIANS ; 
			
		}else if (legalcross == false) {
			
			return Decision.PASSENGERS ;
			
		}else if ((legalcross == true) && (inlane == false)) { // Need to test this condition
			
			return Decision.PEDESTRIANS ; 
			
		} else if ((legalcross == false ) && (inlane == true)) { // Need to test this condition
			
			return Decision.PEDESTRIANS ; 
		}
		else
			return Decision.PEDESTRIANS ;
		
		 
	}
	
	private static void printHelp()
	{
		String strPrint ="";
		
		strPrint+= "EthicalEngine - COMP90041 - Final Project";
		strPrint+= "\n";
		strPrint+= "\nUsage: java EthicalEngine [arguments]";
		strPrint+= "\n";
		strPrint+= "\nArguments:";
		strPrint+= "\n\t-c or --config Optional: path to config file";
		strPrint+= "\n\t-h or --help Print Help (this message) and exit";
		strPrint+= "\n\t-r or --results Optional: path to results log file";
		strPrint+= "\n\t-i or --interactive Optional: launches interactive mode";		
		System.out.println(strPrint);	
		
	}
	
	private static void readAndPrintWelcomeMessage()
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("welcome.ascii"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line = null;
		try {
			while (( line = br.readLine()) != null)
			{
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
			
}
	
	
	
	

