package serialization;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import domain.serializeddata.*;

public class Serialization {
    private XStream xstream;

    public Serialization() {
        xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.registerConverter(new DateConverter("yyyy-MM-dd", new String[]{"yyyy-MM-dd"}));

        xstream.processAnnotations(PostList.class);
        xstream.processAnnotations(AnimalList.class);
        xstream.processAnnotations(BreedList.class);
        xstream.processAnnotations(AccountsList.class);
        xstream.processAnnotations(UsersList.class);
        xstream.processAnnotations(RequestsList.class);
        xstream.processAnnotations(MessagesList.class);
        xstream.processAnnotations(AddressList.class);
        xstream.processAnnotations(SpeciesList.class);


//        xstream.processAnnotations(JelaLista.class);
//        xstream.processAnnotations(CenovnikLista.class);
    }

    public void save() throws IOException {
        File postsFile = new File("src/data/posts.xml");
        File animalsFile = new File("src/data/animals.xml");
        File breedsFile = new File("src/data/breeds.xml");
        File accountsFile = new File("src/data/accounts.xml");
        File usersFile = new File("src/data/users.xml");
        File requestsFile = new File("src/data/requests.xml");
        File messagesFile = new File("src/data/messages.xml");
        File addressesFile = new File("src/data/addresses.xml");
        File speciesFile = new File("src/data/species.xml");

//        File fajlKorisnici = new File("./podaci/korisnici.xml");
//        File fajlTipoviJela = new File("./podaci/tipoviJela.xml");
//        File fajlJela = new File("./podaci/jela.xml");
//        File fajlCenovnik = new File("./podaci/cenovnik.xml");
        OutputStream osPosts = new BufferedOutputStream(new FileOutputStream(postsFile));
        OutputStream osAnimals = new BufferedOutputStream(new FileOutputStream(animalsFile));
        OutputStream osBreeds = new BufferedOutputStream(new FileOutputStream(breedsFile));
        OutputStream osAccounts = new BufferedOutputStream(new FileOutputStream(accountsFile));
        OutputStream osUsers = new BufferedOutputStream(new FileOutputStream(usersFile));
        OutputStream osRequests = new BufferedOutputStream(new FileOutputStream(requestsFile));
        OutputStream osMessages = new BufferedOutputStream(new FileOutputStream(messagesFile));
        OutputStream osAddresses = new BufferedOutputStream(new FileOutputStream(addressesFile));
        OutputStream osSpecies = new BufferedOutputStream(new FileOutputStream(speciesFile));
//        OutputStream osKorisnici = new BufferedOutputStream(new FileOutputStream(fajlKorisnici));
//        OutputStream osTipoviJela = new BufferedOutputStream(new FileOutputStream(fajlTipoviJela));
//        OutputStream osJela = new BufferedOutputStream(new FileOutputStream(fajlJela));
//        OutputStream osCenovnik = new BufferedOutputStream(new FileOutputStream(fajlCenovnik));
        try {
            xstream.toXML(PostList.getInstance(), osPosts);
            xstream.toXML(AnimalList.getInstance(), osAnimals);
            xstream.toXML(BreedList.getInstance(), osBreeds);
            xstream.toXML(AccountsList.getInstance(), osAccounts);
            xstream.toXML(UsersList.getInstance(), osUsers);
            xstream.toXML(RequestsList.getInstance(), osRequests);
            xstream.toXML(MessagesList.getInstance(), osMessages);
            xstream.toXML(AddressList.getInstance(), osAddresses);
            xstream.toXML(SpeciesList.getInstance(), osSpecies);

//            xstream.toXML(KorisniciLista.getInstance(), osKorisnici);
//            xstream.toXML(TipJelaLista.getInstance(), osTipoviJela);
//            xstream.toXML(JelaLista.getInstance(), osJela);
//            xstream.toXML(CenovnikLista.getInstance(), osCenovnik);
        } finally {
            osPosts.close();
            osAnimals.close();
            osBreeds.close();
            osAccounts.close();
            osUsers.close();
            osRequests.close();
            osMessages.close();
            osAddresses.close();
            osSpecies.close();
//            osKorisnici.close();
//            osTipoviJela.close();
//            osJela.close();
//            osCenovnik.close();
        }
    }

