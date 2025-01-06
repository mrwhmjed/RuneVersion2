package core;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

import enemy.Enemy;
import enemy.Goblin;
import enemy.Orc;
import enemy.Troll;
import weapon.Axe;
import weapon.Crossbow;
import weapon.Dagger;
import weapon.Sword;

public class Story {
	
	Game game;
	UI ui;
	VisibilityManager vm;
	Player player = new Player();
	Enemy enemy;
	private Set<String> visitedLocations = new HashSet<>();
	private String playerName = "";
	
	public Story(Game g, UI userInterface, VisibilityManager vManager) {
		game = g;
		ui = userInterface;
		vm = vManager;
	}
	
	public void defaultSetup() {
		player.hp = 10;
		ui.hpNumberLabel.setText("" + player.hp);
		
		player.weapon = new Dagger();
		ui.weaponNameLabel.setText(player.weapon.name);
	}
	
	public void selectPosition(String nextPosition) {
		switch(nextPosition) {
		case "townGate" : townGate(); break;
			case "talkGuard" : talkGuard(); break;
				case "tellGuardName" : tellGuardName(); break;
					case "findTheInn" : findTheInn(); break;
						case "enterInn" : enterInn(); break;
							case "spendTheNight" : spendTheNight(); break;
							case "grabAMeal" : grabAMeal(); break;
								case "eatChicken" : eatChicken(); break;
								case "drinkSoup" : drinkSoup(); break;
					case "meetAlthea" : meetAlthea(); break;
					case "meetBard" : meetBard(); break;
			case "attackGuard" : attackGuard(); break;
		case "crossRoad" : crossRoad(); break;
			case "north" : north(); break;
				case "enterForest" : enterForest(); break;
					case "fightEnemy" : fightEnemy(); break;
						case "attackEnemy" : attackEnemy(); break;
							case "resetGame" : resetGame(); break;
							case "exitGame" : exitGame(); break;
			case "east" : east(); break;
				case "talkToFigure" : talkToFigure(); break;
					case "drinkRiver" : drinkRiver(); break;
					case "attackFigure" : attackFigure(); break;
			case "west" : west(); break;
				case "enterCottage" : enterCottage(); break;
					case "searchCottage" : searchCottage(); break;
						case "equipSword" : equipSword(); break;
						case "equipDagger" : equipDagger(); break;
						case "equipCrossbow" : equipCrossbow(); break;
						case "equipAxe" : equipAxe(); break;
		}
	}
	
	public void townGate() {
		visitedLocations.add("south");
		ui.mainTextArea.setText("You stand before the imposing town gate, its iron-reinforced wood towering above you. A guard in polished armor watches you intently, one hand resting on the hilt of his sword. The muffled sounds of bustling life from beyond the gate contrast sharply with the tense silence of his unwavering gaze.\nWhat do you do?");
		ui.choice1.setText("Talk to the guard");
		ui.choice2.setText("Attack the guard");
		ui.choice3.setText("Leave");
		ui.choice4.setText("");
		
		game.nextPosition1 = "talkGuard";
		game.nextPosition2 = "attackGuard";
		game.nextPosition3 = "crossRoad";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}
	
