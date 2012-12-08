Storm Spring
=============================================

Storm Spring provides the functionality to build Storm topologies in a Spring XML configuration
and submit them to a storm cluster or run them locally.

This allows you to modify topology bolts, spouts, configurations, and properties without
recompiling.

Sample
------

Defining topologies using Storm Spring is very similar to defining a topology in the Storm TopologyBuilder api.
To start lets take a look at the ExclamationTopology in the [Storm Starter Project][2]. The topology defined in java is
as follows:

```java
TopologyBuilder builder = new TopologyBuilder();
builder.setSpout("words", new TestWordSpout(), 10);
builder.setBolt("exclaim1", new ExclamationBolt(), 3)
        .shuffleGrouping("words");
builder.setBolt("exclaim2", new ExclamationBolt(), 2)
        .shuffleGrouping("exclaim1");
```

To see an example of how to use Storm Spring to define the same topology please see the [Storm Spring Sample Project][3].

Contributing
------------

1. Fork it.
2. Create a branch (`git checkout -b my_script`)
3. Commit your changes (`git commit -am "Added Script"`)
4. Push to the branch (`git push origin my_script`)
5. Create an [Issue][1] with a link to your branch

[1]: https://github.com/granthenke/storm-spring/issues
[2]: https://github.com/nathanmarz/storm-starter
[3]: https://github.com/granthenke/storm-spring-sample