package com.example1.test4;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends Activity {
	public static final int SIZE = 9;
    int[][] matrix = new int[SIZE][SIZE];

	GridView gridView;
	Button resetButton;
	Button solveButton;
	 
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		setContentView(R.layout.activity_main);
 
		load();
		
		resetButton = (Button) findViewById(R.id.button1);
		resetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				reset();
			}
		});

		solveButton = (Button) findViewById(R.id.button2);
		solveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				solve(matrix, 0, 0);
				gridView = (GridView) findViewById(R.id.gridView1);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
						R.layout.list_item, convert());
		 
				gridView.setAdapter(adapter);
				
			}
		});

		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setSelector(R.drawable.myselector);
//		gridView.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View v,
//				int position, long id) {
//				EditText editText = (EditText) v;
//				editText.setBackgroundColor(Color.CYAN);
//			}
//		});
	}


	private void load() {
	    matrix[0][0] = 5;
	    matrix[0][1] = 3;
	    matrix[1][4] = 8;
	    matrix[3][5] = 9;
	    matrix[8][8] = 1;
		
		gridView = (GridView) findViewById(R.id.gridView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, convert());
 
		gridView.setAdapter(adapter);
	}


	private void reset() {
		gridView = (GridView) findViewById(R.id.gridView1);

		String[] strArr = new String[81];
		for (int i = 0; i < 81; i++) {
			strArr[i] = new String("");
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, strArr);
 
		gridView.setAdapter(adapter);
	}


	private String[] convert() {
		String[] strNumbers = new String[81];
		int count = 0;
		for (int i = 0; i <9; i++) {
			for (int j= 0; j < 9; j++) {
				strNumbers[count++] = matrix[i][j] + "";
			}
		}
		return strNumbers;
	}


	  private static boolean solve(int[][] matrix, int row, int col) {
		    //System.out.println(row + "," + col);
		    if (col == SIZE) {
		      col = 0;
		      row +=1;
		      if (row == 9) {
		        return true;
		      }
		    }
		    if (matrix[row][col] != 0) {
		      return solve(matrix, row, col+1);
		    }
		    for (int num=1; num <= SIZE; num++) {
		      if (legal(matrix, row, col, num)) {
		        matrix[row][col] = num;
		        if (solve(matrix, row, col+1)) {
		          return true;
		        }
		      }
		    }
		    matrix[row][col] = 0;
		    return false;
		  }

		  private static boolean legal(int[][] matrix, int row, int col, int num) {
		    for (int i=0;i< SIZE; i++){
		      if (matrix[row][i] == num) {
		        return false;
		      }
		    }
		    for (int i=0;i< SIZE; i++){
		      if (matrix[i][col] == num) {
		        return false;
		      }
		    }
		    return true;
		  }

}
