package org.assembly.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.assembly.R;
import org.assembly.models.Citizen;
import org.assembly.models.Proposal;
import org.assembly.models.Vote;
import org.assembly.utils.Endpoints;
import org.assembly.utils.SharedKeys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIClient {
    private String BASE_API_URL;
    private Context context;

    public APIClient() {}

    public APIClient(Context context) {
        this.context = context;
        Resources resources = context.getResources();
        BASE_API_URL = resources.getString(R.string.base_api_url);
    }

    public ArrayList<Proposal> getProposals(String endpoint) {
        ArrayList<Proposal> proposals = new ArrayList<>();
        try {
            URL url = new URL(String.format("%s/%s/%s", BASE_API_URL, Endpoints.PROPOSALS, endpoint));
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

    public Proposal getLastProposal(String endpoint) {
        return getProposals(endpoint).get(0);
    }

    public boolean createProposal(String title, String description) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            URL url = new URL(String.format("%s/%s/%s", BASE_API_URL, Endpoints.PROPOSALS,
                    Endpoints.Proposals.CREATE));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization",
                    "Token " + sp.getString(SharedKeys.TOKEN, ""));
            OutputStream os = connection.getOutputStream();
            os.write(String.format("title=%s"
                            + "&description=%s"
                            + "&user=%d",
                            title, description, sp.getInt(SharedKeys.CITIZEN_ID, 0)).getBytes());
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED)
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Vote> getVotesInPhase(String phase) {
        ArrayList<Vote> votes = new ArrayList<>();
        try {
            URL url = new URL(String.format("%s/%s/%s/%s", BASE_API_URL, Endpoints.PROPOSALS,
                    Endpoints.Proposals.USER_VOTES, phase));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization",
                    "Token " + PreferenceManager.getDefaultSharedPreferences(context)
                            .getString(SharedKeys.TOKEN, ""));
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                votes.addAll(parseResponse(connection.getInputStream(),
                        new TypeToken<List<Vote>>(){}.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return votes;
    }

    public boolean reviewVote(int user, int proposal) {
        try {
            URL url = new URL(String.format("%s/%s/%s/%s", BASE_API_URL, Endpoints.PROPOSALS,
                              Endpoints.Proposals.REVIEW, Endpoints.Proposals.VOTE));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization",
                    "Token " + PreferenceManager.getDefaultSharedPreferences(context)
                            .getString(SharedKeys.TOKEN, ""));
            OutputStream os = connection.getOutputStream();
            os.write(String.format("user=%s"
                                    + "&phase=%s"
                                    + "&proposal=%s",
                                    user, Endpoints.Proposals.REVIEW, proposal).getBytes());
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED)
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean votingVote(boolean option, int proposal) {
        try {
            SecureRandom sr = new SecureRandom();
            byte[] password = new byte[32];
            sr.nextBytes(password);
            String user_pw = android.util.Base64.encodeToString(password,
                    android.util.Base64.URL_SAFE);

            URL url = new URL(String.format("%s/%s/%s/%s", BASE_API_URL, Endpoints.PROPOSALS,
                    Endpoints.Proposals.VOTE, Endpoints.Proposals.VOTE));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization",
                    "Token " + PreferenceManager.getDefaultSharedPreferences(context)
                            .getString(SharedKeys.TOKEN, ""));
            OutputStream os = connection.getOutputStream();
            os.write(String.format("user_pw=%s"
                                    + "&phase=%s"
                                    + "&option=%s"
                                    + "&proposal=%s",
                                    user_pw, Endpoints.Proposals.VOTE, option,
                                    proposal).getBytes());
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED)
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean register(String username, String nationalId, String password, String email) {
        try {
            URL url = new URL(String.format("%s/%s/%s", BASE_API_URL, Endpoints.CITIZENS,
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
        Map<String, String> map = new HashMap<String, String>();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        if (sp.contains(SharedKeys.TOKEN))
            return true;

        try {
            URL url = new URL(String.format("%s/%s/%s", BASE_API_URL, Endpoints.CITIZENS,
                    Endpoints.Citizens.LOGIN));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            os.write(String.format("username=%s&password=%s", username, password).getBytes());
            os.flush();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                map.putAll(parseResponse(connection.getInputStream(),
                        new TypeToken<Map<String, String>>(){}.getType()));
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        sp.edit().putString(SharedKeys.TOKEN, map.get("token")).apply();
        return true;
    }

    public void getCitizen(String username) {
        Citizen citizen = null;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            URL url = new URL(String.format("%s/%s/%s", BASE_API_URL,
                    Endpoints.CITIZENS, username));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization",
                    "Token " + sp.getString(SharedKeys.TOKEN, ""));
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
                citizen = parseResponse(connection.getInputStream(), Citizen.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (citizen == null)
            return;

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SharedKeys.CITIZEN_NATIONAL_ID, citizen.getNational_id());
        editor.putInt(SharedKeys.CITIZEN_ID, citizen.getUser().getId());
        editor.putString(SharedKeys.CITIZEN_USERNAME, citizen.getUser().getUsername());
        editor.putString(SharedKeys.CITIZEN_EMAIL, citizen.getUser().getEmail());
        editor.putString(SharedKeys.CITIZEN_FIRST_NAME, citizen.getUser().getFirst_name());
        editor.putString(SharedKeys.CITIZEN_LAST_NAME, citizen.getUser().getLast_name());
        editor.apply();
    }

    public <T> T parseResponse(InputStream is, Type type) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            return new Gson().fromJson(br.readLine(), type);
        }
    }
}
