package test;

import com.sun.jndi.ldap.PersistentSearchControl;
import me.henry.persistent.Persistent;
import test.configs.MyYamlConfig;

public class Main {
    public static void main(String[] args) {
        Persistent configHandler = new Persistent();
        configHandler.register(new MyYamlConfig());
    }
}
