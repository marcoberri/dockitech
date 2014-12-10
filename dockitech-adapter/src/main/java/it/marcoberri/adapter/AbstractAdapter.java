package it.marcoberri.adapter;

import it.marcoberri.dockitech.model.DTClient;

import java.util.Properties;

public abstract class AbstractAdapter {

    public abstract void initAdapter(Properties p);
    
    public abstract DTClient createWorld(DTClient client);
    
    public abstract  void dropWorld();
    
}
