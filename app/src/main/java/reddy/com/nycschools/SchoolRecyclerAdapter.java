package reddy.com.nycschools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

/**
 * Created by mreddy on 4/10/2018.
 */

public class SchoolRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<SchoolBean> data = Collections.emptyList();
    private ItemClickListener clickListener;

    // create constructor to initilize context and data sent from MainActivity
    public SchoolRecyclerAdapter(Context context, List<SchoolBean> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }


    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        SchoolBean current = data.get(position);
        myHolder.schoolName.setText(current.getSchoolName());

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView schoolName;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            schoolName = (TextView) itemView.findViewById(R.id.schoolName);

          /*
          * set click listener to the view.
          * */
            schoolName.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }


}
