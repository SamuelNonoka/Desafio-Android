package android.projetos.samuel.myfreecommprojeto.Adapters;

import android.projetos.samuel.myfreecommprojeto.Interface.RecycleViewInterface;
import android.projetos.samuel.myfreecommprojeto.Modelos.PullRequest;
import android.projetos.samuel.myfreecommprojeto.Modelos.Repositorio;
import android.projetos.samuel.myfreecommprojeto.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

// Classe responsavel pelas celulas das pulls request
public class PullAdapter extends RecyclerView.Adapter<PullAdapter.PullViewHolder>
{
    private RecycleViewInterface listenner;
    private List<PullRequest> pulls;

    public PullAdapter(List<PullRequest> pulls)
    {
        this.listenner = listenner;
        this.pulls = pulls;
    }

    @NonNull
    @Override
    public PullViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pulls, parent, false);
        PullAdapter.PullViewHolder pull_view_holder = new PullAdapter.PullViewHolder(view);
        return pull_view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PullViewHolder holder, int position)
    {
        PullRequest pull = this.pulls.get(position);
        holder.txt_titulo.setText(pull.title);
        holder.txt_descricao.setText(pull.body);
        holder.txt_autor.setText(pull.user.login);

        String url = pull.user.avatar_url;
        Picasso.get().load(url).into(holder.img_autor);
    }

    @Override
    public int getItemCount() {
        return this.pulls.size();
    }

    public class PullViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_titulo;
        TextView txt_descricao;
        TextView txt_autor;
        ImageView img_autor;

        public PullViewHolder(View view)
        {
            super(view);
            txt_titulo = (TextView) view.findViewById(R.id.txt_titulo_pull);
            txt_descricao = (TextView) view.findViewById(R.id.txt_descricao_pull);
            txt_autor = (TextView) view.findViewById(R.id.txt_autor_pull);
            img_autor = (ImageView) view.findViewById(R.id.img_pull);
        }
    }
}
// fim da classe