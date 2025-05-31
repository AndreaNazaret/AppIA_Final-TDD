package es.ulpgc.eite.da.advmasterdetail.favorites;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.da.advmasterdetail.R;
import es.ulpgc.eite.da.advmasterdetail.data.FavoriteItem;
import es.ulpgc.eite.da.advmasterdetail.data.ProductItem;


public class FavoritesAdapter
    extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

  private List<ProductItem> itemList;
  private final View.OnClickListener clickListener;


  public FavoritesAdapter(View.OnClickListener listener) {

    itemList = new ArrayList<>();
    clickListener = listener;
  }


  public void addItem(ProductItem item){
    itemList.add(item);
    notifyDataSetChanged();
  }

  public void addItems(List<ProductItem> items){
    itemList.addAll(items);
    notifyDataSetChanged();
  }

  public void setItems(List<ProductItem> items){
    Log.e("FavoritesAdapter", "setItems() llamado. Tamaño: " + items.size());
    itemList = items;
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.tool_list_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    ProductItem item = itemList.get(position);
    holder.itemView.setTag(item);
    holder.itemView.setOnClickListener(clickListener);

    holder.nameView.setText(item.name);
    holder.developerView.setText(item.developer);

    int resId = holder.itemView.getContext().getResources()
            .getIdentifier(item.imageName, "drawable", holder.itemView.getContext().getPackageName());

    Glide.with(holder.itemView.getContext())
            .load(resId != 0 ? resId : R.drawable.default_category)
            .into(holder.imageView);

  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final ImageView imageView;
    final TextView nameView;
    final TextView developerView;


    ViewHolder(View view) {
      super(view);
      imageView = view.findViewById(R.id.product_logo);
      nameView= view.findViewById(R.id.product_name);
      developerView= view.findViewById(R.id.product_developer);


    }
  }
}
