package com.example.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TableRowLayout extends
    LinearLayoutCompat {
  


  

  public TableRowLayout(
      Context context,
      AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public TableRowLayout(
      Context context,
      AttributeSet attrs) {
    super(context, attrs);
  }

  public TableRowLayout(Context context) {
    super(context);
  }

  // must initialize the column layout
  // setColumns()
  public void inflateColumns(int columnCount, ViewGroup parent) {
    this.removeAllViews();
    for (int i = 0; i < columnCount; i++) {
      View tempCellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
      this.addView(tempCellView);
    }
  }
  
  public void populateColumn(String columnInfo) {
    
  }
}