	public void talkGuard() {
		if (!visitedLocations.contains("talkGuard")) {
			visitedLocations.add("talkGuard");
			ui.mainTextArea.setText("Guard: I'm sorry, but I cannot let a stranger into our town.");
			ui.choice1.setText("Go back");
			ui.choice2.setText("");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "townGate";
			game.nextPosition2 = "";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(false);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		} else if (visitedLocations.contains("meetGuard")) {
			tellGuardName();
		} else {
			visitedLocations.add("meetGuard");
			ui.mainTextArea.setText("Guard: You're back? Alright, what is your name young traveller?");
			ui.choice1.setText("Tell him your name");
			ui.choice2.setText("Go back");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "tellGuardName";
			game.nextPosition2 = "townGate";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
	}
	
	public void tellGuardName() {
		if (playerName.isEmpty()) {
			playerName = JOptionPane.showInputDialog(null, "Enter your name:", "Name Input", JOptionPane.QUESTION_MESSAGE);
		}
		ui.mainTextArea.setText("Guard: Welcome to our town " + playerName + ". Feel free to explore and get familiar with the locals.");
		ui.choice1.setText(visitedLocations.contains("findTheInn") ? "Go to the Cozy Inn" : "Go straight");
	    ui.choice2.setText(visitedLocations.contains("meetAlthea") ? "Talk to Althea" : "Go right");
	    ui.choice3.setText(visitedLocations.contains("meetBard") ? "Talk to Bard" : "Go left");
	    ui.choice4.setText("Leave");
		
		game.nextPosition1 = "findTheInn";
		game.nextPosition2 = "meetAlthea";
		game.nextPosition3 = "meetBard";
		game.nextPosition4 = "crossRoad";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
	}
	
	public void findTheInn() {
		if (!visitedLocations.contains("findTheInn")) {
			visitedLocations.add("findTheInn");
			ui.mainTextArea.setText("You arrive at a bustling inn, its wooden sign swaying gently in the breeze and lanterns casting a warm, inviting glow. The lively chatter of patrons and the clinking of mugs spill out onto the cobblestone street.\nWhat do you do?");
			ui.choice1.setText("Enter the inn");
			ui.choice2.setText("Leave");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "enterInn";
			game.nextPosition2 = "tellGuardName";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		} else {
			ui.mainTextArea.setText("You return to the Cozy Inn, its familiar warmth and soft glow welcoming you once more. The hum of conversation and the enticing aroma of hearty meals fill the air.\nWhat do you do?");
			ui.choice1.setText("Enter the inn");
			ui.choice2.setText("Leave");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "enterInn";
			game.nextPosition2 = "tellGuardName";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
	}
	
	public void enterInn() {
		ui.mainTextArea.setText("Receptionist: Welcome to the Cozy Inn! What can I do for you?");
		ui.choice1.setText("Spend the night");
		ui.choice2.setText("Grab a meal");
		ui.choice3.setText("Leave");
		ui.choice4.setText("");
			
		game.nextPosition1 = "spendTheNight";
		game.nextPosition2 = "grabAMeal";
		game.nextPosition3 = "tellGuardName";
		game.nextPosition4 = "";
			
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(false);
	}
	
	public void spendTheNight() {
		if(player.hp != 10) {
			ui.mainTextArea.setText("You spend the night in the comfort of the inn, the soft bed and quiet ambiance offering a restful reprieve. As morning sunlight streams through the window, you wake up feeling rejuvenated.\nYour health seems to be fully restored.");
			player.hp = 10;
			ui.hpNumberLabel.setText("" + player.hp);
		} else {
			ui.mainTextArea.setText("You spend the night in the comfort of the inn, the soft bed and quiet ambiance offering a restful reprieve. As morning sunlight streams through the window, you wake up feeling rejuvenated.");
		}
		ui.choice1.setText("Leave the Cozy Inn");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "tellGuardName";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void grabAMeal() {
		ui.mainTextArea.setText("You make your way to the bustling dining hall, the aroma of freshly cooked meals filling the air. The innkeeper greets you warmly, presenting a variety of dishes to choose from.\nWhat would you like to eat?");
		ui.choice1.setText("Eat Chicken");
		ui.choice2.setText("Drink soup");
		ui.choice3.setText("Go back");
		ui.choice4.setText("Leave the Cozy Inn");
		
		game.nextPosition1 = "eatChicken";
		game.nextPosition2 = "drinkSoup";
		game.nextPosition3 = "enterInn";
		game.nextPosition4 = "tellGuardName";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
	}
	
	public void eatChicken() {
		ui.mainTextArea.setText("You savor a hearty serving of roasted chicken, its golden skin crispy and the meat tender and flavorful. The rich aroma and satisfying taste leave you feeling revitalized.");
		if (player.hp < 10) {
	        player.hp = Math.min(player.hp + 4, 10);
	        ui.hpNumberLabel.setText("" + player.hp);
	        ui.mainTextArea.setText("\nYour health seems to have increased.");
	    }
		ui.choice1.setText("Go back");
		ui.choice2.setText("Leave the Cozy Inn");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "enterInn";
		game.nextPosition2 = "tellGuardName";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void drinkSoup() {
		ui.mainTextArea.setText("You enjoy a steaming bowl of hearty soup, its rich aroma filling the air. The warmth spreads through you as each spoonful soothes and revitalizes your spirit.");
		if (player.hp < 10) {
	        player.hp = Math.min(player.hp + 2, 10);
	        ui.hpNumberLabel.setText("" + player.hp);
	        ui.mainTextArea.setText("\nYour health seems to have increased.");
	    }
		ui.choice1.setText("Go back");
		ui.choice2.setText("Leave the Cozy Inn");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "enterInn";
		game.nextPosition2 = "tellGuardName";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void meetAlthea() {
		if (!visitedLocations.contains("meetAlthea")) {
			visitedLocations.add("meetAlthea");
			ui.mainTextArea.setText("");
		} else {
			ui.mainTextArea.setText("");
		}
	}
	
	public void meetBard() {
		if (!visitedLocations.contains("meetBard")) {
			visitedLocations.add("meetBard");
			ui.mainTextArea.setText("");
		} else {
			ui.mainTextArea.setText("");
		}
	}
	
	public void attackGuard() {
		if (player.hp > 0) {
			player.hp -= 3;
			if(player.hp < 0) {
				player.hp = 0;
	        	ui.hpNumberLabel.setText("" + player.hp);
	        	ui.mainTextArea.setText("The guard strikes a fatal blow, and your vision fades to black. Your journey has come to an end.\nWould you like to restart the game and try again?");
				ui.choice1.setText("Yes");
				ui.choice2.setText("No");
				ui.choice3.setText("");
				ui.choice4.setText("");
				
				game.nextPosition1 = "resetGame";
				game.nextPosition2 = "exitGame";
				game.nextPosition3 = "";
				game.nextPosition4 = "";
				
				ui.choice1.setVisible(true);
				ui.choice2.setVisible(true);
				ui.choice3.setVisible(false);
				ui.choice4.setVisible(false);
	        } else {
	        	ui.hpNumberLabel.setText("" + player.hp);
		        ui.mainTextArea.setText("Guard: Hey, watch it! The guard skillfully blocks your attack and counters with a swift strike.\nYou feel the sting as you take damage from the blow.");
		        ui.choice1.setText("Go back");
		        ui.choice2.setText("");
		        ui.choice3.setText("");
		        ui.choice4.setText("");
		            
		        game.nextPosition1 = "townGate";
		        game.nextPosition2 = "";
		        game.nextPosition3 = "";
		        game.nextPosition4 = "";
		            
		        ui.choice1.setVisible(true);
		        ui.choice2.setVisible(false);
		        ui.choice3.setVisible(false);
		        ui.choice4.setVisible(false);
	        }
		}
	}
	
	public void crossRoad() {
		ui.mainTextArea.setText("You stand at a crossroads, the path splitting in several directions. The air feels thick with the choices ahead, each route leading to unknown adventures.\nWhere will you go?");
		ui.choice1.setText(visitedLocations.contains("north") ? "Go to dark forest" : "Go north");
	    ui.choice2.setText(visitedLocations.contains("east") ? "Go to magical river" : "Go east");
	    ui.choice3.setText(visitedLocations.contains("south") ? "Go to town gate" : "Go south");
	    ui.choice4.setText(visitedLocations.contains("west") ? "Go to old cottage" : "Go west");
		
		game.nextPosition1 = "north";
		game.nextPosition2 = "east";
		game.nextPosition3 = "townGate";
		game.nextPosition4 = "west";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
	}
	
	public void north() {
		if (!visitedLocations.contains("north")) {
	        visitedLocations.add("north");
	        ui.mainTextArea.setText("You stand at the edge of a vast, ominous forest, its towering trees casting long shadows. The distant sound of roars echoes through the air, sending a chill down your spine.\nWill you venture into the unknown, or turn back?");
	    } else {
	        ui.mainTextArea.setText("You step back into the eerie, dark forest, where the towering trees loom over you, their branches twisting like gnarled hands. The unsettling roars from deep within the forest grow louder, as if something is watching you.\nWhat will you do next?");
	    }
		ui.choice1.setText("Enter the forest");
		ui.choice2.setText("Leave");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "enterForest";
		game.nextPosition2 = "crossRoad";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void enterForest() {
		Random random = new Random();
	    int enemyType = random.nextInt(3);

	    switch (enemyType) {
	        case 0:
	            enemy = new Orc();
	            break;
	        case 1:
	            enemy = new Troll();
	            break;
	        case 2:
	            enemy = new Goblin();
	            break;
	    }
	    
		ui.mainTextArea.setText("A menacing " + enemy.name + " suddenly emerges from the shadows, its eyes glowing with hostility. With a fearsome growl, it charges toward you, its steps shaking the ground beneath.\nWhat do you do?");
		ui.choice1.setText("Fight");
		ui.choice2.setText("Run away");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "fightEnemy";
		game.nextPosition2 = "crossRoad";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void fightEnemy() {
		ui.mainTextArea.setText("The " + enemy.name + " stands before you, its breathing heavy as it prepares for battle. It has " + enemy.hp + " HP left. What do you do?");
		ui.choice1.setText("Attack");
		ui.choice2.setText("Run away");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "attackEnemy";
		game.nextPosition2 = "crossRoad";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void attackEnemy() {
		int playerDmg = new java.util.Random().nextInt(player.weapon.dmg);
		int enemyDmg = new java.util.Random().nextInt(enemy.attackDmg);
		enemy.hp -= playerDmg;
		
		if (playerDmg != 0) {
			ui.mainTextArea.setText("You strike the " + enemy.name + ", dealing " + playerDmg + " damage! The enemy staggers, but it still has " + enemy.hp + " HP remaining.\nWhat will you do next?");
		} else if (playerDmg == 0) {
			player.hp -= enemyDmg;
			ui.hpNumberLabel.setText("" + player.hp);
			ui.mainTextArea.setText("You miss your attack on the " + enemy.name + " and take " + enemyDmg + " damage in return! Your health is now " + player.hp + " HP. The enemy still has " + enemy.hp + " HP.\nWhat will you do next?");
		}
		
		if(enemy.hp > 0 && player.hp > 0) {
			ui.choice1.setText("Attack");
			ui.choice2.setText("Run away");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "attackEnemy";
			game.nextPosition2 = "crossRoad";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		} else if (enemy.hp <= 0 && player.hp > 0) {
			ui.mainTextArea.setText("You have successfully defeated the " + enemy.name + "! Its body falls to the ground as you stand victorious.\nWhat will you do next?");
			ui.choice1.setText("Keep exploring");
			ui.choice2.setText("Leave");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "enterForest";
			game.nextPosition2 = "crossRoad";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		} else if (player.hp <= 0 && enemy.hp > 0) {
			player.hp = 0;
			ui.hpNumberLabel.setText("" + player.hp);
			ui.mainTextArea.setText("The " + enemy.name + " has overpowered you! You fall to the ground, your strength fading.\nWould you like to restart the game and try again?");
			ui.choice1.setText("Yes");
			ui.choice2.setText("No");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "resetGame";
			game.nextPosition2 = "exitGame";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		}
	}
	
	public void resetGame() {
		player.hp = 10;
	    player.weapon = new Dagger();
	    ui.hpNumberLabel.setText("" + player.hp);
	    ui.weaponNameLabel.setText(player.weapon.name);
	    townGate();
	}
	
	public void exitGame() {
		System.exit(0);
	}
	
	public void east() {
		if (!visitedLocations.contains("east")) {
	        visitedLocations.add("east");
	        ui.mainTextArea.setText("You stumble upon a river that glows with an eerie light, its waters shimmering in the dim twilight. On the riverbank, a small figure sits, gazing at the water, unaware of your presence.\nWhat do you do?");
	        ui.choice1.setText("Talk to the figure");
			ui.choice2.setText("Leave");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "talkToFigure";
			game.nextPosition2 = "crossRoad";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
		} else {
	        ui.mainTextArea.setText("You return to the glowing river and spot Tyrion, still sitting quietly by the water's edge. The faint glow reflects off his features as he seems lost in thought.\nWhat do you do?");
	        ui.choice1.setText("Talk to Tyrion");
			ui.choice2.setText("Leave");
			ui.choice3.setText("");
			ui.choice4.setText("");
			
			game.nextPosition1 = "talkToFigure";
			game.nextPosition2 = "crossRoad";
			game.nextPosition3 = "";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(false);
			ui.choice4.setVisible(false);
	    }
	}
	
	public void talkToFigure() {
		if (!visitedLocations.contains("talkToFigure")) {
			visitedLocations.add("talkToFigure");
	        ui.mainTextArea.setText("Tyrion: Hello, traveler! It's nice to meet you. You look weary and injured. The river here has magical properties—try taking a sip; it might help you feel better.\nHe gestures toward the glowing water.\nWhat do you do?");
	        ui.choice1.setText("Drink from the river");
			ui.choice2.setText("Attack Tyrion");
			ui.choice3.setText("Leave");
			ui.choice4.setText("");
			
			game.nextPosition1 = "drinkRiver";
			game.nextPosition2 = "attackFigure";
			game.nextPosition3 = "crossRoad";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(true);
			ui.choice4.setVisible(false);
	    } else {
	        ui.mainTextArea.setText("Tyrion: Welcome back, traveler! You seem weary and injured. Why don't you take a sip from the river? It might help restore your strength.\nWhat do you do?");
	        ui.choice1.setText("Drink from the river");
			ui.choice2.setText("Attack Tyrion");
			ui.choice3.setText("Leave");
			ui.choice4.setText("");
			
			game.nextPosition1 = "drinkRiver";
			game.nextPosition2 = "attackFigure";
			game.nextPosition3 = "crossRoad";
			game.nextPosition4 = "";
			
			ui.choice1.setVisible(true);
			ui.choice2.setVisible(true);
			ui.choice3.setVisible(true);
			ui.choice4.setVisible(false);
	    }
	}
	
	public void drinkRiver() {
		if(player.hp != 10) {
			ui.mainTextArea.setText("You take a sip from the glowing river, and almost instantly, a warm sensation spreads through your body. A soft light surrounds you as you feel your strength returning.\nYour health seems to have recovered.");
			player.hp = 10;
			ui.hpNumberLabel.setText("" + player.hp);
		} else {
			ui.mainTextArea.setText("You take a sip from the glowing river, but nothing happens. The water is refreshing, but it doesn’t seem to have any magical effects.\nYou are no longer thirsty.");
		}
		ui.choice1.setText("Leave");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "crossRoad";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void attackFigure() {
		ui.mainTextArea.setText("Tyrion: Hey! What was that for?\nTyrion quickly attempts to defend himself, but he fails to land a hit. His movements are slow and unsure, and no harm comes to you.");
		ui.choice1.setText("Go back");
		ui.choice2.setText("");
		ui.choice3.setText("");
		ui.choice4.setText("");
		
		game.nextPosition1 = "talkToFigure";
		game.nextPosition2 = "";
		game.nextPosition3 = "";
		game.nextPosition4 = "";
		
		ui.choice1.setVisible(true);
		ui.choice2.setVisible(false);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void west() {
		if (!visitedLocations.contains("west")) {
	        visitedLocations.add("west");
	        ui.mainTextArea.setText("You come across an old, run-down cottage nestled between the trees. Its wooden walls are weathered, and the roof looks like it's seen better days. The door creaks slightly in the wind, and there is an eerie silence around it, as if it’s been abandoned for years.\nWhat do you do?");
	    } else {
	        ui.mainTextArea.setText("You return to the old cottage, its weathered exterior still standing against the test of time. The door is slightly ajar, and a faint musty smell lingers in the air.\nWhat do you do?");
	    }
	    ui.choice1.setText("Enter the cottage");
	    ui.choice2.setText("Leave");
	    ui.choice3.setText("");
	    ui.choice4.setText("");

	    game.nextPosition1 = "enterCottage";
	    game.nextPosition2 = "crossRoad";
	    game.nextPosition3 = "";
	    game.nextPosition4 = "";
	    
	    ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void enterCottage() {
	    ui.mainTextArea.setText("You cautiously step into the cottage, the creaking of the wooden floorboards echoing in the stillness. The air is thick with the scent of damp wood, mingling with an unsettling metallic odor that you can't quite place.\nWhat do you do?");
	    ui.choice1.setText("Search the room");
	    ui.choice2.setText("Leave");
	    ui.choice3.setText("");
	    ui.choice4.setText("");

	    game.nextPosition1 = "searchCottage";
	    game.nextPosition2 = "crossRoad";
	    game.nextPosition3 = "";
	    game.nextPosition4 = "";
	    
	    ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void searchCottage() {
		String daggerInfo = "Dagger: " + new Dagger().dmg + " DMG";
	    String swordInfo = "Sword: " + new Sword().dmg + " DMG";
	    String crossbowInfo = "Crossbow: " + new Crossbow().dmg + " DMG";
	    String axeInfo = "Axe: " + new Axe().dmg + " DMG";
	    
		if (player.weapon instanceof Sword) {
			ui.mainTextArea.setText("You search the room and find your old dagger, a crossbow, and an axe.\n" + daggerInfo + "\n" + crossbowInfo + "\n" + axeInfo + "\nWhat do you do?");
	        ui.choice1.setText("Equip dagger");
	        ui.choice2.setText("Equip crossbow");
	        ui.choice3.setText("Equip axe");
	        ui.choice4.setText("Leave");

	        game.nextPosition1 = "equipDagger";
	        game.nextPosition2 = "equipCrossbow";
	        game.nextPosition3 = "equipAxe";
	        game.nextPosition4 = "crossRoad";
		} else if (player.weapon instanceof Dagger) {
			ui.mainTextArea.setText("You search the room and find a sword, a crossbow, and an axe.\n" + swordInfo + "\n" + crossbowInfo + "\n" + axeInfo + "\nWhat do you do?");
		    ui.choice1.setText("Equip sword");
		    ui.choice2.setText("Equip crossbow");
		    ui.choice3.setText("Equip axe");
		    ui.choice4.setText("Leave");

		    game.nextPosition1 = "equipSword";
		    game.nextPosition2 = "equipCrossbow";
		    game.nextPosition3 = "equipAxe";
		    game.nextPosition4 = "crossRoad";
		} else if (player.weapon instanceof Crossbow) {
			ui.mainTextArea.setText("You search the room and find a sword, your old dagger, and an axe.\n" + swordInfo + "\n" + daggerInfo + "\n" + axeInfo + "\nWhat do you do?");
		    ui.choice1.setText("Equip sword");
		    ui.choice2.setText("Equip dagger");
		    ui.choice3.setText("Equip axe");
		    ui.choice4.setText("Leave");

		    game.nextPosition1 = "equipSword";
		    game.nextPosition2 = "equipDagger";
		    game.nextPosition3 = "equipAxe";
		    game.nextPosition4 = "crossRoad";
		} else if (player.weapon instanceof Axe) {
			ui.mainTextArea.setText("You search the room and find a sword, a crossbow, and your old dagger.\n" + swordInfo + "\n" + crossbowInfo + "\n" + daggerInfo + "\nWhat do you do?");
		    ui.choice1.setText("Equip sword");
		    ui.choice2.setText("Equip crossbow");
		    ui.choice3.setText("Equip dagger");
		    ui.choice4.setText("Leave");

		    game.nextPosition1 = "equipSword";
		    game.nextPosition2 = "equipCrossbow";
		    game.nextPosition3 = "equipDagger";
		    game.nextPosition4 = "crossRoad";
		}
	    
	    ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(true);
		ui.choice4.setVisible(true);
	}
	
	public void equipSword() {
		ui.mainTextArea.setText("You have successfully equipped a sword!");
		player.weapon = new Sword();
		ui.weaponNameLabel.setText(player.weapon.name);
		ui.choice1.setText("Go back");
	    ui.choice2.setText("Leave");
	    ui.choice3.setText("");
	    ui.choice4.setText("");

	    game.nextPosition1 = "searchCottage";
	    game.nextPosition2 = "crossRoad";
	    game.nextPosition3 = "";
	    game.nextPosition4 = "";
		
	    ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void equipDagger() {
		ui.mainTextArea.setText("You have successfully equipped a dagger!");
		player.weapon = new Dagger();
		ui.weaponNameLabel.setText(player.weapon.name);
		ui.choice1.setText("Go back");
	    ui.choice2.setText("Leave");
	    ui.choice3.setText("");
	    ui.choice4.setText("");

	    game.nextPosition1 = "searchCottage";
	    game.nextPosition2 = "crossRoad";
	    game.nextPosition3 = "";
	    game.nextPosition4 = "";
		
	    ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void equipCrossbow() {
		ui.mainTextArea.setText("You have successfully equipped a crossbow!");
		player.weapon = new Crossbow();
		ui.weaponNameLabel.setText(player.weapon.name);
		ui.choice1.setText("Go back");
	    ui.choice2.setText("Leave");
	    ui.choice3.setText("");
	    ui.choice4.setText("");

	    game.nextPosition1 = "searchCottage";
	    game.nextPosition2 = "crossRoad";
	    game.nextPosition3 = "";
	    game.nextPosition4 = "";
		
	    ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
	
	public void equipAxe() {
		ui.mainTextArea.setText("You have successfully equipped an Axe!");
		player.weapon = new Axe();
		ui.weaponNameLabel.setText(player.weapon.name);
		ui.choice1.setText("Go back");
	    ui.choice2.setText("Leave");
	    ui.choice3.setText("");
	    ui.choice4.setText("");

	    game.nextPosition1 = "searchCottage";
	    game.nextPosition2 = "crossRoad";
	    game.nextPosition3 = "";
	    game.nextPosition4 = "";
		
	    ui.choice1.setVisible(true);
		ui.choice2.setVisible(true);
		ui.choice3.setVisible(false);
		ui.choice4.setVisible(false);
	}
}
