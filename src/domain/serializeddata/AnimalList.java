package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.model.Animal;

import java.util.ArrayList;

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
}
