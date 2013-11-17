package jp.jbug.example.seam.action;

import javax.ejb.Local;

@Local
public interface Authenticator {

    boolean authenticate();

}
