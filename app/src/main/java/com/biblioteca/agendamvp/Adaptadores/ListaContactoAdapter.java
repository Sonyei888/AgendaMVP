package com.biblioteca.agendamvp.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biblioteca.agendamvp.R;
import com.biblioteca.agendamvp.VerActivity;
import com.biblioteca.agendamvp.entidades.Contactos;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListaContactoAdapter extends RecyclerView.Adapter<ListaContactoAdapter.ContactoViewHolder> {
    ArrayList<Contactos> listacontacto;

    public ListaContactoAdapter(ArrayList<Contactos> listacontacto) {
        this.listacontacto = listacontacto;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return  new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaContactoAdapter.ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listacontacto.get(position).getNombrecontact());
        holder.viewTelefono.setText(listacontacto.get(position).getTelefonocontact());
        holder.viewCorreo.setText(listacontacto.get(position).getCorreocontact());
    }

    @Override
    public int getItemCount() {
        return listacontacto.size();
    }

    public class
    ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre;
        TextView viewTelefono;
        TextView viewCorreo;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.textView);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listacontacto.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
