package eu.quitzau.logworktime.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link LogTimeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogTimeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button startWorkButton;
    private Button endWorkButton;


    public static Fragment newInstance() {
        LogTimeFragment fragment = new LogTimeFragment();
        return fragment;
    }

    public LogTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_time, container, false);

        startWorkButton = (Button) view.findViewById(R.id.button);
        startWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogTimeDBHelper dbHelper = new LogTimeDBHelper(getActivity().getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                String id = Math.random() + "";
                Date now = new Date();
                String date = new SimpleDateFormat("ddMMyyyy").format(now);
                String startTime = new SimpleDateFormat("HH:mm:ss").format(now);

                values.put(LogTimeContract.LogTimeEntry.COLUMN_NAME_LOG_ID, id);
                values.put(LogTimeContract.LogTimeEntry.COLUMN_NAME_DATE, date);
                values.put(LogTimeContract.LogTimeEntry.COLUMN_NAME_STARTTIME, startTime);
                values.put(LogTimeContract.LogTimeEntry.COLUMN_NAME_ENDTIME, "");

// Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(LogTimeContract.LogTimeEntry.TABLE_NAME, null, values);
                Toast.makeText(getActivity().getApplicationContext(), "inserting: " + newRowId, Toast.LENGTH_SHORT);
            }
        });
        endWorkButton = (Button) view.findViewById(R.id.button2);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction();
    }

}
