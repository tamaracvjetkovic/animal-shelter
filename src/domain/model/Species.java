package domain.model;

import java.util.ArrayList;

public class Species {
	private Integer id;
	private String name;
	private ArrayList<Integer> breedsIds;
	private ArrayList<Integer> animalsIds;
	public Species() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Species(Integer id, String name, ArrayList<Integer> breeds, ArrayList<Integer> animals) {
		super();
		this.id = id;
		this.name = name;
		this.breedsIds = breeds;
		this.animalsIds = animals;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Integer> getBreeds() {
		return breedsIds;
	}
	public void setBreeds(ArrayList<Integer> breeds) {
		this.breedsIds = breeds;
	}
	public ArrayList<Integer> getAnimals() {
		return animalsIds;
	}
	public void setAnimals(ArrayList<Integer> animals) {
		this.animalsIds = animals;
	}
	@Override
	public String toString() {
		return "Species [id=" + id + ", name=" + name + ", breeds=" + breedsIds + ", animals=" + animalsIds + "]";
	}
	
}
