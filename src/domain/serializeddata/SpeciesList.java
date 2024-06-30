package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.model.Address;
import domain.model.Species;

import java.util.ArrayList;

@XStreamAlias("speciesList")
public class SpeciesList {
    public static SpeciesList instance = null;

    @XStreamAlias("speciess")
    private ArrayList<Species> speciess;

    private SpeciesList() {
        this.speciess = new ArrayList<Species>();
    }

    public static SpeciesList getInstance() {
        if (instance == null) {
            instance = new SpeciesList();
        }

        return instance;
    }

    public Integer generateId() {
        int amountOfSpeciess = speciess.size();
        return amountOfSpeciess + 1;
    }

    public static void setInstance(SpeciesList speciesList) {
        instance = speciesList;
    }

    public ArrayList<Species> getSpeciess() {
        return this.speciess;
    }

    public void setSpeciess(ArrayList<Species> speciess) {
        this.speciess = speciess;
    }

    public Species addSpecies(Species species) {
        this.speciess.add(species);
        return species;
    }

    public Species getSpecies(int id) {
        for (Species species : speciess) {
            if (species.getId() == id) {
                return species;
            }
        }

        return null;
    }
}
