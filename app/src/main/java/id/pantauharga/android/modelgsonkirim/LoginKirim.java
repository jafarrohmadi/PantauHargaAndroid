package id.pantauharga.android.modelgsonkirim;

/**
 * Created by Gulajava Ministudio on 11/6/15.
 */
public class LoginKirim {

    private String username = "";
    private String password = "";
    private String nama = "";
    private String email = "";

    public LoginKirim() {
    }

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

    public String getNama(){return nama;}

    public  void setNama(String nama){this.nama = nama;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email =email;}
}
