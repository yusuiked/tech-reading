package jp.jbug.helloworld.seam.action;

import javax.ejb.Local;

@Local
public interface Authenticator {

    boolean authenticate();

}
