package com.sinapse.unebnoticias.provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by ClaudioSouza on 14/09/2016.
 */
public class SearchableProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.sinapse.unebnoticias.provider.SearchableProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public SearchableProvider(){
        setupSuggestions(AUTHORITY, MODE);
    }
}
