package android.projetos.samuel.myfreecommprojeto.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// Classe modelo para as pull request
public class PullRequest
{
    public int id;
    public String title;
    public String body;
    public user user;

    public PullRequest() { }

    public class user
    {
        public String login;
        public String avatar_url;
    }

    public PullRequest getJson(String reposta)
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        PullRequest pull_request = gson.fromJson(reposta, PullRequest.class);
        return pull_request;
    }

}
// Fim da classe