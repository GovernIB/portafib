package es.caib.portafib.it.actors;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import java.io.IOException;

public class Inbox {

    private final Folder folder;

    public Inbox(Session session, String email, String pass) {
        try {
            Store store = session.getStore("pop3");
            store.connect(email, pass);
            this.folder = store.getFolder("INBOX");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public int getMessageCount(long wait) {
        sleep(wait);
        return getMessageCount();
    }

    private int getMessageCount() {
        try {
            folder.open(Folder.READ_ONLY);
            return folder.getMessageCount();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                folder.close(false);
            } catch (MessagingException ignored) {}
        }
    }

    public Missatge pollLastMessage(long wait) {
        sleep(wait);
        try {
            folder.open(Folder.READ_WRITE);
            int messageCount = folder.getMessageCount();
            if (messageCount == 0) {
                return null;
            }

            Message message = folder.getMessage(messageCount);
            message.setFlag(Flags.Flag.DELETED, true);

            return new Missatge(message.getSubject(), (String) message.getContent());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                folder.close(true);
            } catch (MessagingException ignored) {}
        }
    }

    private void sleep(long wait) {
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static class Missatge {
        private final String assumpte;
        private final String contingut;

        public Missatge(String assumpte, String contingut) {
            this.assumpte = assumpte;
            this.contingut = contingut;
        }

        public String getAssumpte() {
            return assumpte;
        }

        public String getContingut() {
            return contingut;
        }
    }
}
