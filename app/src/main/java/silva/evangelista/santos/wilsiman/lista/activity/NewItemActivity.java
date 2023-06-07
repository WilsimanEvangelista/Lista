package silva.evangelista.santos.wilsiman.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import silva.evangelista.santos.wilsiman.lista.R;
import silva.evangelista.santos.wilsiman.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();
        if (selectPhotoLocation != null) {
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
            imvfotoPreview.setImageURI(selectPhotoLocation);
        }

        //Obtenção do ImageButton
        ImageButton imgCl = findViewById(R.id.imbCl);
        //Definição de um ouvidor de cliques no ImageButton
        imgCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Execução da abertura de galeria para escolha da foto
                //Intent implícito
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //Setar intent para informar que estamos interessados apenas em documentos com mimetype "image/*"
                photoPickerIntent.setType("image/*");
                //Executar o intent atrvés do método (O resultado é a imagem selecionada)
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        //Obtenção do button
        Button btnAddItem = findViewById(R.id.btnAddItem);
        //Setar um ouvidor de cliques
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verificação se os campos foram preenchidos pelos usuários
                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

            //Como uma activity pode retornar dados para a Activity que chamou
                //Criação do Intent - Serve para guardar os dados a serem retornados para MainActivity
                Intent i = new Intent();
                //Setamos o Uri da imagem escolhida dentro do intent
                i.setData(photoSelected);
                //Setamos o título e a descrição
                i.putExtra("title",title);
                i.putExtra("description", description);
                //Usamos o método setResult para indicar o resultado da Activity - O código RESULT_OK indica que há dados de retorno
                setResult(Activity.RESULT_OK, i);
                //Activity é finalizada
                finish();
            }
        });
    }

    @Override
    //Passa três parâmetros (requestCode = qual chamada de startActivity essa resposta se refere; resultCode = Retorna se a Activity de destino retornou com sucesso ou não; data = Intent que contém os dados retornados pela Activity de destino;
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Verifica se requestCode é referente ao fornecido na chamada de startActivity com id PHOTO...
        if (requestCode == PHOTO_PICKER_REQUEST) {
            //Verifica se resultCode é um código de sucesso
            if (resultCode == Activity.RESULT_OK) {
                Uri photoSelected = data.getData();
                //Obtenção de ImageView
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                //Setar o Uri no ImageView para que foto seja exibida
                imvfotoPreview.setImageURI(photoSelected);

                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);
                vm.setSelectPhotoLocation(photoSelected);
            }
        }
    }
}