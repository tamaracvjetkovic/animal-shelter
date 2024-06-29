package domain.model;

import java.util.ArrayList;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.AnimalState;

@XStreamAlias("animal")
public class Animal {
	private Integer id;
	private String name;
	private String colour;
	private Date born;
	private Integer addressId;
	private AnimalState state;
	private ArrayList<String> multimedia;
	private Integer breedId;
	private Integer speciesId;
	public Animal() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Animal(Integer id, String name, String colour, Date born, Integer addressId, AnimalState state,
			ArrayList<String> multimedia, Integer breedId, Integer speciesId) {
		super();
		this.id = id;
		this.name = name;
		this.colour = colour;
		this.born = born;
		this.addressId = addressId;
		this.state = state;
		this.multimedia = multimedia;
		this.breedId = breedId;
		this.speciesId = speciesId;
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
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public Date getBorn() {
		return born;
	}
	public void setBorn(Date born) {
		this.born = born;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public AnimalState getState() {
		return state;
	}
	public void setState(AnimalState state) {
		this.state = state;
	}
	public ArrayList<String> getMultimedia() {
		return multimedia;
	}
	public void setMultimedia(ArrayList<String> multimedia) {
		this.multimedia = multimedia;
	}
	public Integer getBreedId() {
		return breedId;
	}
	public void setBreedId(Integer breedId) {
		this.breedId = breedId;
	}
	public Integer getSpeciesId() {
		return speciesId;
	}
	public void setSpeciesId(Integer speciesId) {
		this.speciesId = speciesId;
	}
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", colour=" + colour + ", born=" + born + ", addressId="
				+ addressId + ", state=" + state + ", multimedia=" + multimedia + ", breedId=" + breedId
				+ ", speciesId=" + speciesId + "]";
	}
	
	

}
