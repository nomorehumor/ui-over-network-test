import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class RemoteUILoader {
    public static void main(String[] args) throws Exception {
        System.out.println("Provide a URL to the UI JSON file:");

        // Read a link from stdin
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String uiJsonDefinitionURL = reader.readLine();
        if (uiJsonDefinitionURL == null || uiJsonDefinitionURL.isEmpty()) {
            System.out.println("No URL provided. Exiting.");
            return;
        }

        createUI(uiJsonDefinitionURL);
    }

    private static void createUI(String uiJsonDefinitionURL) {
        SwingUtilities.invokeLater(() -> {
            try {
                JsonObject json = loadUIFromURL(uiJsonDefinitionURL);
                createUIFromJSON(json);
            } catch (Exception e) {
                System.err.println("Error loading UI from URL: " + e.getMessage());
            }
        });
    }

    private static void createUIFromJSON(JsonObject json) {
        JFrame frame = new JFrame("Remote UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JsonArray components = json.getAsJsonArray("components");
        for (JsonElement elem : components) {
            JsonObject comp = elem.getAsJsonObject();
            switch (comp.get("type").getAsString()) {
                case "label":
                    panel.add(new JLabel(comp.get("text").getAsString()));
                    break;
                case "button":
                    JButton btn = new JButton(comp.get("text").getAsString());
                    panel.add(btn);
                    break;
            }
        }

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    static JsonObject loadUIFromURL(String urlStr) throws Exception {
        URI uri = new URI(urlStr);
        JsonReader reader = new JsonReader(new InputStreamReader(uri.toURL().openStream()));
        reader.setLenient(true);

        return JsonParser.parseReader(reader).getAsJsonObject();
    }
}