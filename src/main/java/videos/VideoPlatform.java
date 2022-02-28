package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoPlatform {
    private List<Channel> channels = new ArrayList<>();

    public void readDataFromFile(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            bufferedReader.readLine();
            String line;
            String[] fields;
            while ((line = bufferedReader.readLine()) != null) {
                fields = line.split(";");
                addChannel(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
            }
        } catch (IOException ioException) {
            throw new IllegalArgumentException("Cannot open file for read!", ioException);
        }
    }

    public int calculateSumOfVideos() {
        return channels.stream()
                .mapToInt(Channel::getNumberOfVideos)
                .sum();
    }

    private void addChannel(String channelName, int subscriptions, int numberOfVideos) {
        channels.add(new Channel(channelName, subscriptions, numberOfVideos));
    }

    public List<Channel> getChannels() {
        return Collections.unmodifiableList(channels);
    }
}