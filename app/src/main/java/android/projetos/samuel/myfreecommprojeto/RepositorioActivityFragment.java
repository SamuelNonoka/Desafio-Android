package android.projetos.samuel.myfreecommprojeto;

import android.projetos.samuel.myfreecommprojeto.Adapters.PullAdapter;
import android.projetos.samuel.myfreecommprojeto.Adapters.RepositoriosAdapter;
import android.projetos.samuel.myfreecommprojeto.Interface.RequestInterface;
import android.projetos.samuel.myfreecommprojeto.Modelos.Repositorio;
import android.projetos.samuel.myfreecommprojeto.Requests.PullRequest;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class RepositorioActivityFragment extends Fragment implements RequestInterface
{
    private Repositorio repositorio;
    private PullRequest pullRequest;
    private List<android.projetos.samuel.myfreecommprojeto.Modelos.PullRequest> pullRequests;
    private RecyclerView recyclerView;
    private PullAdapter adapter;

    public RepositorioActivityFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_repositorio, container, false);
        repositorio = (getActivity().getIntent().getExtras().getParcelable("repositorio") != null) ? (Repositorio) getActivity().getIntent().getExtras().getParcelable("repositorio") : new Repositorio();
        getActivity().setTitle(repositorio.name);

        this.pullRequests = new ArrayList<>();
        this.pullRequest = new PullRequest(getContext(), this);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycle_repositorio);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        baixarPulls(repositorio.pulls_url);
    }

    // Baixa a lista de pulls do repositorio
    public void baixarPulls(String url) {
        url = url.replace("{/number}", "");
        Log.i("rep", "url: " + url);
        this.pullRequest.getRequest(url);
    }

    @Override
    public void getPullRequest(String response) throws JSONException
    {
        JSONArray json_array = new JSONArray(response);

        Log.i("rep", "json: " + json_array.length());

        for(int i = 0; i < json_array.length(); i++)
        {
            Log.i("rep", "for: ");
            JSONObject item = json_array.getJSONObject(i);
            android.projetos.samuel.myfreecommprojeto.Modelos.PullRequest pull = new android.projetos.samuel.myfreecommprojeto.Modelos.PullRequest();
            pull = pull.getJson(item.toString());
            this.pullRequests.add(pull);
        }

        this.adapter = new PullAdapter(this.pullRequests);
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    public void getPullRequestError(String mensagem) {

    }
}
