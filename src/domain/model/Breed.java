package domain.model;

import java.util.ArrayList;

public class Breed {
	private String name;
	private Integer speciesId;
	private ArrayList<Integer> animalsIds;
	public Breed() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Breed(String name, Integer species, ArrayList<Integer> animals) {
		super();
		this.name = name;
		this.speciesId = species;
		this.animalsIds = animals;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSpecies() {
		return speciesId;
	}
	public void setSpecies(Integer species) {
		this.speciesId = species;
	}
	public ArrayList<Integer> getAnimals() {
		return animalsIds;
	}
	public void setAnimals(ArrayList<Integer> animals) {
		this.animalsIds = animals;
	}
	@Override
	public String toString() {
		return "Breed [name=" + name + ", species=" + speciesId + ", animals=" + animalsIds + "]";
	}
	
}
