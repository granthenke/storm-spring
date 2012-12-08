package storm.contrib.spring.topology;

import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.utils.Utils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/7/12
 */
public final class LocalTopologySubmitter {

    private LocalTopologySubmitter() {
    }

    private static void validateArgs(final String[] args) {
        if (args[0] == null) {
            throw new IllegalArgumentException("Argument 1: XmlApplicationContext was not defined");
        }

        if (args[1] == null) {
            throw new IllegalArgumentException("Argument 2: TopologySubmission bean was not defined");
        }

        if (args[2] == null) {
            throw new IllegalArgumentException("Argument 3: Topology runtime was not defined");
        }
    }

    private static void submitTopologies(final LocalCluster cluster, final TopologySubmission topologySubmission) {
        for (Map.Entry<String, StormTopology> entry : topologySubmission.getStormTopologies().entrySet()) {
            cluster.submitTopology(entry.getKey(), topologySubmission.getConfig(), entry.getValue());
        }
    }

    private static void killTopologies(final LocalCluster cluster, final TopologySubmission topologySubmission) {
        for (String key : topologySubmission.getStormTopologies().keySet()) {
            cluster.killTopology(key);
        }
    }

    public static void main(final String[] args) {
        validateArgs(args);
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(args[0]);
        final TopologySubmission topologySubmission = (TopologySubmission) applicationContext.getBean(args[1]);
        final Integer runtime = NumberUtils.toInt(args[2]);

        final LocalCluster cluster = new LocalCluster();
        submitTopologies(cluster, topologySubmission);
        Utils.sleep(runtime); // Let run for a bit
        killTopologies(cluster, topologySubmission);
        cluster.shutdown();
    }
}
