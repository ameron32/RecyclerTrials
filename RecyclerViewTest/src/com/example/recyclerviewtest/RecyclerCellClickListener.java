package com.example.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;

public class RecyclerCellClickListener implements
		RecyclerView.OnItemTouchListener {

	private RecyclerCellClickListener.OnCellClickListener mListener;

	public interface OnCellClickListener {
		public void onCellClick(View cellView, int rowPosition,
				int columnPosition);
	}

	private final GestureDetector mGestureDetector;

	public RecyclerCellClickListener(Context context,
			RecyclerCellClickListener.OnCellClickListener listener) {
		mListener = listener;
		SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return true;
			}
		};
		mGestureDetector = new GestureDetector(context, gestureListener);
	}

	@Override
	public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
		View childView = view.findChildViewUnder(e.getX(), e.getY());
		if (childView != null 
				&& mListener != null
				&& mGestureDetector.onTouchEvent(e)) {
			TableRowLayout rowView = (TableRowLayout) childView;

			int rowPosition = view.getChildPosition(childView);
			int columnPosition = rowView.findCellPositionUnder(view, e.getX(),
					e.getY());
			View cellView = rowView.getChildAt(columnPosition);

			if (cellView != null) {
				mListener.onCellClick(cellView, rowPosition, columnPosition);
			}
		}
		return false;
	}

	@Override
	public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
	}

}