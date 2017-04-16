package com.enew.timetracker.modules.home.presentation.presenter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enew.timetracker.R;
import com.enew.timetracker.modules.home.models.HomeItemModel;

import java.util.List;

/**
 * Created by hamoda on 4/11/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private Context mContext;
    private List<HomeItemModel> ImageOrName;


    public HomeAdapter(List<HomeItemModel> ImageOrName) {
        this.ImageOrName = ImageOrName;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup, false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder personViewHolder, int position) {
        personViewHolder.menu_Name.setText(ImageOrName.get(position).name);
        personViewHolder.flagphoto.setImageResource(ImageOrName.get(position).CphotoId);
    }

    @Override
    public int getItemCount() {

        return ImageOrName.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView menu_Name;
        ImageView flagphoto;

        HomeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview);
            menu_Name = (TextView) itemView.findViewById(R.id.menuName);
            flagphoto = (ImageView) itemView.findViewById(R.id.flag_photo);
        }

        @Override
        public void onClick(View v) {

        }
    }

}


