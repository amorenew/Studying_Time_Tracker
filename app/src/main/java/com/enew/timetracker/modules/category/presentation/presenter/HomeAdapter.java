package com.enew.timetracker.modules.category.presentation.presenter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enew.timetracker.R;
import com.enew.timetracker.modules.category.models.HomeItemModel;

import java.util.List;

/**
 * Created by hamoda on 4/11/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.N_MenueViewHolder> {

    private Context mContext;
    private List<HomeItemModel> ImageOrName;


    HomeAdapter(List<HomeItemModel> ImageOrName) {
        this.ImageOrName = ImageOrName;
    }

    @Override
    public N_MenueViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup, false);
        return new N_MenueViewHolder(v);
    }

    @Override
    public void onBindViewHolder(N_MenueViewHolder personViewHolder, int position) {
        personViewHolder.menu_Name.setText(ImageOrName.get(position).name);
        personViewHolder.flagphoto.setImageResource(ImageOrName.get(position).CphotoId);
        personViewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    // Log.d("yes", " "+position);
                    //   Toast.makeText(mContext,"sdsdfsdf",Toast.LENGTH_SHORT).show();
                    Toast.makeText(view.getContext(), "#" + position, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(view.getContext(), "#" + position, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return ImageOrName.size();
    }

    public static class N_MenueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        CardView cv;
        TextView menu_Name;
        ImageView flagphoto;

        private ItemClickListener clickListener;

        N_MenueViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cardview);
            menu_Name = (TextView) itemView.findViewById(R.id.menuName);
            flagphoto = (ImageView) itemView.findViewById(R.id.flag_photo);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {

            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }


    /*
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView titleTextView;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.menuName);itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {

            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {

            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {

            clickListener.onClick(view, getPosition(), true);
            return true;
        }

*/

    // ------------------------
}


