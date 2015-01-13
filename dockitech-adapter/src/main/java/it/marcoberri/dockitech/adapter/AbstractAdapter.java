package it.marcoberri.dockitech.adapter;

import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTLanguage;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.model.DTText;
import it.marcoberri.dockitech.model.DTToken;

public abstract class AbstractAdapter {

    public abstract AbstractAdapter getSession();

    public abstract DTClient createWorld(DTClient client);

    public abstract void dropUniverse();

    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain);

    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain, boolean withFile);

    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain, String lang);

    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain, String lang, boolean withFile);

    public abstract DTSecurityUser getUserByNick(DTClient client, String nickname);

    public abstract DTText saveText(DTClient client, String text);

    public abstract DTText saveText(DTClient client, String lang, String text);

    public abstract DTToken autenticate(DTClient client, String username, String password);

    public abstract DTToken autenticate(DTClient client,String token);
    
    public abstract DTToken autenticate(String token);
    
    public abstract DTToken autenticate(String client, String nickname, String password);

    public abstract DTToken saveToken(DTClient clinet, DTSecurityUser user);
    
    public abstract DTToken getToken(DTClient client, DTSecurityUser user);
    
    public abstract DTClient getClientByTitle(String title);
    
    public abstract DTLanguage isLangEnable(DTClient client, String lang);
    

}
