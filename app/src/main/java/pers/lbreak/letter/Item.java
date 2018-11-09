package pers.lbreak.letter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by break on 2018/11/5.
 */

public class Item extends RecyclerView.ViewHolder {
    ViewDataBinding binding;
    public Item(View itemView) {
        super(itemView);
        binding= DataBindingUtil.bind(itemView);
    }
    public Item setData(int res,Object o) {
       binding.setVariable(res, o);
       binding.executePendingBindings();
       return this;
    }
}
