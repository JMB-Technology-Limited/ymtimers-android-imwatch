package uk.co.jarofgreen.ymtimers.ui;

import java.util.ArrayList;

import uk.co.jarofgreen.ymtimers.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 
 * @link https://github.com/imspa/NumPad
 * @author i'm Spa
 * @author James Baster <jamesbaster.co.uk>
 * @copyright JMB Technology 2013
 * @copyright i'm Spa 2013
 * @license 3-clause BSD
 *
 */
public class EnterNumberActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_number);
		


        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);

        ImageButton btnCanc = (ImageButton) findViewById(R.id.btnCanc);

        Button btnZero = (Button) findViewById(R.id.btnZero);

        ImageButton btnCall = (ImageButton) findViewById(R.id.btnCall);

        numberView = (TextView) findViewById(R.id.numbernumber);

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('1'); }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('2'); }
        });
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('3'); }
        });

        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('4'); }
        });
        btn5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('5'); }
        });
        btn6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('6'); }
        });

        btn7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('7'); }
        });

        btn8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('8'); }
        });

        btn9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { pushToNumber('9'); }
        });

        btnCanc.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {  popFromNumber(); }
                  });

        btnZero.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {pushToNumber('0'); }
        });

        btnCall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
            	Intent resultIntent = new Intent();
				try {
					resultIntent.putExtra("number", Integer.parseInt(numberToString()));
				} catch (NumberFormatException e) {
					resultIntent.putExtra("number", 0);
				}
				setResult(Activity.RESULT_OK, resultIntent);
				finish();
            	
            	
            }
        });
		
		
		
	}
	
	
	

    /** The max number of numbers/symbols you can write in the numpad. */
    public static final int MAX_NUMBER_SIZE = 3;

    final ArrayList<Character> mNumber = new ArrayList<Character>();
    TextView numberView;




    protected void pushToNumber(char c) {
        if (mNumber.size() < MAX_NUMBER_SIZE) {
            mNumber.add(c);
            numberView.setText(numberToString());
        }
    }

    protected void popFromNumber() {
        if (!mNumber.isEmpty()) {
            mNumber.remove(mNumber.size() - 1);
            numberView.setText(numberToString());
        }
    }

    protected String numberToString() {
        StringBuilder builder = new StringBuilder(mNumber.size());
        for (Character ch : mNumber) {
            builder.append(ch);
        }
        return builder.toString();
    }
	
	
	
	
	
	
	
	
	
}


