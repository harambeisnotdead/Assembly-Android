package org.assembly;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.assembly.models.Proposal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class APIHandler {
    private String BASE_API_URL;

    public APIHandler(Context context) {
        Resources resources = context.getResources();
        BASE_API_URL = resources.getString(R.string.base_api_url);
    }

    public ArrayList<Proposal> getProposals(String endpoint) {
        ArrayList<Proposal> proposals = new ArrayList<>();
        try {
            URL url = new URL(String.format(BASE_API_URL, Endpoints.PROPOSALS, endpoint));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                proposals.addAll(parseResponse(connection.getInputStream()));
            else
                Log.d("RESPONSE", "Error, response code: " + connection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proposals;
    }

    private List<Proposal> parseResponse(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            Type type = new TypeToken<List<Proposal>>() {
            }.getType();
            return new Gson().fromJson(br.readLine(), type);
        }
    }

    public static class Endpoints {
        public static final String PROPOSALS = "proposals";
        public static final String COMMENTS = "comments";
        public static final String VOTES = "votes";

        public static class Proposals {
            public static final String REVIEWING = "reviewing";
            public static final String DEBATING = "debating";
            public static final String VOTING = "voting";
            public static final String MOST_DEBATED = "most-debated";
            public static final String MOST_VOTED = "most-voted";
        }
    }
}
