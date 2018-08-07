package android.projetos.samuel.myfreecommprojeto.Adapters;

import android.content.Context;
import android.projetos.samuel.myfreecommprojeto.Interface.RecycleViewInterface;
import android.projetos.samuel.myfreecommprojeto.Modelos.Repositorio;
import android.projetos.samuel.myfreecommprojeto.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

// Classe responsavel pela listagem de repositorios
public class RepositoriosAdapter extends RecyclerView.Adapter<RepositoriosAdapter.RepositorioViewHolder>
{
    private RecycleViewInterface listenner;
    private List<Repositorio> repositorios;

    public RepositoriosAdapter(RecycleViewInterface listenner, List<Repositorio> repositorios)
    {
        this.listenner = listenner;
        this.repositorios = repositorios;
    }

    @NonNull
    @Override
    public RepositorioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_repositorios, parent, false);
        RepositorioViewHolder repositorioViewHolder = new RepositorioViewHolder(view);
        return repositorioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepositorioViewHolder holder, final int position)
    {
        Repositorio repositorio = this.repositorios.get(position);
        holder.txt_nome.setText(repositorio.name);
        holder.txt_descricao.setText(repositorio.description);
        holder.txt_stars.setText(String.valueOf(repositorio.stargazers_count));
        holder.txt_forks.setText(String.valueOf(repositorio.forks_count));
        holder.txt_nome_completo.setText(repositorio.full_name);
        holder.txt_autor.setText(repositorio.owner.login);

        String url = repositorio.owner.avatar_url;
        Picasso.get().load(url).into(holder.img_autor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.getClick(position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return this.repositorios.size();
    }

    public class RepositorioViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_nome;
        TextView txt_descricao;
        TextView txt_forks;
        TextView txt_stars;
        TextView txt_autor;
        TextView txt_nome_completo;
        ImageView img_autor;

        public RepositorioViewHolder(View view)
        {
            super(view);
            txt_nome = (TextView) view.findViewById(R.id.txt_nome_repositorio);
            txt_descricao = (TextView) view.findViewById(R.id.txt_descricao);
            txt_forks = (TextView) view.findViewById(R.id.txt_forks);
            txt_stars = (TextView) view.findViewById(R.id.txt_stars);
            txt_autor = (TextView) view.findViewById(R.id.txt_autor);
            txt_nome_completo = (TextView) view.findViewById(R.id.txt_nome_completo);
            img_autor = (ImageView) view.findViewById(R.id.img_imagem_repositorio);
        }
    }
}
// Fim da classe