package pers.lbreak.myutils.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 万能适配器
 */
public abstract class RvAdapter<T,S extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<S>{
    private List<T> data;
    private int layoutId;

    public RvAdapter(int layoutId, List<T> data) {
        this.data = data;
        this.layoutId = layoutId;
    }

    @Override
    public S onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        return createViewHolder(v);
    }

    @Override
    public void onBindViewHolder(S holder, int position) {
        bindViewHolder(holder,position, data.get(position));
    }
    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }
    public abstract S createViewHolder(View view);

    /**
     * 绑定数据
     * @param holder
     * @param position
     * @param t 数据
     */
    public abstract void bindViewHolder(S holder,int position, T t);


}


