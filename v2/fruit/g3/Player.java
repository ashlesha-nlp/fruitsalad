package fruit.g3;

import java.util.*;

public class Player extends fruit.sim.Player
{
    public void init(int nplayers, int[] pref) {
		this.preferences = pref.clone();
		this.id = this.getIndex();
		this.players = nplayers;
		this.maxBowlsR0 = this.players - this.id;
		this.maxBowlsR1 = this.id + 1;
		System.out.println("Player ID: " + this.id);
		System.out.println("Max bowls can be seen: Round 0: " + this.maxBowlsR0);
		System.out.println("Max bowls can be seen: Round 1: " + this.maxBowlsR1);

		for (int i = 0; i < 12; i++) {
			this.fruits[i] = 1;
		}

    }

    public boolean pass(int[] bowl, int bowlId, int round,
                        boolean canPick,
                        boolean musTake) {

		int bowlScore = 0;

		for (int i = 0; i < 12; i++) {
			fruits[i] += (double)(bowl[i])/2;
			bowlScore = bowlScore + (bowl[i]*preferences[i]);
			// System.out.println("Fruit " + i + " = " + fruits[i]);
		}
		System.out.println("Bowl Score: " + bowlScore);

		if (round == 0) {
			bowlsSeenR0++;

			if (maxBowlsR0 == 3) {
				if (bowlsSeenR0 >= 2) {
					int maxScore = getMaxScore(scoresSeenR0);
					scoresSeenR0.add(bowlScore);
					return maxScore > bowlScore;
				}
			}

			if (Math.floor((double)(maxBowlsR0)/(double)(Math.E)) > bowlsSeenR0) {
				int maxScore = getMaxScore(scoresSeenR0);
				scoresSeenR0.add(bowlScore);
				return maxScore > bowlScore;
			}
			else {
				scoresSeenR0.add(bowlScore);
				return true;
			}
		}
		
		if (round == 1) {
			bowlsSeenR1++;

			if (maxBowlsR1 == 3) {
				if (bowlsSeenR1 >= 2) {
					int maxScore = getMaxScore(scoresSeenR1);
					scoresSeenR1.add(bowlScore);
					return maxScore > bowlScore;
				}
			}

			if (Math.floor((double)(maxBowlsR1)/(double)(Math.E)) > bowlsSeenR1) {
				int maxScore = getMaxScore(scoresSeenR1);
				scoresSeenR1.add(bowlScore);
				return maxScore > bowlScore;
			}
			else {
				scoresSeenR1.add(bowlScore);
				return true;
			}
		}

		/*int fruitQuantity = 0;
		int expectedScore = 0;
		System.out.println("ID: " + bowlId);
		
		for (int i = 0; i < bowl.length; i++) {
			System.out.println(i + " " + bowl[i]);
			fruitQuantity = fruitQuantity + bowl[i];
			bowlScore = bowlScore + (bowl[i]*preferences[i]);
		}
		expectedScore = ((fruitQuantity*(fruitQuantity+1)/2)*(fruitQuantity/12))+((fruitQuantity%12)*((fruitQuantity%12)+1)/2);
		
		for (int i = 0; i < preferences.length; i++) {

		/*int fruitQuantity = 0;
		int expectedScore = 0;
		System.out.println("ID: " + bowlId);
		
		for (int i = 0; i < bowl.length; i++) {
			System.out.println(i + " " + bowl[i]);
			fruitQuantity = fruitQuantity + bowl[i];
			bowlScore = bowlScore + (bowl[i]*preferences[i]);
		}
		expectedScore = ((fruitQuantity*(fruitQuantity+1)/2)*(fruitQuantity/12))+((fruitQuantity%12)*((fruitQuantity%12)+1)/2);
		
		for (int i = 0; i < preferences.length; i++) {
			System.out.print(i + " " + preferences[i] + " , ");
		}
		System.out.println();
		System.out.println("Expected Score: " + expectedScore);
		System.out.println("Bowl Score: " + bowlScore);
		return bowlScore > expectedScore;*/
		System.out.println("Returning false");
		return false;
    }
	
	private int getMaxScore(ArrayList<Integer> scoresSeen) {
		int max = 0;
		for (int score : scoresSeen) {
			if (max < score) {
				max = score;
			}
		}
		return max;
	}
    

    private Random random = new Random();
    private int[] preferences = new int[12];
	private double[] fruits = new double[12];
	private int id = 0;
	private int bowlsSeenR0 = 0;
	private int bowlsSeenR1 = 0;
	private int maxBowlsR0 = 0;
	private int maxBowlsR1 = 0;
	private int players = 0;
	private ArrayList<Integer> scoresSeenR0 = new ArrayList<Integer>();
	private ArrayList<Integer> scoresSeenR1 = new ArrayList<Integer>();
}
