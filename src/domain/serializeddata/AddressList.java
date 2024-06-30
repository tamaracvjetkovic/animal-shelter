package domain.serializeddata;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import domain.model.Address;

import java.util.ArrayList;

@XStreamAlias("addressList")
public class AddressList {
    public static AddressList instance = null;

    @XStreamAlias("adresses")
    private ArrayList<Address> addresses;

    private AddressList() {
        this.addresses = new ArrayList<Address>();
    }

    public static AddressList getInstance() {
        if (instance == null) {
            instance = new AddressList();
        }

        return instance;
    }

    public Integer generateId() {
        int amountOfAddresses = addresses.size();
        return amountOfAddresses + 1;
    }

    public static void setInstance(AddressList addressList) {
        instance = addressList;
    }

    public ArrayList<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public Address addAddress(Address address) {
        this.addresses.add(address);
        return address;
    }

    public Address getAddress(int id) {
        for (Address address : addresses) {
            if (address.getId() == id) {
                return address;
            }
        }

        return null;
    }
}
