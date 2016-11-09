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
import android.widget.ToggleButton;

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
    }

    private int _penColorSaved = -1;
    // Credit: Based on code Jon Froehlich shared with his UMD CMSC434 class
    public void onClickSetColor(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        _penColorSaved = doodleView.getColor();

        // Inflate color_dialog
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.color_dialog, null);
        dialogView.setBackgroundColor(doodleView.getColor());
        SeekBar seekBar = (SeekBar)dialogView.findViewById(R.id.seekBarColorDialog);

        // Set progress bar to current hue value
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

        // Use custom view
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        doodleView.setColor(_penColorSaved);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int _penSizeSaved = -1;
    // Credit: Based on code Jon Froehlich shared with his UMD CMSC434 class
    public void onClickSetSize(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        _penSizeSaved = doodleView.getSize();

        // Inflate  size_dialog
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.size_dialog, null);
        SeekBar seekBar = (SeekBar)dialogView.findViewById(R.id.seekBarSizeDialog);

        // Set progress bar to current size value
        int seekBarPosition = (int)((_penSizeSaved / 50f) * (float)seekBar.getMax());
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

        // Use custom view
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        doodleView.setSize(_penSizeSaved);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int _penOpacitySaved = -1;
    // Credit: Based on code Jon Froehlich shared with his UMD CMSC434 class
    public void onClickSetOpacity(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        _penOpacitySaved = doodleView.getOpacity();

        // Inflate opacity_dialog
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.opacity_dialog, null);
        SeekBar seekBar = (SeekBar)dialogView.findViewById(R.id.seekBarOpacityDialog);

        // Set progress bar to current opacity value
        int seekBarPosition = (int)((_penOpacitySaved / 255f) * (float)seekBar.getMax());
        seekBar.setProgress(seekBarPosition);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
                //hue is value 0 - 360. Saturation and luminance are 0-1.
                int newPenOpacity = (int)((progress / (float)seekBar.getMax()) * 255f);
                doodleView.setOpacity(newPenOpacity);
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

        // Create custom view
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        doodleView.setColor(_penOpacitySaved);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onClickSetMirror(View v) {
        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleView);
        final ToggleButton toggleButtonMirror = (ToggleButton) findViewById(R.id.toggleButtonMirror);
        if (doodleView.getMirrorOn()) {
            doodleView.setMirrorOn(false);
        } else {
            doodleView.setMirrorOn(true);
        }
    }



}
