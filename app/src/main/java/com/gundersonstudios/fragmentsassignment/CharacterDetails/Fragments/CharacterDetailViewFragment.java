package com.gundersonstudios.fragmentsassignment.CharacterDetails.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gundersonstudios.fragmentsassignment.CharacterDetails.Model.CharacterDetailModel;
import com.gundersonstudios.fragmentsassignment.R;

/**
 * Created by Erik on 2/28/2017.
 */

public class CharacterDetailViewFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_CHARACTER_NAME = "character_name";
    private static final String ARG_CHARACTER_DESCRIPTION = "character_description";
    private static final String ARG_CHARACTER_QUOTE = "character_quote";
    private static final String ARG_IMAGE_RESOURCE_STRING = "image_resource_string";
    private static final String ARG_MEDIA_NAME = "media_name";

    public String oldText = "";
    public String currentQuoteText = "";

    characterDetailViewListener mCallback;

    public CharacterDetailViewFragment(){
    }

    public interface characterDetailViewListener {
        void textChanged(Editable editable, int position);
        void setQuotesForListAtStartup(String quote, int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (characterDetailViewListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement characterDetailViewListener");
        }
    }

    public static CharacterDetailViewFragment newInstance(int position, CharacterDetailModel model){
        CharacterDetailViewFragment fragment = new CharacterDetailViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        args.putString(ARG_CHARACTER_NAME, model.getCharacterName());
        args.putString(ARG_CHARACTER_DESCRIPTION, model.getDescription());
        args.putString(ARG_CHARACTER_QUOTE, model.getQuote());
        args.putString(ARG_IMAGE_RESOURCE_STRING, model.getResourceString());
        args.putString(ARG_MEDIA_NAME, model.getMediaName());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_character_detail_view, container, false);
        setFieldsOfFragment(rootView);
        mCallback.setQuotesForListAtStartup(currentQuoteText, getArguments().getInt(ARG_SECTION_NUMBER));
        return rootView;
    }

    void setFieldsOfFragment(View rootView){
        TextView characterName = (TextView) rootView.findViewById(R.id.characterName);
        characterName.setText(getArguments().getString(ARG_CHARACTER_NAME));

        ImageView characterImage = (ImageView) rootView.findViewById(R.id.characterImage);
        characterImage.setImageResource(getResources().getIdentifier(getArguments().getString(ARG_IMAGE_RESOURCE_STRING), "drawable", getActivity().getPackageName()));

        TextView mediaName = (TextView) rootView.findViewById(R.id.mediaName);
        mediaName.setText(getArguments().getString(ARG_MEDIA_NAME));

        EditText characterQuote = (EditText) rootView.findViewById(R.id.characterQuote);
        characterQuote.setText(getArguments().getString(ARG_CHARACTER_QUOTE));
        currentQuoteText = getArguments().getString(ARG_CHARACTER_QUOTE);
        characterQuote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                oldText = charSequence.toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mCallback.textChanged(editable, getArguments().getInt(ARG_SECTION_NUMBER));
            }
        });

        TextView characterDescription = (TextView) rootView.findViewById(R.id.characterDescription);
        characterDescription.setText(getArguments().getString(ARG_CHARACTER_DESCRIPTION));
    }
}
