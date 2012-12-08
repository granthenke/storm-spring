package storm.contrib.spring.topology;

import backtype.storm.Config;
import backtype.storm.generated.StormTopology;

import java.util.Map;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/6/12
 */
public interface TopologySubmission {

    public Map<String, StormTopology> getStormTopologies();

    public void setConfig(final Config config);

    public Config getConfig();

}