    public void load() throws IOException {
        File postsFile = new File("src/data/posts.xml");
        File animalsFile = new File("src/data/animals.xml");
        File breedsFile = new File("src/data/breeds.xml");
        File accountsFile = new File("src/data/accounts.xml");
        File usersFile = new File("src/data/users.xml");
        File requestsFile = new File("src/data/requests.xml");
        File messagesFile = new File("src/data/messages.xml");
        File addressesFile = new File("src/data/addresses.xml");
        File speciesFile = new File("src/data/species.xml");

//        File fajlKorisnici = new File("./podaci/korisnici.xml");
//        File fajlTipoviJela = new File("./podaci/tipoviJela.xml");
//        File fajlJela = new File("./podaci/jela.xml");
//        File fajlCenovnik = new File("./podaci/cenovnik.xml");
        InputStream isPosts = new BufferedInputStream(new FileInputStream(postsFile));
        InputStream isAnimals = new BufferedInputStream(new FileInputStream(animalsFile));
        InputStream isBreeds = new BufferedInputStream(new FileInputStream(breedsFile));
        InputStream isAccounts = new BufferedInputStream(new FileInputStream(accountsFile));
        InputStream isUsers = new BufferedInputStream(new FileInputStream(usersFile));
        InputStream isRequests = new BufferedInputStream(new FileInputStream(requestsFile));
        InputStream isMessages = new BufferedInputStream(new FileInputStream(messagesFile));
        InputStream isAddresses = new BufferedInputStream(new FileInputStream(addressesFile));
        InputStream isSpecies = new BufferedInputStream(new FileInputStream(speciesFile));

//        InputStream isKorisnici = new BufferedInputStream(new FileInputStream(fajlKorisnici));
//        InputStream isTipoviJela = new BufferedInputStream(new FileInputStream(fajlTipoviJela));
//        InputStream isJela = new BufferedInputStream(new FileInputStream(fajlJela));
//        InputStream isCenovnik = new BufferedInputStream(new FileInputStream(fajlCenovnik));
        PostList postList = null;
        AnimalList animalList = null;
        BreedList breedList = null;
        AccountsList accountsList = null;
        UsersList usersList = null;
        RequestsList requestsList = null;
        MessagesList messagesList = null;
        AddressList addressList = null;
        SpeciesList speciesList = null;
//        KorisniciLista korisniciLista = null;
//        TipJelaLista tipJelaLista = null;
//        JelaLista jelaLista = null;
//        CenovnikLista cenovnikLista = null;
        try {
            postList = ((PostList) xstream.fromXML(isPosts));
            animalList = ((AnimalList) xstream.fromXML(isAnimals));
            breedList = ((BreedList) xstream.fromXML(isBreeds));
            accountsList = ((AccountsList) xstream.fromXML(isAccounts));
            usersList = ((UsersList) xstream.fromXML(isUsers));
            requestsList = ((RequestsList) xstream.fromXML(isRequests));
            messagesList = ((MessagesList) xstream.fromXML(isMessages));
            addressList = ((AddressList) xstream.fromXML(isAddresses));
            speciesList = ((SpeciesList) xstream.fromXML(isSpecies));

//            korisniciLista = ((KorisniciLista) xstream.fromXML(isKorisnici));
//            tipJelaLista = ((TipJelaLista) xstream.fromXML(isTipoviJela));
//            jelaLista = ((JelaLista) xstream.fromXML(isJela));
//            cenovnikLista = ((CenovnikLista) xstream.fromXML(isCenovnik));
        } finally {
            isPosts.close();
            isAnimals.close();
            isBreeds.close();
            isAccounts.close();
            isUsers.close();
            isRequests.close();
            isMessages.close();
            isAddresses.close();
            isSpecies.close();
//            isKorisnici.close();
//            isTipoviJela.close();
//            isJela.close();
//            isCenovnik.close();
        }
        PostList.setInstance(postList);
        AnimalList.setInstance(animalList);
        BreedList.setInstance(breedList);
        AccountsList.setInstance(accountsList);
        UsersList.setInstance(usersList);
        RequestsList.setInstance(requestsList);
        MessagesList.setInstance(messagesList);
        AddressList.setInstance(addressList);
        SpeciesList.setInstance(speciesList);
//        KorisniciLista.setInstance(korisniciLista);
//        TipJelaLista.setInstance(tipJelaLista);
//        JelaLista.setInstance(jelaLista);
//        CenovnikLista.setInstance(cenovnikLista);
    }

    public XStream getXStream() {
        return xstream;
    }
}
