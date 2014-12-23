package it.marcoberri.adapter;

import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTEncryptionMethod;
import it.marcoberri.dockitech.model.DTSecurityUser;

import java.util.Properties;

public abstract class AbstractAdapter {

    public abstract void initAdapter(Properties p);

    public abstract DTClient createWorld(DTClient client);

    public abstract void dropWorld();

    public abstract DTSecurityUser getUserByNick(DTClient client, String nickname);
    
    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain);
}
