package storm.contrib.spring.topology.component.grouping;

import backtype.storm.topology.BoltDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/4/12
 */
public class FieldsBoltGrouping implements IBoltGrouping {

    private final String componentId;
    private final String streamId;
    private final Fields fields;

    public FieldsBoltGrouping(final String componentId, final String streamId, final Fields fields) {
        this.componentId = componentId;
        this.streamId = streamId;
        this.fields = fields;
    }

    public FieldsBoltGrouping(final String componentId, final Fields fields) {
        this(componentId, Utils.DEFAULT_STREAM_ID, fields);
    }

    public String getComponentId() {
        return componentId;
    }

    public String getStreamId() {
        return streamId;
    }

    public Fields getFields() {
        return fields;
    }

    public void addToBolt(final BoltDeclarer boltDeclarer) {
        if (streamId == null) {
            boltDeclarer.fieldsGrouping(componentId, fields);
        } else {
            boltDeclarer.fieldsGrouping(componentId, streamId, fields);
        }
    }
}
