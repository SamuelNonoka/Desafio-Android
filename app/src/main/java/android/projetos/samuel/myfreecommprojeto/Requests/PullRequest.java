package android.projetos.samuel.myfreecommprojeto.Requests;

import android.content.Context;
import android.projetos.samuel.myfreecommprojeto.Interface.RequestInterface;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;

// Classe responsável por fazer uma requisição do tipo get
public class PullRequest
{
    private Context context;
    private RequestInterface listenner;

    public PullRequest(Context context, RequestInterface listenner)
    {
        this.context = context;
        this.listenner = listenner;
    }

    // Faz uma requisicao get no servidor
    public void getRequest(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //JSONObject json_string = new JSONObject(response.toString());
                    //listenner.getPullRequest(json_string);
                    listenner.getPullRequest(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listenner.getPullRequestError("Não foi possível baixar o repositório");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listenner.getPullRequestError("Não foi possível baixar o repositório");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
// Fim da classe