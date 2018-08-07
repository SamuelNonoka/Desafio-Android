package android.projetos.samuel.myfreecommprojeto;

import android.content.Intent;
import android.os.Parcelable;
import android.projetos.samuel.myfreecommprojeto.Adapters.RepositoriosAdapter;
import android.projetos.samuel.myfreecommprojeto.Interface.RecycleViewInterface;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Fragmento responsável pela tela de repositorios
public class MainActivityFragment extends Fragment implements RequestInterface, RecycleViewInterface
{
    private final String github_repositorios_url = "https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1";
    private PullRequest pullRequest;
    private List<Repositorio> repositorios;
    private RecyclerView recyclerView;
    private RepositoriosAdapter adapter;

    public MainActivityFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle(getResources().getString(R.string.titulo_tela_principal));

        this.repositorios = new ArrayList<>();
        this.pullRequest = new PullRequest(getContext(), this);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycle_repositorios);
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
        this.baixarRepositoris();
    }

    // Baixa a lista de repositorios publicos
    public void baixarRepositoris() {
        this.pullRequest.getRequest(this.github_repositorios_url);
    }

    @Override
    public void getPullRequest(String response) throws JSONException
    {
        JSONObject json = new JSONObject(response);
        JSONArray json_array = json.getJSONArray("items");

        for(int i = 0; i < json_array.length(); i++)
        {
            JSONObject item = json_array.getJSONObject(i);
            Repositorio repositorio = new Repositorio();
            repositorio = repositorio.getJson(item.toString());
            this.repositorios.add(repositorio);
        }

        this.adapter = new RepositoriosAdapter(this, this.repositorios);
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    public void getPullRequestError(String mensagem) {

    }

    @Override
    public void getClick(int position)
    {
        Repositorio repositorio =  this.repositorios.get(position);
        Intent i = new Intent(getActivity(), RepositorioActivity.class);
        i.putExtra("repositorio", (Parcelable) repositorio);
        startActivity(i);
    }

}
// Fim da classe