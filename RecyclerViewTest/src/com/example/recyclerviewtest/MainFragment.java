package com.example.recyclerviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.recyclerviewtest.DummyContent.ObjectPlus;
import com.example.recyclerviewtest.TableAdapter.Columnable;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static MainFragment newInstance(int sectionNumber) {
		MainFragment fragment = new MainFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public MainFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		return rootView;
	}

	private static final Columnable<ObjectPlus>[] DATA_SET = DummyContent.DUMMY_DATA;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;
	private TableAdapter mAdapter;
	private EditText mEditor;
	private ImageButton mButton;
	
	private int mRow, mColumn;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mEditor = (EditText) getActivity().findViewById(R.id.my_recycler_editor);
		mRecyclerView = (RecyclerView) getActivity().findViewById(
				R.id.my_recycler_view);
		
		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(mLayoutManager);

		// specify an adapter (see also next example)
		mAdapter = new TableAdapter<ObjectPlus>(DATA_SET, R.layout.my_table_row_layout,
				R.id.textview);
		mRecyclerView.setAdapter(mAdapter);

		mRecyclerView.addOnItemTouchListener(new RecyclerCellClickListener(
				getActivity(),
				new RecyclerCellClickListener.OnCellClickListener() {

					@Override
					public void onCellClick(View view, int rowPosition,
							int columnPosition) {
						mRow = rowPosition;
						mColumn = columnPosition;
						
						TextView tv = (TextView) view;
						String str = String.valueOf(tv.getText());
						mEditor.setText(str);
					}
				}
		));
		
		mButton = (ImageButton) getActivity().findViewById(R.id.my_recycler_editor_button);
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ObjectPlus itemAt = (ObjectPlus) mAdapter.getItemAt(mRow, mColumn);
				String text = mEditor.getText().toString();
				itemAt.setText(text);
				mAdapter.notifyItemChanged(mRow);
			}
		});
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

//	public static class RecyclerItemClickListener 
//		implements RecyclerView.OnItemTouchListener {
//		
//		private RecyclerItemClickListener.OnItemClickListener mListener;
//
//		public interface OnItemClickListener {
//			public void onItemClick(View view, int position);
//		}
//
//		GestureDetector mGestureDetector;
//
//		public RecyclerItemClickListener(Context context,
//				RecyclerItemClickListener.OnItemClickListener listener) {
//			mListener = listener;
//			SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
//				@Override
//				public boolean onSingleTapUp(MotionEvent e) {
//					return true;
//				}
//			};
//			mGestureDetector = new GestureDetector(context, gestureListener);
//		}
//
//		@Override
//		public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
//			View childView = view.findChildViewUnder(e.getX(), e.getY());
//			if (childView != null && mListener != null
//					&& mGestureDetector.onTouchEvent(e)) {
//				mListener.onItemClick(childView,
//						view.getChildPosition(childView));
//			}
//			return false;
//		}
//
//		@Override
//		public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {}
//
//	}

}