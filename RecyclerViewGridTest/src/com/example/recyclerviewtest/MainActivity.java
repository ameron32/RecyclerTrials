package com.example.recyclerviewtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends
    ActionBarActivity
    implements
    NavigationDrawerFragment.NavigationDrawerCallbacks {
  
  /**
   * Fragment managing the behaviors, interactions and presentation of the
   * navigation drawer.
   */
  private NavigationDrawerFragment mNavigationDrawerFragment;
  
  /**
   * Used to store the last screen title. For use in {@link #restoreActionBar()}
   * .
   */
  private CharSequence mTitle;
  
  @Override protected void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    mTitle = getTitle();
    
    // Set up the drawer.
    mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
  }
  
  @Override public void onNavigationDrawerItemSelected(
      int position) {
    // update the main content by replacing fragments
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
  }
  
  public void onSectionAttached(
      int number) {
    switch (number) {
    case 1:
      mTitle = getString(R.string.title_section1);
      break;
    case 2:
      mTitle = getString(R.string.title_section2);
      break;
    case 3:
      mTitle = getString(R.string.title_section3);
      break;
    }
  }
  
  public void restoreActionBar() {
    ActionBar actionBar = getSupportActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    actionBar.setDisplayShowTitleEnabled(true);
    actionBar.setTitle(mTitle);
  }
  
  
  @Override public boolean onCreateOptionsMenu(
      Menu menu) {
    if (!mNavigationDrawerFragment.isDrawerOpen()) {
      // Only show items in the action bar relevant to this screen
      // if the drawer is not showing. Otherwise, let the drawer
      // decide what to show in the action bar.
      getMenuInflater().inflate(R.menu.main, menu);
      restoreActionBar();
      return true;
    }
    return super.onCreateOptionsMenu(menu);
  }
  
  @Override public boolean onOptionsItemSelected(
      MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) { return true; }
    return super.onOptionsItemSelected(item);
  }
  
  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment
      extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(
        int sectionNumber) {
      PlaceholderFragment fragment = new PlaceholderFragment();
      Bundle args = new Bundle();
      args.putInt(ARG_SECTION_NUMBER, sectionNumber);
      fragment.setArguments(args);
      return fragment;
    }
    
    public PlaceholderFragment() {}
    
    @Override public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
    
    
    
    private static final String[] DATA_SET = {
        "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15"
         };
    private static final int SPAN_COUNT = 4;
    private RecyclerView mRecyclerView;
    private LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    
    @Override public void onViewCreated(
        View view,
        Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
      
      // use this setting to improve performance if you know that changes
      // in content do not change the layout size of the RecyclerView
      mRecyclerView.setHasFixedSize(true);
      
      // use a linear layout manager
//      mLayoutManager = new LinearLayoutManager(getActivity());
      mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
      
      mRecyclerView.setLayoutManager(mLayoutManager);
      
      // specify an adapter (see also next example)
      mAdapter = new MyAdapter(DATA_SET);
      mRecyclerView.setAdapter(mAdapter);
      
      mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
        
        @Override public void onItemClick(
            View view, int position) {
          Toast.makeText(getActivity(), "position: "
              + position, Toast.LENGTH_SHORT).show();
        }
      }));
    }
    
    @Override public void onAttach(
        Activity activity) {
      super.onAttach(activity);
      ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
    
    public static class MyAdapter
        extends
        RecyclerView.Adapter<MyAdapter.ViewHolder> {
      private String[] mDataset;
      
      // Provide a reference to the views for each data item
      // Complex data items may need more than one view per item, and
      // you provide access to all the views for a data item in a view holder
      public static class ViewHolder
          extends
          RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        
        public ViewHolder(TextView v) {
          super(v);
          mTextView = v;
        }
      }
      
      // Provide a suitable constructor (depends on the kind of dataset)
      public MyAdapter(
          String[] myDataset) {
        mDataset = myDataset;
      }
      
      // Create new views (invoked by the layout manager)
      @Override public MyAdapter.ViewHolder onCreateViewHolder(
          ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        
        TextView tv = (TextView) v;
        
        ViewHolder vh = new ViewHolder(tv);
        return vh;
      }
      
      // Replace the contents of a view (invoked by the layout manager)
      @Override public void onBindViewHolder(
          ViewHolder holder,
          int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);
      }
      
      // Return the size of your dataset (invoked by the layout manager)
      @Override public int getItemCount() {
        return mDataset.length;
      }
      
      
    }
    
    public static class RecyclerItemClickListener
        implements
        RecyclerView.OnItemTouchListener {
      private OnItemClickListener mListener;
      
      public interface OnItemClickListener {
        public void onItemClick(
            View view, int position);
      }
      
      GestureDetector mGestureDetector;
      
      public RecyclerItemClickListener(
          Context context,
          OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
          @Override public boolean onSingleTapUp(
              MotionEvent e) {
            return true;
          }
        });
      }
      
      @Override public boolean onInterceptTouchEvent(
          RecyclerView view,
          MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null
            && mListener != null
            && mGestureDetector.onTouchEvent(e)) {
          mListener.onItemClick(childView, view.getChildPosition(childView));
        }
        return false;
      }
      
      @Override public void onTouchEvent(
          RecyclerView view,
          MotionEvent motionEvent) {}
      
    }
  }
  
}
