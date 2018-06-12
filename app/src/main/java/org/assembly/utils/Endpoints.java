package org.assembly.utils;

public class Endpoints {
    public static final String PROPOSALS = "proposals";
    public static final String COMMENTS = "comments";
    public static final String CITIZENS = "citizen";

    public static class Proposals {
        public static final String CREATE = "create";
        public static final String REVIEWING = "reviewing";
        public static final String DEBATING = "debating";
        public static final String VOTING = "voting";
        public static final String VOTE = "vote";
        public static final String REVIEW = "review";
        public static final String DEBATE = "debate";
        public static final String USER_VOTES = "user-votes";
        public static final String MOST_DEBATED = "most-debated";
        public static final String MOST_VOTED = "most-voted";
    }


    public static class Citizens {
        public static final String CREATE = "create";
        public static final String LOGIN = "login";
    }
}
