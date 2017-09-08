# What each performance monitor/data analysis tool in the DDMS does?

## Viewing heap usage for a process

DDMS allows you to view how much heap memory a process is using. This information is useful in tracking heap usage at a certain point of time during the execution of your application.

To view heap usage for a process:

1. In the Devices tab, select the process that you want to see the heap information for.
2. Click the Update Heap button to enable heap information for the process.
3. In the Heap tab, click Cause GC to invoke garbage collection, which enables the collection of heap data. When the operation completes, you will see a group of object types and the memory that has been allocated for each type. You can click Cause GC again to refresh the data.
4. Click on an object type in the list to see a bar graph that shows the number of objects allocated for a particular memory size in bytes.

## Tracking memory allocation of objects

DDMS provides a feature to track objects that are being allocated to memory and to see which classes and threads are allocating the objects. This allows you to track, in real time, where objects are being allocated when you perform certain actions in your application. This information is valuable for assessing memory usage that can affect application performance.

To track memory allocation of objects:

1. In the Devices tab, select the process that you want to enable allocation tracking for.
2. In the Allocation Tracker tab, click the Start Tracking button to begin allocation tracking. At this point, anything you do in your application will be tracked.
3. Click Get Allocations to see a list of objects that have been allocated since you clicked on the Start Tracking button. You can click on Get Allocations again to append to the list new objects that have been allocated.
4. To stop tracking or to clear the data and start over, click the Stop Tracking button.
5. Click on a specific row in the list to see more detailed information such as the method and line number of the code that allocated the object.

## Working with an emulator or device's file system

DDMS provides a File Explorer tab that allows you to view, copy, and delete files on the device. This feature is useful in examining files that are created by your application or if you want to transfer files to and from the device.

To work with an emulator or device's file system:

1. In the Devices tab, select the emulator that you want to view the file system for.
2. To copy a file from the device, locate the file in the File Explorer and click the Pull file button.
3. To copy a file to the device, click the Push file button on the File Explorer tab.

## Examining thread information

The Threads tab in DDMS shows you the currently running threads for a selected process.

1. In the Devices tab, select the process that you want to examine the threads for.
2. Click the Update Threads button.
3. In the Threads tab, you can view the thread information for the selected process.

## Starting method profiling

Method profiling is a means to track certain metrics about a method, such as number of calls, execution time, and time spent executing the method. If you want more granular control over where profiling data is collected, use the startMethodTracing() and stopMethodTracing() methods.

Before you start method profiling in DDMS, be aware of the following restrictions:

- Android 2.1 and earlier devices must have an SD card present and your application must have permission to write to the SD card.
- Android 2.2 and later devices do not need an SD card. The trace log files are streamed directly to your development machine.

To start method profiling:

1. On the Devices tab, select the process that you want to enable method profiling for.
2. Click the Start Method Profiling button.
3. In Android 4.4 and later, choose either trace-based profiling or sample-based profiling with a specified sampling interval. For earlier versions of Android, only trace-based profiling is available.
4. Interact with your application to start the methods that you want to profile.
5. Click the Stop Method Profiling button. DDMS stops profiling your application and opens Traceview with the method profiling information that was collected between the time you clicked on Start Method Profiling and Stop Method Profiling.

## Using the Network Traffic tool

In Android 4.0, the DDMS (Dalvik Debug Monitor Server) includes a Detailed Network Usage tab that makes it possible to track when your application is making network requests. Using this tool, you can monitor how and when your app transfers data and optimize the underlying code appropriately. You can also distinguish between different traffic types by applying a "tag" to network sockets before use.

These tags are shown in a stack area chart in DDMS, as shown in figure 1:

![alt text](https://developer.android.com/images/developing/ddms-network.png)

Figure 1. Network Usage tab.

By monitoring the frequency of your data transfers, and the amount of data transferred during each connection, you can identify areas of your application that can be made more battery-efficient. Generally, you should look for short spikes that can be delayed, or that should cause a later transfer to be pre-empted.

To better identify the cause of transfer spikes, the TrafficStats API allows you to tag the data transfers occurring within a thread using setThreadStatsTag(), followed by manually tagging (and untagging) individual sockets using tagSocket() and untagSocket(). For example:

```
TrafficStats.setThreadStatsTag(0xF00D);
TrafficStats.tagSocket(outputSocket);
// Transfer data using socket
TrafficStats.untagSocket(outputSocket);
```

Alternatively, the URLConnection APIs included in the platform automatically tag sockets internally based on the active tag (as identified by getThreadStatsTag()). These APIs correctly tag/untag sockets when recycled through keep-alive pools. In the following example, setThreadStatsTag() sets the active tag to be 0xF00D. There can only be one active tag per thread. That is the value that will be returned by getThreadStatsTag() and thus used by the HTTP client to tag sockets. The finally statement invokes clearThreadStatsTag() to clear the tag.

```
TrafficStats.setThreadStatsTag(0xF00D);
    try {
        // Make network request using your http client.
    } finally {
        TrafficStats.clearThreadStatsTag();
}
```

Socket tagging is supported in Android 4.0, but real-time stats will only be displayed on devices running Android 4.0.3 or higher.

## Using LogCat

LogCat is integrated into DDMS, and outputs the messages that you print out using the Log class along with other system messages such as stack traces when exceptions are thrown.

When you have set up your logging, you can use the LogCat feature of DDMS to filter certain messages with the following buttons:

- Verbose
- Debug
- Info
- Warn
- Error

You can also setup your own custom filter to specify more details such as filtering messages with the log tags or with the process id that generated the log message. The add filter, edit filter, and delete filter buttons let you manage your custom filters.
