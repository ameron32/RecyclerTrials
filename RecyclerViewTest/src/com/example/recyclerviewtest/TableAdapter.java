package com.example.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TableAdapter
    extends
    RecyclerView.Adapter<TableAdapter.ViewHolder> {
  private Columnable<String>[] mDataset; // TODO: make dataset generic
  private int mCellLayoutResource;
  
  // Provide a reference to the views for each data item
  // Complex data items may need more than one view per item, and
  // you provide access to all the views for a data item in a view holder
  public static class ViewHolder extends
      RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public View mView; // TODO: replace with View#Row
    
    public ViewHolder(View v) {
      super(v);
      mView = v;
    }
  }
  
  // Provide a suitable constructor (depends on the kind of dataset)
  public TableAdapter(
      Columnable<String>[] myDataset,
      int cellLayoutResource) {
    mDataset = myDataset;
    mCellLayoutResource = cellLayoutResource;
  }
  
  // Create new views (invoked by the layout manager)
  @Override public TableAdapter.ViewHolder onCreateViewHolder(
      ViewGroup parent, int viewType) {
    /**
     * CREATE A NEW ROW
     */
    // create a new view
    TableRowLayout trl = (TableRowLayout) LayoutInflater.from(parent.getContext()).inflate(mCellLayoutResource, parent, false);
    // set the view's size, margins, paddings and layout parameters
    
    /**
     * PUT COLUMNS INTO NEW ROW
     */
    int columnCount = mDataset[0].getColumnCount();
    trl.inflateColumns(columnCount, parent);
    
    TableAdapter.ViewHolder vh = new ViewHolder(trl);
    return vh;
  }
  
  // Replace the contents of a view (invoked by the layout manager)
  @Override public void onBindViewHolder(
      TableAdapter.ViewHolder holder,
      int position) {
    /**
     * REPLACE CONTENTS OF VIEW WITH NEW CONTENTS
     */
    // - get element from your dataset at this position
    // - replace the contents of the view with that element
    int columnPosition = 0; // TODO: implement
    holder.mView.setText(mDataset[position].get(columnPosition));
    
    /**
     * POPULATE INFORMATION INTO COLUMNS
     */
    
  }
  
  
  
  // Return the size of your dataset (invoked by the layout manager)
  @Override public int getItemCount() {
    /**
     * GET NUMBER OF ROWS
     */
    return mDataset.length;
  }
  
  
  
  
  
  public static class Column {
    public interface ColumnInfo {
      public int getDataTypeCount();
      
      public Object getDataType(
          int position);
    }
    
    public enum DataType
        implements ColumnInfo {
      String, Integer, ObjectId, Boolean, Pointer, UserRelation, Relation, Array;
      
      @Override public int getDataTypeCount() {
        return values().length;
      }
      
      @Override public Object getDataType(
          int position) {
        return values()[position];
      }
    }
    
    String title;
    int weight;
    DataType dataType;
    
    
  }
  
  private OnCellClickListener listener;
  
  public void setOnCellClickListener(
      OnCellClickListener listener) {
    this.listener = listener;
  }
  
  public OnCellClickListener getOnCellClickListener() {
    return listener;
  }
  
  public interface OnCellClickListener {
    
    public void onCellClick(
        ViewGroup parentRowLayout,
        View cellView,
        int columnPosition,
        Object object);
  }
  
  public interface Columnable<T> {
    public T get(int columnPosition);
    public int getColumnCount();
  }
}