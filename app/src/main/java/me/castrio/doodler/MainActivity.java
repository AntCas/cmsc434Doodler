package me.castrio.doodler;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Handle the Clear screen button
        Button buttonClearScreen = (Button)findViewById(R.id.buttonClearScreen);
        buttonClearScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
                doodleView.clearCanvas();

            }
        });

        // Handle Color button
        Button buttonSetColor = (Button) findViewById(R.id.roundButtonColor);
        buttonSetColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
                doodleView.setColor(Color.GREEN);

            }
        });

        // Handle Size button
        Button buttonSetSize = (Button) findViewById(R.id.roundButtonSize);
        buttonSetSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
                doodleView.setSize(5);

            }
        });

        // Handle Opacity button
        Button buttonSetOpacity = (Button) findViewById(R.id.roundButtonOpacity);
        buttonSetOpacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
                doodleView.setOpacity(150);
            }
        });

        SeekBar newSeekBar = (SeekBar) findViewById(R.id.seekBarNew);
        newSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { // finger on

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { // finger off
//                Context context = getApplicationContext();
//                CharSequence text = "The seekbar value is " + seekBar.getProgress();
//                Toast.


            }
        });
    }

}
