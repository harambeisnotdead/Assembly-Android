package org.assembly;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.assembly.api.APIClient;
import org.assembly.models.Proposal;
import org.assembly.utils.Endpoints;
import org.assembly.utils.SharedKeys;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AndroidUnitTests {
    @Test
    public void loginUser_isLogged() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        PreferenceManager.getDefaultSharedPreferences(appContext).edit().remove(SharedKeys.TOKEN);
        APIClient api = new APIClient(appContext);
        assertTrue(api.login("testing", "testing"));
    }

    @Test
    public void registerUser_isRegistered() throws Exception {
        int n = new Random().nextInt(1000);
        Context appContext = InstrumentationRegistry.getTargetContext();
        PreferenceManager.getDefaultSharedPreferences(appContext).edit().remove(SharedKeys.TOKEN);
        APIClient api = new APIClient(appContext);
        assertTrue(api.register("testing" + n, "testing" + n, "testing", "testing@testing.com"));
    }

    @Test
    public void createProposal_isCreated() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        APIClient api = new APIClient(appContext);
        assertTrue(api.createProposal("Test title", "Test description"));
    }

    @Test
    public void getProposals_isNotNull() throws Exception {
        ArrayList<Proposal> proposals;
        Context appContext = InstrumentationRegistry.getTargetContext();
        APIClient api = new APIClient(appContext);
        proposals = api.getProposals(Endpoints.Proposals.REVIEWING);
        assertNotNull(proposals);
    }
}
