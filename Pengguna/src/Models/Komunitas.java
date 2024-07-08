package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Komunitas {
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty imageUrl = new SimpleStringProperty(this, "imageUrl");

    public Komunitas(String name, String imageUrl) {
        this.name.set(name);
        this.imageUrl.set(imageUrl);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl.get();
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl.set(imageUrl);
    }

    public StringProperty imageUrlProperty() {
        return imageUrl;
    }
}

