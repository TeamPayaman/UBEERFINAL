package ceng319.teampayaman.ubeerfinal.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ceng319.teampayaman.ubeerfinal.Interface.ItemClickListener;
import ceng319.teampayaman.ubeerfinal.R;

public class DrinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView drink_name;
    public ImageView drink_image;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public DrinkViewHolder(@NonNull View itemView) {
        super(itemView);

        drink_name = (TextView)itemView.findViewById(R.id.drink_name);
        drink_image = (ImageView) itemView.findViewById(R.id.drink_image);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
