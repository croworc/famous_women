/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Word> words = new ArrayList<Word>();
    public static final String CHOSEN_WOMAN = "chosen_woman";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // ( ͡° ͜ʖ ͡°)
        // Create a list of words


        words.add(new Word(R.string.maria, R.string.maria_profession, R.string.body_details_descriprion_maria_sklodowska,
                R.drawable.maria_sklodowska_listimg, R.drawable.maria_poland_flag, R.drawable.maria_portrait));
        words.add(new Word(R.string.dalia, R.string.dalia_profession, R.string.body_details_description_dalia,
                R.drawable.dalia_grybauskaite_listimg, R.drawable.dalia_lithuania_flag, R.drawable.dalia_portrait));
        words.add(new Word(R.string.elisabeta, R.string.elisabeta_proffesion, R.string.body_details_description_elisabeta,
                R.drawable.elisabeta_rizea_listimg, R.drawable.elisabeta_rizea_flag, R.drawable.elisabeta_portrait));
        words.add(new Word(R.string.mother_theresa, R.string.mother_theresa_profession, R.string.body_details_description_mother_theresa,
                R.drawable.mother_theresa_listimg, R.drawable.theresa_macedonia_flag, R.drawable.theresa_portrait));
        words.add(new Word(R.string.wanda, R.string.wanda_profession, R.string.body_details_description_wanda,
                R.drawable.wanda_rutkiewicz_listimg, R.drawable.maria_poland_flag, R.drawable.wanda_rutkiewicz_portrait));
        words.add(new Word(R.string.ameenah, R.string.ameenah_profession, R.string.body_details_description_ameenah,
                R.drawable.ameenah_listing, R.drawable.ameenah_mauritius_flag, R.drawable.ameenah_portrait));
        words.add(new Word(R.string.ellen_Sirleaf, R.string.sirleaf_profession, R.string.body_details_description_sirleaf,
                R.drawable.sirleaf_listing, R.drawable.sirleaf_liberia_flag, R.drawable.sirleaf_portrait));
        words.add(new Word(R.string.maria_telkes, R.string.maria_telkes_profession, R.string.body_details_description_maria_telkes,
                R.drawable.maria_telkes_listimg, R.drawable.maria_hungary_flag, R.drawable.maria_telkes_portrait));
        words.add(new Word(R.string.Merieme_Chadid, R.string.meriem_profession, R.string.body_details_description_meriem,
                R.drawable.meriem_listimg, R.drawable.meriem_morocco_flag, R.drawable.meriem_portrait));
        words.add(new Word(R.string.irena, R.string.irena_profession, R.string.body_details_description_irena,
                R.drawable.irena_sendler_listimg, R.drawable.maria_poland_flag, R.drawable.irena_portrait));
        words.add(new Word(R.string.ada, R.string.ada_profession, R.string.body_details_description_irena,
                R.drawable.ada_yonath_listimg, R.drawable.israel_flag, R.drawable.irena_portrait));
        words.add(new Word(R.string.ilhan, R.string.ilhan_profession, R.string.body_details_description_ilhan,
                R.drawable.ilhan_listing, R.drawable.ilhan_flag, R.drawable.ilhan_portrait));
        words.add(new Word(R.string.valentina, R.string.valentina_profession, R.string.body_details_description_valentina,
                R.drawable.valentina_tereshkova_listimg, R.drawable.russia_flag, R.drawable.valentina_portrait));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //We need to pass only the position. We can retrieve the rest from the list on the next page.
                // Others were redundant I erased them(Oya)
                //we use INTENT to turn on new ones activity
                Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                myIntent.putExtra(CHOSEN_WOMAN, words.get(position));
                      // Start the new activity
               startActivity(myIntent);
            }
        });
    }

    // this is to create the menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    // this is to create the different parts of the menu bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_application:
                Intent intent1 = new Intent(this, AboutApplication.class);
                this.startActivity(intent1);
                return true;
            case R.id.quiz:
                Intent intent2 = new Intent(this, QuizActivity.class);
                this.startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
