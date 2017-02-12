package com.gundersonstudios.fragmentsassignment.CharacterDetails.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gundersonstudios.fragmentsassignment.CharacterDetails.Model.CharacterDetailModel;
import com.gundersonstudios.fragmentsassignment.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterDetailView extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static String packageName;

    private static ArrayList<CharacterDetailModel> characterList;

    private List<CharacterDetailViewFragment> characterFragmentList = new ArrayList<CharacterDetailViewFragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail_view);
        packageName = getPackageName();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        characterList = new ArrayList<>();

        characterList.add(new CharacterDetailModel("Pink Guy",
                "Eyb0ss.",
                "Filthy Frank - Youtube",
                "Pink Guy is a man in a pink suit who follows his friend Filthy Frank on wacky adventures.",
                "eyb0ss"));
        characterList.add(new CharacterDetailModel("Steven Universe",
                "Hold the phone, now give the phone to me.",
                "Steven Universe - TV Show",
                "Steven Universe is the first human gem hybrid in a world of magical rock based aliens invading Earth.",
                "steven_universe"));
        characterList.add(new CharacterDetailModel("Cthulhu",
                "Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn.",
                "The Call of Cthulhu - Short Story",
                "Cthulhu is a cosmic deity of evil and insanity, also known as the great dreamer, as his dreams travel the cosmos driving all who see them insane.",
                "cthulhu"));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_character_detail_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        public PlaceholderFragment() {
//        }
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_character_detail_view, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//    }

    public static class CharacterDetailViewFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_CHARACTER_NAME = "character_name";
        private static final String ARG_CHARACTER_DESCRIPTION = "character_description";
        private static final String ARG_CHARACTER_QUOTE = "character_quote";
        private static final String ARG_IMAGE_RESOURCE_STRING = "image_resource_string";
        private static final String ARG_MEDIA_NAME = "media_name";

        public String oldText = "";
        public String currentQuoteText = "";
        public String newQuoteText = "";

        public CharacterDetailViewFragment(){
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
            return rootView;
        }

        void setFieldsOfFragment(View rootView){
            TextView characterName = (TextView) rootView.findViewById(R.id.characterName);
            characterName.setText(getArguments().getString(ARG_CHARACTER_NAME));

            ImageView characterImage = (ImageView) rootView.findViewById(R.id.characterImage);
            characterImage.setImageResource(getResources().getIdentifier(getArguments().getString(ARG_IMAGE_RESOURCE_STRING), "drawable", packageName));

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
                    newQuoteText = editable.toString();
                }
            });

            TextView characterDescription = (TextView) rootView.findViewById(R.id.characterDescription);
            characterDescription.setText(getArguments().getString(ARG_CHARACTER_DESCRIPTION));
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            CharacterDetailViewFragment newFragment = CharacterDetailViewFragment.newInstance(position + 1, characterList.get(position));
            characterFragmentList.add(newFragment);
            return newFragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CharacterDetailModel model = characterList.get(position);
            if(model != null) {
                return model.getCharacterName();
            }
            return null;
        }
    }
}
