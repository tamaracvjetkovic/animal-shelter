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
        xstream.processAnnotations(CommentsList.class);
    }

    public void save() throws IOException {
        File postsFile = new File("src/data/posts.xml");
        File animalsFile = new File("src/data/animals.xml");
        File breedsFile = new File("src/data/breeds.xml");
        File accountsFile = new File("src/data/accounts.xml");
        File usersFile = new File("src/data/users.xml");
        File requestsFile = new File("src/data/requests.xml");
        File messagesFile = new File("src/data/messages.xml");
        File commentsFile = new File("src/data/comments.xml");

        OutputStream osPosts = new BufferedOutputStream(new FileOutputStream(postsFile));
        OutputStream osAnimals = new BufferedOutputStream(new FileOutputStream(animalsFile));
        OutputStream osBreeds = new BufferedOutputStream(new FileOutputStream(breedsFile));
        OutputStream osAccounts = new BufferedOutputStream(new FileOutputStream(accountsFile));
        OutputStream osUsers = new BufferedOutputStream(new FileOutputStream(usersFile));
        OutputStream osRequests = new BufferedOutputStream(new FileOutputStream(requestsFile));
        OutputStream osMessages = new BufferedOutputStream(new FileOutputStream(messagesFile));
        OutputStream osComments = new BufferedOutputStream(new FileOutputStream(commentsFile));

        try {
            xstream.toXML(PostList.getInstance(), osPosts);
            xstream.toXML(AnimalList.getInstance(), osAnimals);
            xstream.toXML(BreedList.getInstance(), osBreeds);
            xstream.toXML(AccountsList.getInstance(), osAccounts);
            xstream.toXML(UsersList.getInstance(), osUsers);
            xstream.toXML(RequestsList.getInstance(), osRequests);
            xstream.toXML(MessagesList.getInstance(), osMessages);
            xstream.toXML(CommentsList.getInstance(), osComments);

        } finally {
            osPosts.close();
            osAnimals.close();
            osBreeds.close();
            osAccounts.close();
            osUsers.close();
            osRequests.close();
            osMessages.close();
            osComments.close();
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
        File commentsFile = new File("src/data/comments.xml");

        InputStream isPosts = new BufferedInputStream(new FileInputStream(postsFile));
        InputStream isAnimals = new BufferedInputStream(new FileInputStream(animalsFile));
        InputStream isBreeds = new BufferedInputStream(new FileInputStream(breedsFile));
        InputStream isAccounts = new BufferedInputStream(new FileInputStream(accountsFile));
        InputStream isUsers = new BufferedInputStream(new FileInputStream(usersFile));
        InputStream isRequests = new BufferedInputStream(new FileInputStream(requestsFile));
        InputStream isMessages = new BufferedInputStream(new FileInputStream(messagesFile));
        InputStream isComments = new BufferedInputStream(new FileInputStream(commentsFile));

        PostList postList = null;
        AnimalList animalList = null;
        BreedList breedList = null;
        AccountsList accountsList = null;
        UsersList usersList = null;
        RequestsList requestsList = null;
        MessagesList messagesList = null;
        CommentsList commentsList = null;

        try {
            postList = ((PostList) xstream.fromXML(isPosts));
            animalList = ((AnimalList) xstream.fromXML(isAnimals));
            breedList = ((BreedList) xstream.fromXML(isBreeds));
            accountsList = ((AccountsList) xstream.fromXML(isAccounts));
            usersList = ((UsersList) xstream.fromXML(isUsers));
            requestsList = ((RequestsList) xstream.fromXML(isRequests));
            messagesList = ((MessagesList) xstream.fromXML(isMessages));
            commentsList = ((CommentsList) xstream.fromXML(isComments));

        } finally {
            isPosts.close();
            isAnimals.close();
            isBreeds.close();
            isAccounts.close();
            isUsers.close();
            isRequests.close();
            isMessages.close();
            isComments.close();
        }
        PostList.setInstance(postList);
        AnimalList.setInstance(animalList);
        BreedList.setInstance(breedList);
        AccountsList.setInstance(accountsList);
        UsersList.setInstance(usersList);
        RequestsList.setInstance(requestsList);
        MessagesList.setInstance(messagesList);
        CommentsList.setInstance(commentsList);
    }

    public XStream getXStream() {
        return xstream;
    }
}
