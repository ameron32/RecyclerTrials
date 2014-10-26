package com.example.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TableRowLayout extends LinearLayoutCompat {

	String TAG = TableRowLayout.class.getSimpleName();

	public TableRowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public TableRowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TableRowLayout(Context context) {
		super(context);
	}

	// must initialize the column layout
	// setColumns()
	public void inflateColumns(int columnCount, ViewGroup parent) {
		Log.d(TAG, "inflate new row");
		this.removeAllViews();

		// determine column weightsum
		float totalWeight = columnCount; // TODO: replace with calculated
											// weightsum
		this.setWeightSum(totalWeight);

		for (int i = 0; i < columnCount; i++) {
			float columnWeight = 1.0f; // TODO: replace with calculated column
										// weight
			View tempCellView = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.my_text_view, parent, false);
			tempCellView.setLayoutParams(new TableRowLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
					columnWeight));
			this.addView(tempCellView);
		}
	}

	public void populateColumnTextView(int column, String columnInfo,
			int textViewResourceId) {
		Log.d(TAG, "populate column");
		View tempCellView = this.getChildAt(column);
		TextView textView = (TextView) tempCellView
				.findViewById(textViewResourceId);
		textView.setText(columnInfo);
	}
}
