package android.projetos.samuel.myfreecommprojeto;

import android.os.Bundle;
import android.projetos.samuel.myfreecommprojeto.Modelos.Repositorio;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RepositorioActivity extends AppCompatActivity {

    private Repositorio repositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.repositorio = (getIntent().getExtras().getParcelable("repositorio") != null) ? (Repositorio) getIntent().getExtras().getParcelable("repositorio") : new Repositorio();
    }

    public Repositorio getRepositorio() {
        return this.repositorio;
    }

}
