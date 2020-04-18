package com.mgkhnyldz.todoapp.utils.MyAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mgkhnyldz.todoapp.utils.DataModel;
import com.mgkhnyldz.todoapp.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Activity activity;
    Context context;
    ArrayList<DataModel> mData;
    DataModel mRecentlyDeletedItem;
    int mRecentlyDeletedItemPosition;

    public MyAdapter(Context context, ArrayList<DataModel> mData, Activity activity) {
        this.context = context;
        this.mData = mData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel model = mData.get(position);
        holder.tvHeader.setText(model.getHeader());
        holder.tvSubHeader.setText(model.getSubHeader());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHeader, tvSubHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
            tvSubHeader = itemView.findViewById(R.id.tvSubHeader);
        }
    }


    public void deleteItem(int position) {
        mRecentlyDeletedItem = mData.get(position);
        mRecentlyDeletedItemPosition = position;
        mData.remove(position);
        notifyItemRemoved(position);
        showUndoSnackBar();
    }

    private void showUndoSnackBar() {
        View view = activity.findViewById(R.id.recycleFrame);
        Snackbar snackbar = Snackbar.make(view, "Geri al", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Geri al", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.add(mRecentlyDeletedItemPosition, mRecentlyDeletedItem);
                notifyItemInserted(mRecentlyDeletedItemPosition);

            }
        });
        snackbar.show();

    }

}
