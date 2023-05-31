package silva.evangelista.santos.wilsiman.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {

    //Guardamos somente o endereço URI da foto que foi escolhida pelo usuario
    Uri selectPhotoLocation = null;

    //Abaixo obtemos a lista de itens
    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }

    //Abaixo setamos o endereço URI dentro do ViewModel
    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
