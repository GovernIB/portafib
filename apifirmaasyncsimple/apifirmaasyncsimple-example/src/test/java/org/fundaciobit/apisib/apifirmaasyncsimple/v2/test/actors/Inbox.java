package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

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

    public int getMessages(long wait) {
        sleep(wait);
        return getMessages();
    }

    private int getMessages() {
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

    private void sleep(long wait) {
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
