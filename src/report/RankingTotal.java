package report;

import java.util.*;
import core.*;
import routing.*;
import routing.community.PeopleRank;
import routing.community.RankingNodeValue;

public class RankingTotal extends Report {

    public RankingTotal() {
        init();
    }

    public void done() {
        // Get all hosts in the network
        List<DTNHost> hosts = SimScenario.getInstance().getHosts();

        // Initialize variables to store total ranking and ranking list
        double totalRanking = 0.0;
        List<Double> rankingList = new ArrayList<>();
        Map<DTNHost, Double> allRankings = new HashMap<>();

        // Iterate through all hosts to calculate total ranking
        for (DTNHost host : hosts) {
            // Check if the host has a router and routing decision engine
            if (host.getRouter() instanceof DecisionEngineRouter) {
                RoutingDecisionEngine decisionEngine = ((DecisionEngineRouter) host.getRouter()).getDecisionEngine();
                if (decisionEngine instanceof PeopleRank) {
                    RankingNodeValue rankingNodeValue = (RankingNodeValue) decisionEngine;
                    // Get the ranking of the current host
                    double ranking = rankingNodeValue.getRanking(host);
                    Map<DTNHost, Double> getRankAll = rankingNodeValue.getAllRankings();
                    // Add the ranking of the current host to the total ranking
                    totalRanking += ranking;

                    // Add the ranking to the ranking list
                    rankingList.add(ranking);

                    // Merge allRankings with the new rankings
                    allRankings.putAll(getRankAll);
                }
            }
        }

        // Write the total ranking to the output file
        // Iterate over the map entries to write all rankings to the output file
        for (Map.Entry<DTNHost, Double> entry : allRankings.entrySet()) {
            DTNHost hostEntry = entry.getKey();
            double rankingEntry = entry.getValue();
            // Write the ranking to the output file
            write("Ranking for Host " + hostEntry + ": " + rankingEntry);
        }

        super.done();
    }

}
