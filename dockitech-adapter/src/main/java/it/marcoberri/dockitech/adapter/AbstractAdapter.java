package it.marcoberri.dockitech.adapter;

import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.model.DTText;
import it.marcoberri.dockitech.model.DTToken;

import java.util.Properties;

import javax.swing.text.DefaultTextUI;

public abstract class AbstractAdapter {

    public abstract DTClient createWorld(DTClient client);

    public abstract void dropWorld();

    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain);

    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain, boolean withFile);

    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain, String lang);

    public abstract DTDocument getDocumentByTitle(DTClient client, String titlePlain, String lang, boolean withFile);

    public abstract DTSecurityUser getUserByNick(DTClient client, String nickname);

    public abstract void initAdapter(Properties p);

    public abstract DTText saveText(DTClient client, String text);

    public abstract DTText saveText(DTClient client, String lang, String text);

    public abstract DTSecurityUser autenticate(DTClient client, String username, String password);

    public abstract DTSecurityUser autenticate(String token);

    public abstract String saveToken(DTClient clinet, DTSecurityUser user);

}
