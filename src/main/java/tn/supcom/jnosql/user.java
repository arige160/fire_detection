package tn.supcom.jnosql;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

@Entity
public class user {
    @Id
    private int id;
    @Column
    private String username;
    @Column
    private String password;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public user(){

    }
    private  user(int id,String username,String password){
        this.id=id;
        this.username=username;
        this.password=password;
    }
    @Override
    public String toString() {
        return "user{" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}
