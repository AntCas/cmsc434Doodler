package me.castrio.doodler;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

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

//        Button buttonClearScreen = (Button) findViewById(R.id.buttonClearScreen);
//        buttonClearScreen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DoodleView doodleView = (DoodleView) findViewById(R.id)
//            }
//        });
//    }
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

//    public void onClickResetColor(View v) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setMessage("Reset color?")
//                .setTitle("Do you want to reset the color?");
//
//        builder.setPositiveButton()
//
//    }
}
