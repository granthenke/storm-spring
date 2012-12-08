package storm.contrib.spring.topology;

import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import org.springframework.beans.factory.FactoryBean;
import storm.contrib.spring.topology.component.bolt.IBolt;
import storm.contrib.spring.topology.component.spout.ISpout;

import java.util.List;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/3/12
 */
public class TopologyFactory implements FactoryBean<StormTopology> {

    private final List<ISpout> spouts;
    private final List<IBolt> bolts;

    public TopologyFactory(final List<ISpout> spout, final List<IBolt> bolt) {
        this.spouts = spout;
        this.bolts = bolt;
    }

    public StormTopology getObject() throws Exception {
        final TopologyBuilder builder = new TopologyBuilder();

        setTopologySpouts(builder);
        setTopologyBolts(builder);

        return builder.createTopology();
    }

    private void setTopologySpouts(final TopologyBuilder builder) {
        for (ISpout spout : spouts) {
            spout.addToTopology(builder);
        }
    }

    private void setTopologyBolts(final TopologyBuilder builder) {
        for (IBolt bolt : bolts) {
            bolt.addToTopology(builder);
        }
    }

    public Class<?> getObjectType() {
        return StormTopology.class;
    }

    public boolean isSingleton() {
        return false;
    }
}
