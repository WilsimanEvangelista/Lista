package silva.evangelista.santos.wilsiman.lista.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import silva.evangelista.santos.wilsiman.lista.R;
import silva.evangelista.santos.wilsiman.lista.activity.MainActivity;
import silva.evangelista.santos.wilsiman.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    //Deve aceitar dois parâmetros (Intância para a classe MainActivity, Lista de objetos do tipo MyItem)
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    //Criar os elementos de interface para um item. Esses elementos são guardados em uma classe container do tipo ViewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Otenção de um inflador de layouts
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //Uso do inflador para criar os elementos de interface referentes a um item e os guardar dentro de um objeto do tipo View
        View v = inflater.inflate(R.layout.item_list,parent,false);
        //O objeto do tipo View(v) é guardado dentro de um objeto do tipo MyViewHolder que é retornado
        return new MyViewHolder(v);
    }

    @Override
    //Recebe o ViewHolder criado por OnCreateViewHolder e preenche os elementos de UI com os dados do item
    //Recebe dois parâmetros (holder - Objeto do tipo ViewHolder que guarda os itens de interface criados na execução de onCreateViewHolder, position - indica qual elemento da lista deve ser usado para preencher o item)
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Obtenção do item que serpa usado para preencher a UI
        MyItem myItem = itens.get(position);
        //Obtenção do objeto do tipo View que está guardado dentro do ViewHolder
        View v = holder.itemView;
        //Preenchimento do UI com os dados do item
        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageBitmap(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.description);
    }

    @Override
    //Informa quantos elementos a lista possui
    public int getItemCount(){
        return itens.size();
    }
}
