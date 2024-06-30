package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.enums.AnimalState;
import domain.model.Animal;

import java.util.ArrayList;
import java.util.Date;

@XStreamAlias("animalList")
public class AnimalList {
    public static AnimalList instance = null;

    @XStreamAlias("animals")
    private ArrayList<Animal> animals;

    private AnimalList() {
        this.animals = new ArrayList<Animal>();
    }

    public static AnimalList getInstance() {
        if (instance == null) {
            instance = new AnimalList();
        }

        return instance;
    }

    public Integer generateId() {
        int amountOfAnimals = animals.size();
        return amountOfAnimals + 1;
    }

    public static void setInstance(AnimalList animals) {
        instance = animals;
    }

    public ArrayList<Animal> getAnimals() {
        return this.animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public Animal addAnimal(Animal animal) {
        this.animals.add(animal);
        return animal;
    }
    public Animal createAnimal(String name, String color, Date born, Integer addressId, AnimalState state, ArrayList<String> multimedia, Integer breedId, Integer speciesId) {
        Animal animal = new Animal(generateId(),name,color,born,addressId,state,multimedia,breedId,speciesId);
        return addAnimal(animal);
    }

    public Animal getAnimal(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }

        return null;
    }

    public void updateAnimal(Animal newAnimal) {
        Animal animal = getAnimal(newAnimal.getId());
        animal.setName(newAnimal.getName());
        animal.setAddressId(newAnimal.getAddressId());
        animal.setBorn(newAnimal.getBorn());
        animal.setState(newAnimal.getState());
        animal.setBreedId(newAnimal.getBreedId());
        animal.setColour(newAnimal.getColour());
        animal.setMultimedia(newAnimal.getMultimedia());
        animal.setSpeciesId(newAnimal.getSpeciesId());
    }
}
