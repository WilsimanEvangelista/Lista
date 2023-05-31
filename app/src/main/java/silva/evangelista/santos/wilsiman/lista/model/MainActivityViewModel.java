package silva.evangelista.santos.wilsiman.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

//Abaixo guardamos a lista de itens cadastrados
public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>();

    //Abaixo obtemos a lista de itens
    public List<MyItem> getItens() {
        return itens;
    }
}
