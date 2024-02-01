package _04_animals_inheritance;

public class Dog extends Animal{
	public Dog(String name, String furColor, boolean isGirl) {
		super(name, furColor, isGirl);
	}
	
	public void wagTail() {
		System.out.println("Wagging tail");
	}
}
