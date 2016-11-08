package me.castrio.doodler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

//        // Handle Color button
//        Button buttonSetColor = (Button) findViewById(R.id.roundButtonColor);
//        buttonSetColor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
////                doodleView.setColor(Color.GREEN);
//
//            }
//        });

//        // Handle Size button
//        Button buttonSetSize = (Button) findViewById(R.id.roundButtonSize);
//        buttonSetSize.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
//                doodleView.setSize(5);
//
//            }
//        });

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

    private int _penColorSaved = -1;
    // Credit: Jon Froehlich code shared with UMD CMSC434 class
    public void onClickSetColor(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        _penColorSaved = doodleView.getColor();

        // Get the layout inflater. LayoutInflaters take a layout XML file and create its
        // corresponding View objects. Never create LayoutInflaters directly. Always use the
        // factory method getLayoutInflater. See https://developer.android.com/reference/android/view/LayoutInflater.html
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate the dialog_color.xml layout and create the View
        final View dialogView = inflater.inflate(R.layout.color_dialog, null);
        dialogView.setBackgroundColor(doodleView.getColor());

        // Get access to the seakbar on this dialog.
        SeekBar seekBar = (SeekBar)dialogView.findViewById(R.id.seekBarColorDialog);

        // Set progress bar to current hue value (in other words, when the user first sees
        // this seek bar, it's already set to the hue value of the background)
        float[] hsl = new float[3];
        ColorUtils.colorToHSL(_penColorSaved, hsl);
        int seekBarPosition = (int)((hsl[0] / 360) * (float)seekBar.getMax());
        seekBar.setProgress(seekBarPosition);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
                //hue is value 0 - 360. Saturation and luminance are 0-1.
                float newPenColorHue = (progress / (float)seekBar.getMax()) * 360f;
                doodleView.setColor(ColorUtils.HSLToColor(new float[]{newPenColorHue, 0.5f, 0.5f}));
                dialogView.setBackgroundColor(ColorUtils.HSLToColor(new float[]{newPenColorHue, 0.5f, 0.5f}));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not implemented
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not implemented
            }
        });

        // This is the method that allows us to use our own custom view. We set the AlertDialog builder
        // to the view we created with the inflater above.
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //DoodleView doodleView = (ViewGroup)findViewById(R.id.activity_main);
                        doodleView.setColor(_penColorSaved);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int _penSizeSaved = -1;
    // Credit: Jon Froehlich code shared with UMD CMSC434 class
    public void onClickSetSize(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        _penSizeSaved = doodleView.getSize();

        // Get the layout inflater. LayoutInflaters take a layout XML file and create its
        // corresponding View objects. Never create LayoutInflaters directly. Always use the
        // factory method getLayoutInflater. See https://developer.android.com/reference/android/view/LayoutInflater.html
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate the dialog_color.xml layout and create the View
        final View dialogView = inflater.inflate(R.layout.size_dialog, null);

        // Get access to the seakbar on this dialog.
        SeekBar seekBar = (SeekBar)dialogView.findViewById(R.id.seekBarSizeDialog);

        // Set progress bar to current hue value (in other words, when the user first sees
        // this seek bar, it's already set to the size value of the current path)
        int seekBarPosition = (int)((_penSizeSaved / 50) * (float)seekBar.getMax());
        seekBar.setProgress(seekBarPosition);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
                //hue is value 0 - 360. Saturation and luminance are 0-1.
                int newPenSize = (int)((progress / (float)seekBar.getMax()) * 50f);
                doodleView.setSize(newPenSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not implemented
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not implemented
            }
        });

        // This is the method that allows us to use our own custom view. We set the AlertDialog builder
        // to the view we created with the inflater above.
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //DoodleView doodleView = (ViewGroup)findViewById(R.id.activity_main);
                        doodleView.setSize(_penSizeSaved);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
