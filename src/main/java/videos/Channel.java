package videos;

public class Channel {
    private String channelName;
    private int subscriptions;
    private int numberOfVideos;


    public Channel(String channelName, int subscriptions, int numberOfVideos) {
        this.channelName = channelName;
        this.subscriptions = subscriptions;
        this.numberOfVideos = numberOfVideos;
    }

    public String getChannelName() {
        return channelName;
    }

    public int getSubscriptions() {
        return subscriptions;
    }

    public int getNumberOfVideos() {
        return numberOfVideos;
    }
}