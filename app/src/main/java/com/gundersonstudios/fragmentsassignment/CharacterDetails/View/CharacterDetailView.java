package com.gundersonstudios.fragmentsassignment.CharacterDetails.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;

import com.gundersonstudios.fragmentsassignment.CharacterDetails.Fragments.CharacterDetailViewFragment;
import com.gundersonstudios.fragmentsassignment.CharacterDetails.Fragments.CharacterQuoteListViewFragment;
import com.gundersonstudios.fragmentsassignment.CharacterDetails.Model.CharacterDetailModel;
import com.gundersonstudios.fragmentsassignment.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterDetailView extends AppCompatActivity implements CharacterDetailViewFragment.characterDetailViewListener,
                                                                      CharacterQuoteListViewFragment.characterQuoteListListener {

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

    private  ArrayList<CharacterDetailModel> characterList;

    private CharacterQuoteListViewFragment quoteFrag = null;

    private List<String> quoteList;

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

        quoteList = new ArrayList<>();
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

    @Override
    public void textChanged(Editable editable, int position) {
        String newQuoteText = editable.toString();
        quoteList.set(position - 1, newQuoteText);
        if (quoteFrag != null){
            quoteFrag.updateQuoteList(quoteList);
        }
    }

    @Override
    public void setQuotesForListAtStartup(String quote, int position) {
        if(quoteList.size() <= 2){
            String newQuoteText = quote;
            quoteList.add(newQuoteText);
        }
    }

    @Override
    public void quoteFragCreated() {
        quoteFrag.updateQuoteList(quoteList);
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
            if(position < 3) {
                CharacterDetailViewFragment newFragment = CharacterDetailViewFragment.newInstance(position + 1, characterList.get(position));
                return newFragment;
            }
            else {
                quoteFrag = CharacterQuoteListViewFragment.newInstance(position + 1);
                return quoteFrag;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
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