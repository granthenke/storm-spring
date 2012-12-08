package storm.contrib.spring.topology.component.bolt;

import backtype.storm.topology.BoltDeclarer;
import storm.contrib.spring.topology.component.ComponentConfig;
import storm.contrib.spring.topology.component.grouping.IBoltGrouping;

import java.util.ArrayList;
import java.util.List;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/5/12
 */
public abstract class GenericBolt extends ComponentConfig implements IBolt {

    protected final String componentId;
    protected List<IBoltGrouping> boltGroupings;
    protected Integer parallelismHint;

    protected GenericBolt(final String componentId) {
        this.componentId = componentId;
        this.boltGroupings = new ArrayList<IBoltGrouping>();
        this.parallelismHint = null;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setBoltGroupings(final List<IBoltGrouping> boltGroupings) {
        this.boltGroupings = boltGroupings;
    }

    public List<IBoltGrouping> getBoltGroupings() {
        return boltGroupings;
    }

    public void setParallelismHint(final Integer parallelismHint) {
        this.parallelismHint = parallelismHint;
    }

    public Integer getParallelismHint() {
        return parallelismHint;
    }

    protected void addBoltGroupingsToBolt(final BoltDeclarer boltDeclarer) {
        for (IBoltGrouping boltGrouping : boltGroupings) {
            boltGrouping.addToBolt(boltDeclarer);
        }
    }
}
