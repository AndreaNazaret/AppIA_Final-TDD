package es.ulpgc.eite.da.advmasterdetail.categories;

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
import es.ulpgc.eite.da.advmasterdetail.data.CategoryItem;


public class CategoryListAdapter
    extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

  private List<CategoryItem> itemList;
  private final View.OnClickListener clickListener;


  public CategoryListAdapter(View.OnClickListener listener) {

    itemList = new ArrayList<>(); //Necesita los datos
    clickListener = listener; //Listener para cuando se hace click en un elemento
  }

  public void addItem(CategoryItem item){
    itemList.add(item);
    notifyDataSetChanged();
  }

  public void addItems(List<CategoryItem> items){
    itemList.addAll(items);
    notifyDataSetChanged();
  }

  //Actualiza con los nuevos datos
  public void setItems(List<CategoryItem> items){
    itemList = items;
    notifyDataSetChanged();
  }


  @Override
  public int getItemCount() {
    return itemList.size();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.category_card_item, parent, false);
    return new ViewHolder(view);
  }

  //Cada vez que se cree una celda asociale un "tag" (etiqueta) para identificarla unicamente

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    CategoryItem item = itemList.get(position);
    holder.itemView.setTag(item);
    holder.itemView.setOnClickListener(clickListener);

    holder.titleView.setText(item.title);
    holder.descriptionView.setText(item.description);
    holder.detailsView.setText(item.details);

    Glide.with(holder.itemView.getContext())
            .load(item.imageResId)
            .into(holder.imageView);

  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final ImageView imageView;
    final TextView titleView;
    final TextView descriptionView;
    final TextView detailsView;

    ViewHolder(View view) {
      super(view);
      imageView = view.findViewById(R.id.category_image);
      titleView = view.findViewById(R.id.category_title);
      descriptionView = view.findViewById(R.id.category_description);
      detailsView = view.findViewById(R.id.category_details);
    }
  }
}
