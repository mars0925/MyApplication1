package com.example.user.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 2018/3/23.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>{
    private int etFocusPos = -1;
    private Context context;

    public Myadapter(Context context){
        this.context = context;
    }
    @Override
    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Myadapter.ViewHolder holder, int i) {
        Log.e("tag","绑定Holder,index="+i);
        final int position = i;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv.setText("item "+position);
        viewHolder.ed.setText(etTextAry.get(position));
        viewHolder.ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    etFocusPos = position;
                    Log.e("tag","etFocusPos焦点选中-"+etFocusPos);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        EditText ed;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
            ed = itemView.findViewById(R.id.editText);

        }
    }

    SparseArray<String> etTextAry = new SparseArray();
    //监听文字变化的TextWatcher接口
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            //保存输入的文字内容
            etTextAry.put(etFocusPos, s.toString());
        }
    };

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        Log.e("tag","隐藏item="+holder.getAdapterPosition());
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.ed.removeTextChangedListener(textWatcher);
        viewHolder.ed.clearFocus();

    }
}
