package biz.serviscope.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

/**
 * This project code is based on the code developed originally by me for mini-project of
 * Programming Mobile Applications for Android Handheld Systems course Part 1
 * University of Maryland, College Park
 * https://www.coursera.org/account/accomplishments/records/j8xBZZKunZ7HVEAd
 * Submitted for assessment on March 18, 2015
 */
public class MainActivity extends ActionBarActivity {

    // Identifier for type of Dialog
    private static final int MORE_INFO = 1;
    static private final String URL = "http://www.moma.org/collection/browse_results.php?" +
            "criteria=O%3AAD%3AE%3A4057&page_number=13&template_id=1&sort_order=1";

    private static final String TAG = "MainActivity";
    private DialogFragment mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_moreinfo) {

            // Dialog fragment call
            showDialogFragment(MORE_INFO);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Show desired Dialog
    void showDialogFragment(int dialogID) {

        switch (dialogID) {

            // Show More info Dialog
            case MORE_INFO:
                // Create a new MoreInfoDialogFragment
                mDialog = MoreInfoDialogFragment.newInstance();

                // Show MoreInfoDialogFragment
                mDialog.show(getFragmentManager(), "More info");

                break;
        }
    }

    // Class that creates the MoreInfoDialog
    public static class MoreInfoDialogFragment extends DialogFragment {

        public static MoreInfoDialogFragment newInstance() {
            return new MoreInfoDialogFragment();
        }

        // Build MoreInfoDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage("Inspired by the Composition No. II,\n" +
                            "with Red and Blue, of Piet Mondrian \n\n" +
                            "Click below to learn more!")

                    // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)

                    // Set up "Visit MOMA" Button
                    .setNegativeButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {

                                    // Create an intent for viewing a URL
                                    Intent viewIntent = new Intent(Intent.ACTION_VIEW);
                                    viewIntent.setData(Uri.parse(URL));
                                    startActivity(viewIntent);
                                }
                            })

                    // Set up "Not Now" Button
                    .setPositiveButton("Not Now",
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog,
                                                    int id) {

                                    // Dialog dismissing
                                    Toast.makeText(getActivity(), "Thank you!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }).create();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private SeekBar seekBar;

        private FrameLayout frameTopLeft, frameMidLeft, frameBotLeft,
                frameTopRight, frameMidRight, frameBotRight;

        private InsetDrawable bgDrawable1, bgDrawable2,
                bgDrawable3, bgDrawable4, bgDrawable5, bgDrawable6;
        private GradientDrawable bgShape1, bgShape2,
                bgShape3, bgShape4, bgShape5, bgShape6;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Get reference to Seek Bar
            seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);

            // Get references to Frame Layouts
            frameTopLeft = (FrameLayout) rootView.findViewById(R.id.top_left);
            frameMidLeft = (FrameLayout) rootView.findViewById(R.id.mid_left);
            frameBotLeft = (FrameLayout) rootView.findViewById(R.id.bot_left);
            frameTopRight = (FrameLayout) rootView.findViewById(R.id.top_right);
            frameMidRight = (FrameLayout) rootView.findViewById(R.id.mid_right);
            frameBotRight = (FrameLayout) rootView.findViewById(R.id.bot_right);

            // Get references to a background containers
            bgDrawable1 = (InsetDrawable) rootView.
                    findViewById(R.id.top_left).getBackground();
            bgShape1 = (GradientDrawable) bgDrawable1.getDrawable();

            bgDrawable2 = (InsetDrawable) rootView.
                    findViewById(R.id.mid_right).getBackground();
            bgShape2 = (GradientDrawable) bgDrawable2.getDrawable();

            bgDrawable3 = (InsetDrawable) rootView.
                    findViewById(R.id.top_right).getBackground();
            bgShape3 = (GradientDrawable) bgDrawable3.getDrawable();

            bgDrawable4 = (InsetDrawable) rootView.
                    findViewById(R.id.mid_left).getBackground();
            bgShape4 = (GradientDrawable) bgDrawable4.getDrawable();

            bgDrawable5 = (InsetDrawable) rootView.
                    findViewById(R.id.bot_left).getBackground();
            bgShape5 = (GradientDrawable) bgDrawable5.getDrawable();

            bgDrawable6 = (InsetDrawable) rootView.
                    findViewById(R.id.bot_right).getBackground();
            bgShape6 = (GradientDrawable) bgDrawable6.getDrawable();

            seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                int progressChanged;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {

                    progressChanged = progress;
                    //red - 0xffff2e29
                    bgShape1.setColor(0xffff2e29 + progressChanged*2);
                    //blue - 0xff0f2dff
                    bgShape2.setColor(0xff0f2dff + progressChanged*0x1ff);
                    // transitions of gray - 0xffececec
                    bgShape3.setColor(Color.rgb(0xec - progressChanged,
                            0xec - progressChanged, 0xec - progressChanged));
                    bgShape4.setColor(Color.rgb(0xec - progressChanged/2,
                            0xec - progressChanged/2, 0xec - progressChanged/2));
                    bgShape5.setColor(Color.rgb(0xec - progressChanged / 2,
                            0xec - progressChanged / 2, 0xec - progressChanged / 2));
                    bgShape6.setColor(Color.rgb(0xec - progressChanged,
                            0xec - progressChanged, 0xec - progressChanged));

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                        // Add onStart actions
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Toast.makeText(container.getContext(),
                            "Seek bar progress: " + progressChanged,
                            Toast.LENGTH_SHORT).show();
                }
            });

            frameTopLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: invoke intent to launch Spotify Stramer App

                    // Display toast with message
                    Toast.makeText(container.getContext(),
                            "Such click will launch Spotify Stramer App",
                            Toast.LENGTH_SHORT).show();
                }
            });

            frameTopRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: invoke intent to launch Scores App

                    // Display toast with message
                    Toast.makeText(container.getContext(),
                            "Such click will launch Scores App",
                            Toast.LENGTH_SHORT).show();
                }
            });

            frameMidLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: invoke intent to launch Build it bigger App

                    // Display toast with message
                    Toast.makeText(container.getContext(),
                            "Such click will launch Build it bigger App",
                            Toast.LENGTH_SHORT).show();
                }
            });

            frameMidRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: invoke intent to launch Library App

                    // Display toast with message
                    Toast.makeText(container.getContext(),
                            "Such click will launch Library App",
                            Toast.LENGTH_SHORT).show();
                }
            });

            frameBotLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: invoke intent to launch Capstone project App

                    // Display toast with message
                    Toast.makeText(container.getContext(),
                            "Such click will launch My Capstone App",
                            Toast.LENGTH_SHORT).show();
                }
            });

            frameBotRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: invoke intent to launch XYZ Reader App

                    // Display toast with message
                    Toast.makeText(container.getContext(),
                            "Such click will launch XYZ Reader App",
                            Toast.LENGTH_SHORT).show();
                }
            });

            return rootView;
        }
    }
}
