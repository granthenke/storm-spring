package storm.contrib.spring.topology.component.grouping;

/**
 * [Class Description]
 *
 * @author Grant Henke
 * @since 12/4/12
 */
public abstract class GenericBoltGrouping {
    protected final String componentId;
    protected final String streamId;

    protected GenericBoltGrouping(final String componentId, final String streamId) {
        this.componentId = componentId;
        this.streamId = streamId;
    }

    protected GenericBoltGrouping(final String componentId) {
        this(componentId, null);
    }

    public String getComponentId() {
        return componentId;
    }

    public String getStreamId() {
        return streamId;
    }
}
