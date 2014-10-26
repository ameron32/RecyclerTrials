package com.example.recyclerviewtest;

import com.example.recyclerviewtest.TableAdapter.Columnable;

public class DummyContent {

	public static class RowData implements Columnable<String> {

		private String mName;
		private int mColumnCount;

		public RowData(String name, int columnCount) {
			mName = name;
			mColumnCount = columnCount;
		}

		@Override
		public String get(int columnPosition) {
			return mName + " " + columnPosition;
		}

		@Override
		public int getColumnCount() {
			return mColumnCount;
		}
	}

	public static Columnable<String>[] DUMMY_DATA = new RowData[] {
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5),
			new RowData("second", 5),
			new RowData("third", 5),
			new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5), new RowData("second", 5),
			new RowData("third", 5), new RowData("fourth", 5),
			new RowData("fifth", 5),
			new RowData("sixth", 5),
			//
			new RowData("first", 5), new RowData("second", 5),
			new RowData("third", 5), new RowData("fourth", 5),
			new RowData("fifth", 5), new RowData("sixth", 5) };

}
