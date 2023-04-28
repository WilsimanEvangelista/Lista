package silva.evangelista.santos.wilsiman.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import silva.evangelista.santos.wilsiman.lista.R;
import silva.evangelista.santos.wilsiman.lista.adapter.MyAdapter;
import silva.evangelista.santos.wilsiman.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Verificação se as condições de retorno foram cumpridas
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                //Criação de myItem para guardar os dados do item
                MyItem myItem = new MyItem();
                //Obtenção dos dados retornados por NewItemActivity e os guardamos em myItem
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();
                //Adição dos itens à uma lista de itens
                itens.add(myItem);
                //Para que o novo item seja mostrado no RecycleView, o Adapter precisa ser notificado
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }

    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenção do botão FAB
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //Registro de um ouvidor de cliques
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent explícito para navegar para NewItemActivity
                Intent i = new Intent(MainActivity.this,NewItemActivity.class);
                //Execução do intent usando um método especial, este assume que a activity destino irá retornar com dados em algum momento
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });
        //Obtenção do RecycleView
        RecyclerView rvItens = findViewById(R.id.rvItens);
        //Criação do myAdapter e é setado no RecycleView
        myAdapter = new MyAdapter(this,itens);
        rvItens.setAdapter(myAdapter);
        //setHasFixedSize indica ao RecycleView que não há variação de tamanho entre os itens da lista - Construção da lista fica mais rápida
        rvItens.setHasFixedSize(true);
        //Criação de um gerenciador de layout do tipo linear
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        //setar no RecycleView
        rvItens.setLayoutManager(layoutManager);
        //Criação de um decorador para a lista
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(),DividerItemDecoration.VERTICAL);
        //Setar no RecycleView
        rvItens.addItemDecoration(dividerItemDecoration);
    }
}