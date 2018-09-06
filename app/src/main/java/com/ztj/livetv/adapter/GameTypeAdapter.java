package com.ztj.livetv.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import com.ztj.androidlib.utils.StringUtil;
import com.ztj.livetv.R;
import com.ztj.livetv.databinding.ItemGameTypeBinding;
import com.ztj.livetv.db.entity.GameTypeInfo;
import com.ztj.livetv.listener.OnItemGameTypeListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhoutianjie
 * @date 2018/9/4
 */

public class GameTypeAdapter extends RecyclerView.Adapter<GameTypeAdapter.GameTypeViewHolder>{

    private List<GameTypeInfo> gameTypeInfos;
    private final OnItemGameTypeListener listener;
    private Context mContext;

    public GameTypeAdapter(OnItemGameTypeListener listener,Context context,List<GameTypeInfo> list){
        this.listener = listener;
        mContext = context;
        gameTypeInfos = new ArrayList<>();
        if(list!=null){
            gameTypeInfos.addAll(list);
        }
    }

    public void setGameTypeInfos(List<GameTypeInfo> list){
        if(gameTypeInfos == null){
            gameTypeInfos = new ArrayList<>();
        }
        gameTypeInfos.addAll(list);
        notifyItemRangeInserted(0,gameTypeInfos.size());
    }


    @NonNull
    @Override
    public GameTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGameTypeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                                        , R.layout.item_game_type,parent,false);
        return new GameTypeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GameTypeViewHolder holder, int position) {
        GameTypeInfo gameTypeInfo = gameTypeInfos.get(position);
        holder.binding.setGameInfo(gameTypeInfo);
        //图片加载
        String iconUrl = gameTypeInfo.getGameIcon();
        if(!StringUtil.isNull(iconUrl)){
            Glide.with(mContext).load(iconUrl)
                    .into(holder.binding.gameTypeIconIv);
        }
        holder.binding.setListener(listener);
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return gameTypeInfos.size();
    }

    static class GameTypeViewHolder extends RecyclerView.ViewHolder{
        final ItemGameTypeBinding binding;
        public GameTypeViewHolder(ItemGameTypeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
