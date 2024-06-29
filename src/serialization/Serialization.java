package serialization;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import domain.serializeddata.AnimalList;
import domain.serializeddata.BreedList;
import domain.serializeddata.PostList;

public class Serialization {
    private XStream xstream;

    public Serialization() {
        xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.processAnnotations(PostList.class);
        xstream.processAnnotations(AnimalList.class);
        xstream.processAnnotations(BreedList.class);
//        xstream.processAnnotations(KorisniciLista.class);
//        xstream.processAnnotations(TipJelaLista.class);
//        xstream.processAnnotations(JelaLista.class);
//        xstream.processAnnotations(CenovnikLista.class);
    }

    public void save() throws IOException {
        File postsFile = new File("src/data/posts.xml");
        File animalsFile = new File("src/data/animals.xml");
        File breedsFile = new File("src/data/breeds.xml");
//        File fajlKorisnici = new File("./podaci/korisnici.xml");
//        File fajlTipoviJela = new File("./podaci/tipoviJela.xml");
//        File fajlJela = new File("./podaci/jela.xml");
//        File fajlCenovnik = new File("./podaci/cenovnik.xml");
        OutputStream osPosts = new BufferedOutputStream(new FileOutputStream(postsFile));
        OutputStream osAnimals = new BufferedOutputStream(new FileOutputStream(animalsFile));
        OutputStream osBreeds = new BufferedOutputStream(new FileOutputStream(breedsFile));
//        OutputStream osKorisnici = new BufferedOutputStream(new FileOutputStream(fajlKorisnici));
//        OutputStream osTipoviJela = new BufferedOutputStream(new FileOutputStream(fajlTipoviJela));
//        OutputStream osJela = new BufferedOutputStream(new FileOutputStream(fajlJela));
//        OutputStream osCenovnik = new BufferedOutputStream(new FileOutputStream(fajlCenovnik));
        try {
            xstream.toXML(PostList.getInstance(), osPosts);
            xstream.toXML(AnimalList.getInstance(), osAnimals);
            xstream.toXML(BreedList.getInstance(), osBreeds);
//            xstream.toXML(KorisniciLista.getInstance(), osKorisnici);
//            xstream.toXML(TipJelaLista.getInstance(), osTipoviJela);
//            xstream.toXML(JelaLista.getInstance(), osJela);
//            xstream.toXML(CenovnikLista.getInstance(), osCenovnik);
        } finally {
            osPosts.close();
            osAnimals.close();
            osBreeds.close();
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
//        File fajlKorisnici = new File("./podaci/korisnici.xml");
//        File fajlTipoviJela = new File("./podaci/tipoviJela.xml");
//        File fajlJela = new File("./podaci/jela.xml");
//        File fajlCenovnik = new File("./podaci/cenovnik.xml");
        InputStream isPosts = new BufferedInputStream(new FileInputStream(postsFile));
        InputStream isAnimals = new BufferedInputStream(new FileInputStream(animalsFile));
        InputStream isBreeds = new BufferedInputStream(new FileInputStream(breedsFile));
//        InputStream isKorisnici = new BufferedInputStream(new FileInputStream(fajlKorisnici));
//        InputStream isTipoviJela = new BufferedInputStream(new FileInputStream(fajlTipoviJela));
//        InputStream isJela = new BufferedInputStream(new FileInputStream(fajlJela));
//        InputStream isCenovnik = new BufferedInputStream(new FileInputStream(fajlCenovnik));
        PostList postList = null;
        AnimalList animalList = null;
        BreedList breedList = null;
//        KorisniciLista korisniciLista = null;
//        TipJelaLista tipJelaLista = null;
//        JelaLista jelaLista = null;
//        CenovnikLista cenovnikLista = null;
        try {
            postList = ((PostList) xstream.fromXML(isPosts));
            animalList = ((AnimalList) xstream.fromXML(isAnimals));
            breedList = ((BreedList) xstream.fromXML(isBreeds));
//            korisniciLista = ((KorisniciLista) xstream.fromXML(isKorisnici));
//            tipJelaLista = ((TipJelaLista) xstream.fromXML(isTipoviJela));
//            jelaLista = ((JelaLista) xstream.fromXML(isJela));
//            cenovnikLista = ((CenovnikLista) xstream.fromXML(isCenovnik));
        } finally {
            isPosts.close();
            isAnimals.close();
            isBreeds.close();
//            isKorisnici.close();
//            isTipoviJela.close();
//            isJela.close();
//            isCenovnik.close();
        }
        PostList.setInstance(postList);
        AnimalList.setInstance(animalList);
        BreedList.setInstance(breedList);
//        KorisniciLista.setInstance(korisniciLista);
//        TipJelaLista.setInstance(tipJelaLista);
//        JelaLista.setInstance(jelaLista);
//        CenovnikLista.setInstance(cenovnikLista);
    }

    public XStream getXStream() {
        return xstream;
    }
}
