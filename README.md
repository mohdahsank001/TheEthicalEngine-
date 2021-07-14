# TheEthicalEngine-
------------------------------------

An AI program or a decision Algorithm designed to explore different scenarios which would include interaction between pedestrians and passengers in a vehicle at the pedestrian crossing and produce a decision on whom to survive ! The Decision Algorithm would choose either PEDESTRIANS or PASSENGERS and to make the decision the algorithm would consider the characteristics (age, bodyType, pets, etc.) into account when making the decision. The Decision have been made on atleast 5 characteristics from the scenario itself(eg. whether it's a legal crossing or not) or these are chosen from the character's attributes.  


The scenario entails an autonomous car whose brakes fail at a pedestrian crossing. As it is too late to relinquish control to the car's passengers, the car needs to make a decision based on the facts available about the situation.


The idea of Moral Machines is based on the Trolley Dilemma, a ﬁctional scenario presenting a decision maker with a moral dilemma: choosing ”the lesser of two evils”. The scenario entails an autonomous car whose brakes fail at a pedestrian crossing. As it is too late to relinquish control to the car’s passengers, the car needs to make a decision based on the facts available about the situation. Here An Ethical Engine,is a program designed to explore diﬀerent scenarios, build an algorithm to decide between the life of the car’s passengers vs. the life of the pedestrians, audit the decision-making algorithm through simulations, and allow users of the program to judge the outcomes themselves.


The Code would consist of following classes with appropriate functionalities : 

EthicalEngine.java contains the main function and coordinates the program ﬂow. Scenario.java contains a list of passengers, a list of pedestrians, as well as additional scenario conditions, such as whether pedestrians are legally crossing at the traﬃc light. The decision-making algorithm is implemented as a static method with the name decide(Scenario scenario) in EthicalEngine.java.


Person.java: scenarios are inhabited by people who exhibit a number of characteristics (e.g., age, gender, body type, profession etc.). In the scenarios, each person is either considered to be a passenger or a pedestrian. A person can be you.This class represents a human in the scenarios.

Animal.java: animals are part of the environment we live in. People walk their pets so make sure your program accounts for these, at least for: cats and dogs.his class represents animals in the scenarios

Scenario.java : This class contains all relevant information about a presented scenario, including the car’s passengers and the pedestrians on the street as well as whether the pedestrians are crossing legally. Each scenario can have only one instance of Person for which isYou() returns true. 

ScenarioGenerator.java : The class ScenarioGenerator.java will be the basis of the simulation and shall be used to create a variety of scenarios. To guarantee a balanced set of scenarios, it is crucial to randomize as many elements as possible, including the number and characteristics of persons and animals involved in each scenario as well as the scenario itself. 


![image](https://user-images.githubusercontent.com/67852641/125564257-e3da3dd0-e7e7-4eac-b8d2-916429910f62.png)
(image source: http://moralmachine.mit.edu/).

In the figure above, A scenario example can be seen. A self driving car approaches a pedestrian crossing but it's break would fail. The Algorithm and the code that I have desgined would decide between the two cases. 
1. On the Left : The car will continue ahead and drive through the crossing resulting in one elderly man, one pregnant woman, one boy and one dog losing thier lives. 
2. On the Right: The car will swerve and crash into a concrete barrier resulting in the death of its passengers : One woman, One man, and one baby. Note that the pedestrians abide by the law as they are crossing the GREEN signal. 







for more info Visit : 

https://www.moralmachine.net/
