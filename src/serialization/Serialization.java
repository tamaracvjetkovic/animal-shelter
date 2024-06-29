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

import domain.serializeddata.PostList;

public class Serialization {
    private XStream xstream;

    public Serialization() {
        xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.processAnnotations(PostList.class);
//        xstream.processAnnotations(KorisniciLista.class);
//        xstream.processAnnotations(TipJelaLista.class);
//        xstream.processAnnotations(JelaLista.class);
//        xstream.processAnnotations(CenovnikLista.class);
    }

    public void save() throws IOException {
        File postsFile = new File("src/data/posts.xml");
//        File fajlKorisnici = new File("./podaci/korisnici.xml");
//        File fajlTipoviJela = new File("./podaci/tipoviJela.xml");
//        File fajlJela = new File("./podaci/jela.xml");
//        File fajlCenovnik = new File("./podaci/cenovnik.xml");
        OutputStream osPosts = new BufferedOutputStream(new FileOutputStream(postsFile));
//        OutputStream osKorisnici = new BufferedOutputStream(new FileOutputStream(fajlKorisnici));
//        OutputStream osTipoviJela = new BufferedOutputStream(new FileOutputStream(fajlTipoviJela));
//        OutputStream osJela = new BufferedOutputStream(new FileOutputStream(fajlJela));
//        OutputStream osCenovnik = new BufferedOutputStream(new FileOutputStream(fajlCenovnik));
        try {
            xstream.toXML(PostList.getInstance(), osPosts);
//            xstream.toXML(KorisniciLista.getInstance(), osKorisnici);
//            xstream.toXML(TipJelaLista.getInstance(), osTipoviJela);
//            xstream.toXML(JelaLista.getInstance(), osJela);
//            xstream.toXML(CenovnikLista.getInstance(), osCenovnik);
        } finally {
            osPosts.close();
//            osKorisnici.close();
//            osTipoviJela.close();
//            osJela.close();
//            osCenovnik.close();
        }
    }

    public void load() throws IOException {
        File postsFile = new File("src/data/posts.xml");
//        File fajlKorisnici = new File("./podaci/korisnici.xml");
//        File fajlTipoviJela = new File("./podaci/tipoviJela.xml");
//        File fajlJela = new File("./podaci/jela.xml");
//        File fajlCenovnik = new File("./podaci/cenovnik.xml");
        InputStream isPosts = new BufferedInputStream(new FileInputStream(postsFile));
//        InputStream isKorisnici = new BufferedInputStream(new FileInputStream(fajlKorisnici));
//        InputStream isTipoviJela = new BufferedInputStream(new FileInputStream(fajlTipoviJela));
//        InputStream isJela = new BufferedInputStream(new FileInputStream(fajlJela));
//        InputStream isCenovnik = new BufferedInputStream(new FileInputStream(fajlCenovnik));
        PostList postList = null;
//        KorisniciLista korisniciLista = null;
//        TipJelaLista tipJelaLista = null;
//        JelaLista jelaLista = null;
//        CenovnikLista cenovnikLista = null;
        try {
            postList = ((PostList) xstream.fromXML(isPosts));
//            korisniciLista = ((KorisniciLista) xstream.fromXML(isKorisnici));
//            tipJelaLista = ((TipJelaLista) xstream.fromXML(isTipoviJela));
//            jelaLista = ((JelaLista) xstream.fromXML(isJela));
//            cenovnikLista = ((CenovnikLista) xstream.fromXML(isCenovnik));
        } finally {
            isPosts.close();
//            isKorisnici.close();
//            isTipoviJela.close();
//            isJela.close();
//            isCenovnik.close();
        }
        PostList.setInstance(postList);
//        KorisniciLista.setInstance(korisniciLista);
//        TipJelaLista.setInstance(tipJelaLista);
//        JelaLista.setInstance(jelaLista);
//        CenovnikLista.setInstance(cenovnikLista);
    }

    public XStream getXStream() {
        return xstream;
    }
}
