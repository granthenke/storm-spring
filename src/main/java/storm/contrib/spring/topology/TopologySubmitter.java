package storm.contrib.spring.topology;

import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/3/12
 */
public final class TopologySubmitter {

    private TopologySubmitter() {}

    private static void validateArgs(final String[] args) {
        if (args[0] == null) {
            throw new IllegalArgumentException("Argument 1: XmlApplicationContext was not defined");
        }

        if (args[1] == null) {
            throw new IllegalArgumentException("Argument 2: TopologySubmission bean was not defined");
        }
    }

    private static void submitTopologies(final TopologySubmission topologySubmission) throws AlreadyAliveException, InvalidTopologyException {
        for (Map.Entry<String, StormTopology> entry : topologySubmission.getStormTopologies().entrySet()) {
            StormSubmitter.submitTopology(entry.getKey(), topologySubmission.getConfig(), entry.getValue());
        }
    }

    public static void main(final String[] args) throws AlreadyAliveException, InvalidTopologyException {
        validateArgs(args);
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(args[0]);
        final TopologySubmission topologySubmission = (TopologySubmission) applicationContext.getBean(args[1]);
        submitTopologies(topologySubmission);
    }
}
