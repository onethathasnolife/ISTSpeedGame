package istspeedgame;

import java.io.Serializable;

public class Card implements Serializable{
	private String suit;
	private String value;
	private int rank;
	
	public Card(String suit, String value, int rank) {
		this.suit = suit;
		this.value = value;
		this.rank = rank;
	}
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return (suit + value);
	}
	
	public int getRank() {
		return rank;
	}
}
