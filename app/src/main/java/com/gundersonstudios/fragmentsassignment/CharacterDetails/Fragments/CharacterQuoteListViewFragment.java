package com.gundersonstudios.fragmentsassignment.CharacterDetails.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gundersonstudios.fragmentsassignment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 2/28/2017.
 */

public class CharacterQuoteListViewFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    private List<String> quoteList = new ArrayList<>();
    public ListView listView;

    characterQuoteListListener mCallback;


    public CharacterQuoteListViewFragment() {
    }

    public interface characterQuoteListListener {
        void quoteFragCreated();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (characterQuoteListListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement characterDetailViewListener");
        }
    }

    public static CharacterQuoteListViewFragment newInstance(int position) {
        CharacterQuoteListViewFragment fragment = new CharacterQuoteListViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_character_quote_list_view, container, false);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.character_quote_list_item, quoteList);
        listView = (ListView) rootView.findViewById(R.id.character_quote_list);
        listView.setAdapter(adapter);
        mCallback.quoteFragCreated();
        return rootView;
    }

    public void updateQuoteList(List<String> quoteList){
        this.quoteList = quoteList;
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.character_quote_list_item, quoteList);
        listView.setAdapter(adapter);
    }
}
