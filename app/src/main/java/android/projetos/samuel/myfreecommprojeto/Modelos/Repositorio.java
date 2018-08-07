package android.projetos.samuel.myfreecommprojeto.Modelos;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// Classe modelo para os repositorios
public class Repositorio implements Parcelable
{
    public int id;
    public String name;
    public String description;
    public int stargazers_count;
    public int forks_count;
    public owner owner;
    public String full_name;
    public String pulls_url;

    public Repositorio(){}

    protected Repositorio(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        stargazers_count = in.readInt();
        forks_count = in.readInt();
        full_name = in.readString();
        pulls_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(stargazers_count);
        dest.writeInt(forks_count);
        dest.writeString(full_name);
        dest.writeString(pulls_url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Repositorio> CREATOR = new Creator<Repositorio>() {
        @Override
        public Repositorio createFromParcel(Parcel in) {
            return new Repositorio(in);
        }

        @Override
        public Repositorio[] newArray(int size) {
            return new Repositorio[size];
        }
    };

    public Repositorio getJson(String reposta)
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        Repositorio repositorio = gson.fromJson(reposta, Repositorio.class);
        return repositorio;
    }

    public class owner
    {
        public String login;
        public String avatar_url;
    }
}
// Fim da classe
