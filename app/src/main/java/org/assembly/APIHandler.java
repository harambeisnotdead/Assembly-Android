package org.assembly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.assembly.models.Proposal;
import org.assembly.models.Vote;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.EncryptedPrivateKeyInfo;

public class APIHandler {
    private String BASE_API_URL;
    private Context context;

    public APIHandler(Context context) {
        this.context = context;
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
                proposals.addAll(parseResponse(connection.getInputStream(),
                        new TypeToken<List<Proposal>>(){}.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proposals;
    }

    public ArrayList<Vote> getVotes(String endpoint) {
        //TODO
        ArrayList<Vote> votes = new ArrayList<>();
        return votes;
    }

    public boolean register(String username, String nationalId, String password, String email) {
        try {
            URL url = new URL(String.format(BASE_API_URL, Endpoints.CITIZENS,
                    Endpoints.Citizens.CREATE));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            os.write(String.format("user.username=%s"
                                    + "&national_id=%s"
                                    + "&user.password=%s"
                                    + "&user.email=%s",
                                    username, nationalId, password, email).getBytes());
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED)
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean login(String username, String password) {
        Map<String, String> map = new HashMap<String, String>(){{put("token", "");}};
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        if (sp.contains("token"))
            return true;

        try {
            URL url = new URL(String.format(BASE_API_URL, Endpoints.CITIZENS,
                    Endpoints.Citizens.LOGIN));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            os.write(String.format("username=%s&password=%s", username, password).getBytes());
            os.flush();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                map.putAll(parseResponse(connection.getInputStream(),
                        new TypeToken<Map<String,String>>(){}.getType()));
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        sp.edit().putString("token", map.get("token")).commit();
        return true;
    }

    private <T> T parseResponse(InputStream is, Type type) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            return new Gson().fromJson(br.readLine(), type);
        }
    }

    public static class Endpoints {
        public static final String PROPOSALS = "proposals";
        public static final String COMMENTS = "comments";
        public static final String VOTES = "votes";
        public static final String CITIZENS = "citizen";

        public static class Proposals {
            public static final String REVIEWING = "reviewing";
            public static final String DEBATING = "debating";
            public static final String VOTING = "voting";
            public static final String MOST_DEBATED = "most-debated";
            public static final String MOST_VOTED = "most-voted";
        }

        public static class Citizens {
            public static final String CREATE = "create";
            public static final String LOGIN = "login";
        }
    }
}
