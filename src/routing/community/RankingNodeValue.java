package routing.community;

import core.DTNHost;
import java.util.*;

public interface RankingNodeValue {

    /**
     * Get the ranking for the specified host.
     * 
     * @param host The host for which to get the ranking.
     * @return The ranking value for the specified host.
     */
    public double getRanking(DTNHost host);

    /**
     * Get all rankings for all nodes with the latest ranking.
     * 
     * @return A map containing the rankings for all nodes.
     */
    public Map<DTNHost, Double> getAllRankings();

}
