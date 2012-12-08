package storm.contrib.spring.topology.component.grouping;

import backtype.storm.topology.BoltDeclarer;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/4/12
 */
public class LocalOrShuffleBoltGrouping extends GenericBoltGrouping implements IBoltGrouping {

    public LocalOrShuffleBoltGrouping(final String componentId, final String streamId) {
        super(componentId, streamId);
    }

    public LocalOrShuffleBoltGrouping(final String componentId) {
        super(componentId);
    }

    public void addToBolt(final BoltDeclarer boltDeclarer) {
        if (streamId == null) {
            boltDeclarer.localOrShuffleGrouping(componentId);
        } else {
            boltDeclarer.localOrShuffleGrouping(componentId, streamId);
        }
    }
}
