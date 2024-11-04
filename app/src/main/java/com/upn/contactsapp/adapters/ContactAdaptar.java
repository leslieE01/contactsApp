package com.upn.contactsapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.contactsapp.R;
import com.upn.contactsapp.entities.Contact;

import java.util.List;

public class ContactAdaptar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CONTACT = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    private final List<Contact> data;
    private boolean isLoading = false; // Bandera para mostrar la vista de carga

    public ContactAdaptar(List<Contact> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_LOADING) {
            View view = inflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_contact, parent, false);
            return new ContactViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactViewHolder) {
            Contact item = data.get(position);
            ContactViewHolder contactHolder = (ContactViewHolder) holder;

            // Enlaza los datos del contacto
            contactHolder.tvName.setText(item.name);
            contactHolder.tvNumber.setText(item.phone);

            // Descomentar si necesitas cargar la imagen
            // byte[] decodedString = Base64.decode(item.image, Base64.DEFAULT);
            // Bitmap imageBM = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            // contactHolder.ivPhoto.setImageBitmap(imageBM);

            contactHolder.itemView.setOnClickListener(view -> {
                // Aquí puedes agregar el código para manejar el clic en el contacto
                // Intent intent = new Intent(view.getContext(), DetailActivity.class);
                // view.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return isLoading ? data.size() + 1 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == data.size() && isLoading) ? VIEW_TYPE_LOADING : VIEW_TYPE_CONTACT;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (loading) {
            notifyItemInserted(data.size()); // Añade un nuevo item de carga
        } else {
            notifyItemRemoved(data.size()); // Remueve el item de carga
        }
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvNumber;
        ImageView ivPhoto;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            // Aquí podrías agregar lógica para la vista de carga si necesitas personalizarla
        }
    }
}
