package com.algonquincollege.razp0002.guessanumber;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String ABOUT_DIALOG_TAG;

    static {
        ABOUT_DIALOG_TAG = "About";
    }

    public int randNumber = randomNum();
    public int guessCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println(randNumber);

        Button guessButton = (Button) findViewById(R.id.buttonGuess);

        guessButton.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);

                guessCount++;

                int guessNumber = Integer.parseInt(editText.getText().toString());

                if (guessCount < 11) {
                    if (guessNumber == randNumber) {
                        if (guessCount <= 5) {
                            Toast.makeText(getApplicationContext(), "Superior Win!!!", Toast.LENGTH_SHORT).show();
                        } else if (guessCount <= 10) {
                            Toast.makeText(getApplicationContext(), "Excellent Win!!!", Toast.LENGTH_SHORT).show();
                        }

                    } else if (guessNumber >= randNumber) {
                        Toast.makeText(getApplicationContext(), "Too high!", Toast.LENGTH_SHORT).show();
                    } else if (guessNumber <= randNumber) {
                        Toast.makeText(getApplicationContext(), "Too low!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "You lose, please reset", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button resetButton = (Button) findViewById(R.id.buttonReset);

        resetButton.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v){
                randNumber = randomNum();
                Toast.makeText( getApplicationContext( ), "Number has been reset", Toast.LENGTH_SHORT ).show( );

                guessCount = 0;
            }
        });

        resetButton.setOnLongClickListener( new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                Toast.makeText( getApplicationContext( ), String.valueOf(randNumber), Toast.LENGTH_SHORT ).show( );
                return true;
            }
        });

    }

    public int randomNum() {

        Random rand = new Random();
        int ranNum = rand.nextInt(1000) + 1;

        return ranNum;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
