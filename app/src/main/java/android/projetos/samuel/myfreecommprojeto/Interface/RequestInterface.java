package android.projetos.samuel.myfreecommprojeto.Interface;

import org.json.JSONException;
import org.json.JSONObject;

// Interface de comunicacao entre fragmentos e request
public interface RequestInterface
{
    public void getPullRequest(String json) throws JSONException;
    public void getPullRequestError(String mensagem);
}
// Fim da classe