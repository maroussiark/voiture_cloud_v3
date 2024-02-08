package cloud.voiture.model.response;

import cloud.voiture.model.Utilisateur;

public class LoginRes {
    private String email;
    private String token;
    Utilisateur utilisateur;


    public LoginRes(String email, String token, Utilisateur utilisateur) {
        this.email = email;
        this.token = token;
        this.utilisateur = utilisateur;
    }


    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    public LoginRes(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
